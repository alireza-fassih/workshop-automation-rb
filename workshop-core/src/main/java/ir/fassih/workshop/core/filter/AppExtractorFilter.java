package ir.fassih.workshop.core.filter;


import ir.fassih.workshop.core.entity.WorkshopAppEntity;
import ir.fassih.workshop.core.holder.AppHolder;
import ir.fassih.workshop.core.manager.WorkshopAppManager;
import ir.fassih.workshop.core.rest.model.CommonsResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Slf4j
@Component
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class AppExtractorFilter implements Filter, GeneralResponseCloser{

    private static final String APP_HEADER = "Workshop-app";

    private final WorkshopAppManager manager;


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        AppHolder.AppModel appModel = AppHolder.getAppModel();
        if(appModel == null) {
            doFilterInternal(servletRequest, servletResponse, filterChain);
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    private void doFilterInternal(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (servletRequest instanceof HttpServletRequest && servletResponse instanceof HttpServletResponse ) {
            HttpServletRequest request = (HttpServletRequest) servletRequest;
            HttpServletResponse response = (HttpServletResponse) servletResponse;
            String appName = request.getHeader(APP_HEADER);
            if(StringUtils.hasText(appName)) {
                WorkshopAppEntity entity = manager.findByTitle(appName);
                if(entity != null) {
                    AppHolder.setAppModel(AppHolder.AppModel.builder()
                        .id(entity.getId())
                        .title(entity.getTitle())
                        .build());
                    try {
                        filterChain.doFilter(servletRequest, servletResponse);
                        return;
                    } finally {
                        AppHolder.setAppModel(null);
                    }
                }
            }
            closeResponse(servletResponse, new CommonsResponse("app not found"), HttpStatus.BAD_REQUEST);
        } else {
            closeResponse(servletResponse, new CommonsResponse("Not http request"), HttpStatus.BAD_REQUEST);
        }
    }





}

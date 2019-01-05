package ir.fassih.workshop.usermanagement.filter;

import ir.fassih.workshop.core.jwt.JwtAlgorithm;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;


@Slf4j
@Order(Integer.MIN_VALUE)
@Component
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class JwtExtractorFilter implements Filter {

    private final List<String> notSecureUris = Arrays.asList("/rest/guest", "/rest/auth");

    private final JwtAlgorithm algorithm;


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (servletRequest instanceof HttpServletRequest) {
            HttpServletRequest request = (HttpServletRequest) servletRequest;
            if( isNotSecureUri( request.getRequestURI() ) ) {
                filterChain.doFilter(servletRequest, servletResponse);
            } else {
                // todo: extract jwt token
            }
        } else {
            log.warn("not http request {}", servletRequest.getClass());
            try ( PrintWriter writer = servletResponse.getWriter() ) {
                writer.println("{ \"message\" : \"Not Http request\" }");
            }
        }
    }

    private boolean isNotSecureUri(String uri) {
        return notSecureUris.stream()
            .anyMatch(uri::startsWith);
    }

}

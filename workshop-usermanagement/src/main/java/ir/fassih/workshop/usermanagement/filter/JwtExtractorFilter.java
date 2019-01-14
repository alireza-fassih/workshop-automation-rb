package ir.fassih.workshop.usermanagement.filter;

import ir.fassih.workshop.core.filter.GeneralResponseCloser;
import ir.fassih.workshop.core.jwt.JwtAlgorithm;
import ir.fassih.workshop.core.rest.model.CommonsResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;


@Slf4j
@Order(Integer.MIN_VALUE)
@Component
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class JwtExtractorFilter implements Filter, GeneralResponseCloser {

    private final List<String> notSecureUris = Arrays.asList("/rest/guest", "/rest/auth");

    private final JwtAlgorithm algorithm;


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (servletRequest instanceof HttpServletRequest) {
            HttpServletRequest request = (HttpServletRequest) servletRequest;
            if( isNotSecureUri( request.getRequestURI() ) ) {
                filterChain.doFilter(servletRequest, servletResponse);
            } else {
                Optional<Cookie> token = getTokenCookie(request);
                if( token.isPresent() ) {
                    // TODO: validate token
                } else {
                    closeResponse(servletResponse, new CommonsResponse("Authentication failed"), HttpStatus.UNAUTHORIZED);
                }
            }
        } else {
            closeResponse(servletResponse, new CommonsResponse("Not http request"), HttpStatus.BAD_REQUEST);
        }
    }

    private Optional<Cookie> getTokenCookie(HttpServletRequest request) {
        if(request.getCookies() == null) {
            return Optional.empty();
        }
        return Stream.of(request.getCookies())
                .filter(c -> "token".equals(c.getName()))
                .findFirst();
    }

    private boolean isNotSecureUri(String uri) {
        return notSecureUris.stream()
            .anyMatch(uri::startsWith);
    }

}

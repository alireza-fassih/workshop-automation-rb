package ir.fassih.workshop.usermanagement.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.exceptions.JWTVerificationException;
import ir.fassih.workshop.core.filter.GeneralResponseCloser;
import ir.fassih.workshop.core.jwt.JwtAlgorithm;
import ir.fassih.workshop.core.rest.model.CommonsResponse;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;


@Slf4j
@Order(Integer.MIN_VALUE)
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class JwtExtractorFilter implements Filter, GeneralResponseCloser, InitializingBean {

    private final List<String> notSecureUris = Arrays.asList("/rest/guest", "/rest/auth");

    private JWTVerifier verifier;

    private final JwtAlgorithm algorithm;



    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (servletRequest instanceof HttpServletRequest &&
            servletResponse instanceof HttpServletResponse ) {
            HttpServletRequest request = (HttpServletRequest) servletRequest;
            HttpServletResponse response = (HttpServletResponse) servletResponse;
            if( isNotSecureUri( request.getRequestURI() ) ) {
                filterChain.doFilter(servletRequest, servletResponse);
            } else {
                Optional<Cookie> token = getTokenCookie(request);
                if( token.isPresent() ) {
                    try {
                        verifier.verify(token.get().getValue());
                        // TODO: put user and app to thread local
                        filterChain.doFilter(servletRequest, servletResponse);
                    } catch (JWTVerificationException ex) {
                        log.info("cannot verified jwt ", ex);
                        response.addCookie(removeCookie("token"));
                        response.addCookie(removeCookie("scrf-token"));
                        closeResponse(servletResponse, new CommonsResponse("Invalid token"), HttpStatus.BAD_REQUEST);
                    } finally {
                        // TODO: clear thread local
                    }
                } else {
                    closeResponse(servletResponse, new CommonsResponse("Authentication failed"), HttpStatus.UNAUTHORIZED);
                }
            }
        } else {
            closeResponse(servletResponse, new CommonsResponse("Not http request"), HttpStatus.BAD_REQUEST);
        }
    }

    private Cookie removeCookie(String name) {
        Cookie cookie = new Cookie(name, "");
        cookie.setMaxAge(0);
        return cookie;
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

    @Override
    public void afterPropertiesSet() throws Exception {
        this.verifier = JWT.require(algorithm.getAlgorithm()).build();
    }
}

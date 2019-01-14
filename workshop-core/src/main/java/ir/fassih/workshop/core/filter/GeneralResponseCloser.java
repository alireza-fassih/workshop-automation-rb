package ir.fassih.workshop.core.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import ir.fassih.workshop.core.rest.model.CommonsResponse;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


public interface GeneralResponseCloser {

    Logger log = LoggerFactory.getLogger(GeneralResponseCloser.class);
    ObjectMapper mapper = new ObjectMapper();


    default void closeResponse(ServletResponse servletResponse, CommonsResponse response, HttpStatus status) throws IOException {
        if (servletResponse instanceof HttpServletResponse) {
            HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
            httpServletResponse.setStatus(status.value());
        }
        log.debug("try to close response with reason {} and status {}", response, status);
        try ( PrintWriter writer = servletResponse.getWriter() ) {
            writer.println( mapper.writeValueAsString(response) );
        }
    }
}

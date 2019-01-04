package ir.fassih.workshop.core.rest;

import ir.fassih.workshop.core.localeutil.LocaleUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;
import java.util.stream.Collectors;

@RestControllerAdvice
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CommonsExceptionHandler {

    private LocaleUtil localeUtil;


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorModel methodArgumentNotValidException(MethodArgumentNotValidException ex) {
        String msg = ex.getBindingResult().getFieldErrors()
                .stream().map(this::getLocaleError)
                .collect(Collectors.joining("\n"));
        return new ErrorModel(msg);
    }


    private String getLocaleError(FieldError er) {
        return localeUtil.getString(er.getObjectName() + "." + er.getField()) + " " +
                localeUtil.getString(er.getCode());
    }


    @Getter @Setter
    private static class ErrorModel {
        private String message;
        private Date timestamp;

        private ErrorModel(String message) {
            this.message = message;
            this.timestamp = new Date();
        }
    }

}

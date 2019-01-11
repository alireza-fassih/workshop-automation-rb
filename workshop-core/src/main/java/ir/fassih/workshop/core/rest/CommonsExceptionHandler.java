package ir.fassih.workshop.core.rest;

import ir.fassih.workshop.core.exceptions.AppNotFoundException;
import ir.fassih.workshop.core.exceptions.RestException;
import ir.fassih.workshop.core.localeutil.LocaleUtil;
import ir.fassih.workshop.core.rest.model.CommonsResponse;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@RestControllerAdvice
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CommonsExceptionHandler {

    private LocaleUtil localeUtil;


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public CommonsResponse methodArgumentNotValidException(MethodArgumentNotValidException ex) {
        String msg = ex.getBindingResult().getFieldErrors()
                .stream().map(this::getLocaleError)
                .collect(Collectors.joining("\n"));
        return new CommonsResponse(msg);
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(AppNotFoundException.class)
    public CommonsResponse appNotFoundException(AppNotFoundException ex) {
        return new CommonsResponse("app not found");
    }


    @ExceptionHandler(RestException.class)
    public ResponseEntity<CommonsResponse> restException(RestException ex) {
        return ResponseEntity.status(ex.getStatus())
            .body(new CommonsResponse(localeUtil.getString(ex.getMessage())));
    }


    private String getLocaleError(FieldError er) {
        return localeUtil.getString(er.getObjectName() + "." + er.getField()) + " " +
                localeUtil.getString(er.getCode());
    }

}

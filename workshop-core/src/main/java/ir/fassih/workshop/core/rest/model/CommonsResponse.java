package ir.fassih.workshop.core.rest.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter @Setter
public class CommonsResponse {
    private String message;
    private Date timestamp;

    public CommonsResponse(String message) {
        this.message = message;
        this.timestamp = new Date();
    }
}

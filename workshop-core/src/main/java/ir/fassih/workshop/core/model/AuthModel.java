package ir.fassih.workshop.core.model;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class AuthModel {

    private long appId;
    private String userId;

}

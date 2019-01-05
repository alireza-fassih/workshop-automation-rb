package ir.fassih.workshop.core.baseentity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.UUID;

@Data
@Embeddable
@NoArgsConstructor
public class WorkshopKey implements Serializable {

    private Long    appId;
    private String  id;


    public WorkshopKey(Long appId) {
        this.appId = appId;
        this.id = UUID.randomUUID().toString();
    }

}

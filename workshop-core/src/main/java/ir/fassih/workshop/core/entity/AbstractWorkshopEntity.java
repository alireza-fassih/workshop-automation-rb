package ir.fassih.workshop.core.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.EmbeddedId;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@Getter @Setter
@MappedSuperclass
public abstract class AbstractWorkshopEntity implements Serializable {

    @EmbeddedId
    private WorkshopKey id;

}

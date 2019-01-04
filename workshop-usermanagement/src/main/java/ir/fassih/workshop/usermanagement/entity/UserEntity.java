package ir.fassih.workshop.usermanagement.entity;

import ir.fassih.workshop.core.entity.AbstractBaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "UM_USER")
@Getter @Setter
public class UserEntity extends AbstractBaseEntity {

    @Basic
    @Column(name = "USER_USERNAME")
    private String username;

    @Basic
    @Column(name = "USER_PASSWORD")
    private String password;

    @Basic
    @Column(name = "USER_CREATION_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationTime;

}

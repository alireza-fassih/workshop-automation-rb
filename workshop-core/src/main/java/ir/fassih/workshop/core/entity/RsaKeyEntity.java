package ir.fassih.workshop.core.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "CORE_RSA_KEY")
public class RsaKeyEntity implements Serializable {


    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;


    @Lob
    @Column(name = "PUBLIC_KEY")
    private byte[] publicKey;

    @Lob
    @Column(name = "PRIVATE_KEY")
    private byte[] privateKey;

    @Basic
    @Column(name = "CREATION_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationTime;

}

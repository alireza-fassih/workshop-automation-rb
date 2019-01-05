package ir.fassih.workshop.core.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "CORE_APP")
public class WorkshopAppEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Basic
    @Column(name = "APP_TITLE")
    private String title;

}

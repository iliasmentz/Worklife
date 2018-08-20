package com.linkedin.entities.database;


import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

import static javax.persistence.TemporalType.DATE;

@Data
@Table(name = "job")
@Entity
@DynamicUpdate

public class Job implements Serializable {

    @Id
    @Column(name="job_id")
    private Long job_id;

    @Column(name = "creator_id" , nullable = false)
    private Long creator_id;

    @Column(name="title" ,nullable = false)
    private String title;

    @Column(name="deser" ,nullable = false) //////////////////////////////////////////AYTO TI SKATA EINAI @todo
    private String deser;

}

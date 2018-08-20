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
@Table(name = "post")
@Entity
@DynamicUpdate

public class Post implements Serializable {
    @Id
    @Column(name="post_id")
    private Long post_id;

    @Column(name = "creator_id" , nullable = false)
    private Long creator_id;

    @NotNull
    @Temporal(DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Column(name= "post_date")
    private Date post_date;

    @Column(name="context" ,nullable = false)
    private String context;

}

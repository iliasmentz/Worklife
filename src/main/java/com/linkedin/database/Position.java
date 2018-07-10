package com.linkedin.database;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

import static javax.persistence.TemporalType.DATE;

@Data
@Table(name = "position")
@Entity
@DynamicUpdate

public class Position implements Serializable {
    @Id
    @Column(name="position_id")
    private Long position_id;

    @Column(name = "user_id" , nullable = false)
    private Long user_id;

    @NotNull
    @Temporal(DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Column(name= "starting_date")
    private Date starting_date;

    @NotNull
    @Temporal(DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Column(name= "ending_date")
    private Date ending_date;
}

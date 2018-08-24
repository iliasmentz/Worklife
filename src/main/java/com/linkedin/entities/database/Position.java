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
@Table(name = "position")
@Entity
@DynamicUpdate

public class Position implements Serializable {
    @Id
    @Column(name="position_id")
    private Long positionId;

    @Column(name = "user_id" , nullable = false)
    private Long userId;

    @NotNull
    @Temporal(DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Column(name= "starting_date")
    private Date startingDate;

    @NotNull
    @Temporal(DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Column(name= "ending_date")
    private Date endingDate;
}

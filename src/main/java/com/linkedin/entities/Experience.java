package com.linkedin.entities;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

import static javax.persistence.TemporalType.DATE;

@Data
@Table(name = "experience")
@Entity
@DynamicUpdate

public class Experience implements Serializable {

  @Id
  @Column(name = "experience_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long experienceId;

  @Column(name = "user_id", nullable = false)
  private Long userId;

  @NotNull
  @Temporal(DATE)
  @DateTimeFormat(pattern = "dd/MM/yyyy")
  @Column(name = "start_date")
  private Date startDate;

  @Temporal(DATE)
  @DateTimeFormat(pattern = "dd/MM/yyyy")
  @Column(name = "end_date")
  private Date endDate;


  @NotNull
  @Column(name = "title")
  private String title;


  @NotNull
  @Column(name = "company")
  private String company;

  @NotNull
  @Column(name = "visible")
  private Integer visible;
}

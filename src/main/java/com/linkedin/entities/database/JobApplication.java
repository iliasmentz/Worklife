package com.linkedin.entities.database;


import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Table(name = "JobApplication")
@Entity
@DynamicUpdate
public class JobApplication  implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  @Column(name = "job_application_id")
  private Long jobApplicationId;

  @NotNull
  @Column(name = "userId")
  private Long userId;

  @NotNull
  @Column(name = "jobId")
  private Long jobId;
}

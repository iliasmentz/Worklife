package com.linkedin.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@Table(name = "connection")
@Entity
@DynamicUpdate
public class Connection implements Serializable {

  @Id
  @Column(name = "connection_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long connectionId;


  @NotNull
  @Column(name = "user_requested_id")
  private Long userRequestedId;

  @NotNull
  @Column(name = "user_accepted_id")
  private Long userAcceptedId;

  @NotNull
  @Column(name = "create_date")
  private Date createDate;

  @Column(name = "connection_request_id")
  private Long connectionRequestId;
}

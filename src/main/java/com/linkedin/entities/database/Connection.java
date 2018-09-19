package com.linkedin.entities.database;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
  @Column(name = "date_of_accept")
  private Date dateOfAccept;

  @Column(name = "connection_request_id")
  private Long connectionRequestId;
}

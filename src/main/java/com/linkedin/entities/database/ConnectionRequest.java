package com.linkedin.entities.database;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "connection_request")
@Entity
@DynamicUpdate

public class ConnectionRequest implements Serializable {

  @Id
  @Column(name = "connection_request_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long connectionRequestId;

  @NotNull
  @Column(name = "user_requested_id")
  private Long userRequestedId;

  @NotNull
  @Column(name = "user_target_id")
  private Long userTargetId;

  @NotNull
  @Column(name = "date_of_request")
  private Date dateOfRequest;

  @NotNull
  @Column(name = "status") //True(accept) or False(reject)
  private Integer status; //0->pending , 1 -> accepted , 2 ->rejected
}

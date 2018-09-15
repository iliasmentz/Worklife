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
	private Long id;

	@JoinColumn(name = "request_user_id")
	@ManyToOne(targetEntity = User.class)
	private User userRequested;

	@JoinColumn(name = "target_user_id")
	@ManyToOne(targetEntity = User.class)
	private User userTarget;

	@Column(name = "date_of_request")
	private Date dateOfRequest;

	@Column(name = "completed")
	private boolean completed;
}

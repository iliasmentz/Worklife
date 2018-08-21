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
@Table(name = "notification")
@Entity
@DynamicUpdate
public class Notification implements Serializable {
	@Id
	@Column(name = "notification_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@Column(name = "user_id")
	private Long userId;

	@NotNull
	@Column(name = "status")
	private short status = 0; //default 0

	@NotNull
	@Column(name = "message")
	private String message;
}

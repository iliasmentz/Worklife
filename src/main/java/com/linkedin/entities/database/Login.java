package com.linkedin.entities.database;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.linkedin.constants.RoleName;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.NaturalId;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@NoArgsConstructor
@Table(name = "login")
@Entity
@DynamicUpdate
public class Login implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "id")
	private Long userId;

	@NaturalId
	@Column(name = "email", nullable = false)
	private String email;

	@JsonIgnore
	@Column(name = "password", nullable = false)
	private String password;

	@Enumerated(EnumType.STRING)
	@Column(name = "role", nullable = false)
	private RoleName role;

	@Column(name = "is_active")
	private boolean active;

	public Login(String email, String password) {
		this.email = email;
		this.password = password;
		this.active = true;
	}
}

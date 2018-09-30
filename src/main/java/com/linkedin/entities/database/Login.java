package com.linkedin.entities.database;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.linkedin.constants.Role;
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
	@Column(name = "username", nullable = false)
	private String username;

	@JsonIgnore
	@Column(name = "password", nullable = false)
	private String password;

	@Enumerated(EnumType.ORDINAL)
	@Column(name = "role", nullable = false)
	private Role role;

	@Column(name = "is_active")
	private Boolean active;

	public Login(String username, String password) {
		this.username = username;
		this.password = password;
		this.active = true;
	}
}

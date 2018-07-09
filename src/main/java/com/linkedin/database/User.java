package com.linkedin.database;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

import static javax.persistence.TemporalType.DATE;

@Data
@Table(name = "users")
@Entity
@DynamicUpdate

public class User implements Serializable {
	private static final long serialVersionUID = 3097430508040456243L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column (name="user_id")
	private Long user_id;

	@NotNull
	@Column(name= "name")
	private String name;


	@NotNull
	@Column(name="surname")
	private String username;

	@NotNull
	@Column(name="email")
	private String email;

	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(name="role")
	private Role role;


	@NotNull
	@Temporal(DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Column(name= "birthdate")
	private Date birthdate;


	@NotNull
	@Column(name="address")
	private String address;


	@NotNull
	@Column(name="phone_number")
	private String phone_number;

	@NotNull
	@Column(name="img_path")
	private String img_path;


	public void setId(Long id) {
		this.user_id = id;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Long getUserId() {

		return user_id;
	}



	public String getUsername() {
		return username;
	}

	public String getEmail() {
		return email;
	}

	public Role getRole() {
		return role;
	}

	public User (){};

	public User( String email,
				 String username,

				 String password
	){

		this.email = email;
		this.username = username;
		this.role = Role.USER;

	}

	public void printUser(){
		System.out.println("\n\n");
		System.out.println("\n\n");
		System.out.println(this.email);
		System.out.println("\n\n");
	}

}

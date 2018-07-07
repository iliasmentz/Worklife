package com.linkedin.database;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Table(name = "users")
@Entity
public class User implements Serializable {
	private static final long serialVersionUID = 3097430508040456243L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@Column(name="password")
	private String password;
	@Column(name="username")
	private String username;
	@Column(name="email")
	private String email;
	@Column(name="role")
	private String role;


	public User (){};

	public User( String email,
				 String username,

				 String password
	){

		this.email = email;
		this.username = username;
		this.password = password;
		this.role = "USER";

	}

	public void printUser(){
		System.out.println("\n\n");
		System.out.println("\n\n");
		System.out.println(this.email);
		System.out.println("\n\n");
	}
	public void setEmail(String email){
		this.email = email;
	}
	public void setPassword(String password){
		this.password = password;
	}
}

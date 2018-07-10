package com.linkedin.database;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Table(name = "login")
@Entity
@DynamicUpdate

public class Login implements Serializable {

    @Id
    @Column(name="email",nullable = false)
    private String  email;

    @Column(name = "password",nullable = false)
    private String password;

    @Column(name= "user_id",nullable = false)
    private Long user_id;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name="role")
    private Role role;



}

package com.linkedin.entities.database;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;


@Data
@Table(name = "notification")
@Entity
@DynamicUpdate

public class Notification  implements Serializable{
    @Id
    @Column(name="notification_id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long notification_id ;

    @NotNull
    @Column(name="user_id")
    private Long user_id ;

    @NotNull
    @Column(name="status")
    private short status = 0 ; //default 0



    @NotNull
    @Column (name="message")
    private String message;


    public void setNotification_id(Long notification_id) {
        this.notification_id = notification_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public void setStatus(short status) {
        this.status = status;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getNotification_id() {

        return notification_id;
    }

    public Long getUser_id() {
        return user_id;
    }

    public short getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }




}

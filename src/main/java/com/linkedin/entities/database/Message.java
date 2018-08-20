package com.linkedin.database;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.Date;

import static javax.persistence.TemporalType.DATE;

@Data
@Table(name = "message")
@Entity
@DynamicUpdate


public class Message  implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="message_id")

    private Long message_id;
    @Column(name= "conversation_id" , nullable = false)
    private Long conversation_id;



    @Column(name= "sender_id")
    private Long sender_id;


    @NotNull
    @Temporal(DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Column(name= "sent_date")
    private Date sent_date;

    @NotNull
    @Column(name="context")
    private String context;

    public void setMessage_id(Long message_id) {
        this.message_id = message_id;
    }

    public void setConversation_id(Long message_id) {
        this.message_id = message_id;
    }



    public void setSent_date(Date sent_date) {
        this.sent_date = sent_date;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public Long getMessage_id() {

        return message_id;
    }

    public Long getConversation_id() {
        return message_id;
    }


    public Date getSent_date() {
        return sent_date;
    }

    public String getContext() {
        return context;
    }
}

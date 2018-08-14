package com.linkedin.entities.database;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

import static javax.persistence.TemporalType.DATE;

@Data
@Table(name = "message")
@Entity
@DynamicUpdate
public class Message implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "message_id")
	private Long message_id;

//    @OneToOne
//    @JoinColumn(name = "conversation_id", nullable = false)
//    @OnDelete(action = OnDeleteAction.CASCADE)
//    @Column(name= "conversation_id")
//    private Long conversation_id;

//
//    @OneToOne
//    @JoinColumn(name = "User.id", nullable = false)
//    @OnDelete(action = OnDeleteAction.CASCADE)
//    @Column(name= "sender_id")
//    private Long sender_id;

	@NotNull
	@Temporal(DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Column(name = "sent_date")
	private Date sentDate;

	@NotNull
	@Column(name = "context")
	private String context;
}

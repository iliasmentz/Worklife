package com.linkedin.entities.database;

import com.linkedin.constants.Visible;
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
@Table(name = "post")
@Entity
@DynamicUpdate
public class Post implements Serializable {
	@Id
	@Column(name = "post_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long postId;

	@Column(name = "creator_id", nullable = false)
	private Long creatorId;

	@NotNull
	@Temporal(DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Column(name = "post_date")
	private Date postDate;

	@Column(columnDefinition = "text", name = "context", nullable = false)
	private String context;

	//Todo na to doume
	//@NotNull
	@Column(name = "visible")
	private Visible visible;

}

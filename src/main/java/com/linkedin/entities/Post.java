package com.linkedin.entities;

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
import javax.validation.constraints.NotBlank;
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

  @NotNull
  @Column(name = "creator_id")
  private Long creatorId;

  @NotNull
  @Temporal(DATE)
  @DateTimeFormat(pattern = "dd/MM/yyyy")
  @Column(name = "post_date")
  private Date postDate;

  @NotBlank
  @Column(columnDefinition = "text", name = "context")
  private String context;

  @NotNull
  @Column(name = "number_of_likes")
  private Long numberOfLikes;

  @Column(name = "visible")
  private Integer visible;

  @Column(name = "image_path")
  private String imagePath;

  @Column(name = "file_type")
  private String fileType;

}

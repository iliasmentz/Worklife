package com.linkedin.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@Table(name = "Comment")
@Entity
@DynamicUpdate
public class Comment implements Serializable {

  @Id
  @Column(name = "comment_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long commentId;

  @NotNull
  @Column(name = "commenter_id")
  private Long commenterId;

  @NotNull
  @Column(name = "post_id")
  private Long postId;

  @NotBlank
  @Column(name = "context")
  private String context;

  @NotNull
  @Column(name = "commentDate")
  private Date commentDate;
}

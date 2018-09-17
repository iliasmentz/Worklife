package com.linkedin.entities.database;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Table(name = "like_post")
@Entity
@DynamicUpdate
public class Like implements Serializable {

  @Id
  @Column(name = "like_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long likeId;

  @Column(name = "user_id")
  private Long userId;

  @Column(name = "post_id")
  private Long postId;

}

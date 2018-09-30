package com.linkedin.entities.database;

import com.linkedin.entities.model.UserSimpleDto;
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
@Table(name = "notification")
@Entity
@DynamicUpdate
public class Notification implements Serializable {
  @Id
  @Column(name = "notification_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long notificationId;

  @Column(name = "userId")
  private Long userId;

  @Column(name = "target_user_id") //o user gia ton opoio anaferetai to notification
  private Long targetUserId;//connectionRequest se poion ? h like se poion h comment se poion

  @Column(name = "status")
  private Integer status; //0 == seen , 1 == not seen yet

  @Column(name = "message")
  private String message;

  @Column(name = "type") //0->like , 1->comment ,2->connectionRequest
  private Integer type;

  @Column(name= "like_comment_connection_id")
  private Long likeCommentConnectionId ;

}

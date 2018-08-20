package com.linkedin.entities.database;


import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
@Data
@Table(name = "conversation")
@Entity
@DynamicUpdate


public class  Conversation implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column (name="conversation_id")
    private Long conversation_id;

    @NotNull
    @Column (name="user_id_1")
    private Long user_id_1;


    @NotNull
    @Column (name="user_id_2")
    private Long user_id_2;


}

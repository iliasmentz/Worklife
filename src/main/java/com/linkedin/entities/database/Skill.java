package com.linkedin.entities.database;


import com.linkedin.constants.SkillLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Table(name = "skill")
@Entity
@DynamicUpdate
public class Skill {
  @Id
  @Column(name = "skill_id")
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long skillId;

  @Column(name = "user_id", nullable = false)
  private Long userId;

  @Column(name = "name", nullable = false)
  private String name;

  @Column(name = "level", nullable = false)
  private SkillLevel level;


}

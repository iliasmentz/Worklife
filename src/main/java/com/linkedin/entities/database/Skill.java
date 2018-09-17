package com.linkedin.entities.database;

import lombok.Data;
import lombok.NoArgsConstructor;
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
@NoArgsConstructor
@Table(name = "skill")
@Entity
@DynamicUpdate
public class Skill implements Serializable {
	@Id
	@Column(name = "skill_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long skillId;
	@NotNull
	@Column(name = "user_id", nullable = false)
	private Long userId;

	@NotNull
	@Column(name = "name", nullable = false)
	private String name;

	@NotNull
	@Column(name = "level", nullable = false)
	private Integer level;

	@NotNull
	@Column(name = "visible")
	private Integer visible;


}

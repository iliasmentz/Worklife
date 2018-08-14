package com.linkedin.entities.database;

import com.linkedin.constants.RoleName;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "roles")
public class Role {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "role_id")
	private Long id;

	@Enumerated(EnumType.STRING)
	@NaturalId
	@Column(name = "name", length = 60)
	private RoleName name;

	public Role(RoleName name) {
		this.name = name;
	}
}
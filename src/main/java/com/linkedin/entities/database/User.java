package com.linkedin.entities.database;

import com.linkedin.entities.model.recommendation.UserInfoBo;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

import static javax.persistence.TemporalType.DATE;


//@SqlResultSetMappings({
//		@SqlResultSetMapping(name = "getUsersVectorMap",
//				columns = {
//						@ColumnResult(name = "EMPID", type = Long.class),
//						@ColumnResult(name = "CODE", type = String.class),
//						@ColumnResult(name = "TOTALCOUNT", type = Integer.class)
//				})
//}
//
@SqlResultSetMapping(
		name = "getUsersVectorMap",
		classes = {
				@ConstructorResult(
						targetClass = UserInfoBo.class,
						columns = {
								@ColumnResult(name = "userId", type = Long.class),
								@ColumnResult(name = "postId", type = Long.class),
//								@ColumnResult(name = "dateOfPost", type = String.class),
								@ColumnResult(name = "postCreatorId", type = Long.class),
								@ColumnResult(name = "numberOfLikes", type = Long.class),
								@ColumnResult(name = "numberOfComments", type = Long.class),
								@ColumnResult(name = "isUserAndPosterFriends", type = Long.class)
						}
				)
		}
)

@NamedNativeQuery(name = "getUsersVectorQuery"
		, query = "select u.user_id as userId" +
		", p.post_id as postId" +
//		", p.post_date as dateOfPost" +
		", p.creator_id as postCreatorId" +
		", count(l) as numberOfLikes" +
		", count(comm) as numberOfComments" +
		", (select count(conn) from connection conn where (conn.user_accepted_id = u.user_id and conn.user_requested_id = p.creator_id) or (conn.user_accepted_id = p.creator_id and conn.user_requested_id = u.user_id) ) as isUserAndPosterFriends " +

		" from users u, " +
		"     ((Post p inner join like_post l on l.post_id = p.post_id) " +
		"         inner join comment comm on p.post_id = comm.post_id " +
		"         ) " +

		"where l.user_id = u.user_id " +
		"  and comm.commenter_id = u.user_id " +
		"group by u.user_id, p.post_id ",
		resultSetMapping = "getUsersVectorMap"
)


@Data
@NoArgsConstructor
@Table(name = "users")
@Entity
@DynamicUpdate
public class User implements Serializable {
	private static final long serialVersionUID = 3097430508040456243L;

	@Id
	@Column(name = "user_id")
	private Long id;

	@NotNull
	@Column(name = "name")
	private String name;

	@NotNull
	@Column(name = "surname")
	private String surname;

	@NotNull
	@Column(name = "username")
	private String username;

	@NotNull
	@Column(name = "email", nullable = false)
	private String email;

	@NotNull
	@Temporal(DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Column(name = "birthdate")
	private Date birthdate;

	@NotNull
	@Temporal(DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Column(name = "date_created")
	private Date dateCreated;

	@Column(name = "address")
	private String address;

	@Column(name = "phone_number")
	private String phoneNumber;

	/**
	 * Image path is just a string with image file name
	 * For example user-1312-my_photo.png, 
	 * without '/'
	 */
	@Column(name = "img_path")
	private String imgPath;
}

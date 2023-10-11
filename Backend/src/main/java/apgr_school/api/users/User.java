package apgr_school.api.users;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.sql.Date;

@Entity(name = "users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id")
	private Long id;
	@Column(name = "Name")
	@NotNull
	private String name;
	@Column(name = "DateOfBirth")
	@NotNull
	private Date date_of_birth;
	@Column(name = "Email")
	@NotNull
	private String email;
	@Column(name = "Cellphone")
	@NotNull
	private String cellphone;
	@Column(name = "Password")
	@NotNull
	private String password;
	@Column(name = "Gender")
	@NotNull
	private String gender;
	@Column(name = "PhotoPath")
	@Nullable
	private String photo_path;
}

package apgr_school.api.users;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Entity
@Table(name = "Users")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NotNull
	private Long id;
	@NotNull
	private String name;
	@NotNull
	private Date date_of_birth;
	@NotNull
	private String email;
	@NotNull
	private String cellphone;
	@NotNull
	private String password;
	@Nullable
	@Enumerated(EnumType.STRING)
	private EnumGender gender;
	@Nullable
	private String photo_path;

	public User(UserRegisterData user_data){
		id = user_data.id();
		name = user_data.name();
		date_of_birth = user_data.date_of_birth();
		email = user_data.email();
		cellphone = user_data.cellphone();
		password = user_data.password();
		gender = user_data.gender();
		photo_path = user_data.PhotoPath();
	}

}

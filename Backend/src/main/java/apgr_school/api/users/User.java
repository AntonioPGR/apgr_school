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
	private Long id;
	private String name;
	private Date date_of_birth;
	private String email;
	private String cellphone;
	private String password;
	@Enumerated(EnumType.STRING)
	private EnumGender gender;
	private String photo_path;
	public User(UserRegisterDTO user_data){
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

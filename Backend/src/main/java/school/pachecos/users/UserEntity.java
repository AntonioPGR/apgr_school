package school.pachecos.users;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import school.pachecos.users.dtos.UserCreateDTO;
import school.pachecos.users.dtos.UserUpdateDTO;

import java.time.LocalDate;

@Table(name = "Users")
@Entity(name = "UserEntity")
@Getter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class UserEntity {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@NotBlank
	private String name;
	@Past
	private LocalDate birth_date;
	@Email
	private String email;
	@NotBlank
	@Pattern(regexp = "[+][\\d]{2,3}[(][\\d]{2,3}[)][\\d]{9}")
	private String cellphone;
	@NotBlank
	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*\\W).{8,}$")
	private String password;
	private String gender;
	@Nullable
	private String photo_path;
	private boolean active;
	private String permissions;

	public UserEntity(
			String name,
			LocalDate birth_date,
			String email,
			String cellphone,
			String password,
			String gender,
			@Nullable String photo_path
	) {
		this.name = name;
		this.birth_date = birth_date;
		this.email = email;
		this.cellphone = cellphone;
		this.password = password;
		this.gender = gender;
		this.photo_path = photo_path;
		this.active = true;
	}

	public UserEntity(UserCreateDTO user_info) {
		this.name = user_info.name();
		this.birth_date = user_info.birth_date();
		this.email = user_info.email();
		this.cellphone = user_info.cellphone();
		this.password = user_info.password();
		this.gender = user_info.gender();
		this.photo_path = user_info.photo_path();
		this.active = true;
	}

	public void update(UserUpdateDTO user_info) {
		name = user_info.name() != null ? user_info.name() : name;
		birth_date = user_info.birth_date() != null ? user_info.birth_date() : birth_date;
		email = user_info.email() != null ? user_info.email() : email;
		cellphone = user_info.cellphone() != null ? user_info.cellphone() : cellphone;
		password = user_info.password() != null ? user_info.password() : password;
		gender = user_info.gender() != null ? user_info.gender() : gender;
		photo_path = user_info.photo_path() != null ? user_info.photo_path() : photo_path;
	}

	public void desactive(){
		active = false;
	}

	public void active(){
		active = true;
	}

}

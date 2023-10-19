package apgr_school.api.users;

import apgr_school.api.users.DTOs.UserRegisterDTO;
import apgr_school.api.users.DTOs.UserUpdateDTO;
import jakarta.persistence.*;
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
	private Boolean active;

	public User(UserRegisterDTO user_data){
		id = user_data.id();
		name = user_data.name();
		date_of_birth = user_data.date_of_birth();
		email = user_data.email();
		cellphone = user_data.cellphone();
		password = user_data.password();
		gender = user_data.gender();
		photo_path = user_data.photo_path();
	}

	public void updateInformation(UserUpdateDTO user_data){
		name = user_data.name() != null? user_data.name() : name;
		email = user_data.email() != null? user_data.email() : email;
		cellphone = user_data.cellphone() != null? user_data.cellphone() : cellphone;
		password = user_data.password() != null? user_data.password() : password;
		photo_path = user_data.photo_path() != null? user_data.photo_path() : photo_path;
	}

	public void exclude() {
		active = false;
	}
}

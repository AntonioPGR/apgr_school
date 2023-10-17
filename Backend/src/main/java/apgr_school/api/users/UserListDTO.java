package apgr_school.api.users;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.sql.Date;

public record UserListDTO(
	@NotNull
	Long id,
	@NotBlank
	String name,
	@NotNull
	Date date_of_birth,
	@NotNull
	EnumGender gender,
	String photo_path
){

	public UserListDTO(User user){
		this(user.getId(), user.getName(), user.getDate_of_birth(), user.getGender(), user.getPhoto_path());
	}

}
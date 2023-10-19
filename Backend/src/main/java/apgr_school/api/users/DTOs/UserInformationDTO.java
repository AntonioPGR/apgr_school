package apgr_school.api.users.DTOs;

import apgr_school.api.users.EnumGender;
import apgr_school.api.users.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.sql.Date;

public record UserInformationDTO(
	@NotNull
	Long id,
	@NotBlank
	String name,
	@NotBlank
	@Email
	String email,
	@NotBlank
	@Pattern(regexp = "\\+\\d{1,3}\\(\\d{1,4}\\)\\d{6,10}|0\\d{9,10}")
	String cellphone,
	@NotNull
	Date date_of_birth,
	@NotNull
	EnumGender gender,
	String photo_path
){

	public UserInformationDTO(User user){
		this(user.getId(), user.getName(), user.getEmail(), user.getCellphone(), user.getDate_of_birth(), user.getGender(), user.getPhoto_path());
	}

}
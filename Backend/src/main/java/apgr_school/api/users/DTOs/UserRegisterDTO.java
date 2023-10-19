package apgr_school.api.users.DTOs;

import apgr_school.api.users.EnumGender;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.sql.Date;

public record UserRegisterDTO(
		long id,
		@NotBlank
		String name,
		@NotNull
		Date date_of_birth,
		@NotBlank
		@Email
		String email,
		@NotBlank
		@Pattern(regexp = "\\+\\d{1,3}\\(\\d{1,4}\\)\\d{6,10}|0\\d{9,10}")
		String cellphone,
		@NotBlank
		String password,
		@NotNull
		EnumGender gender,
		String photo_path
){}

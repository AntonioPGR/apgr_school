package apgr_school.api.models.users.DTOs;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record UserUpdateDTO(
		@NotNull
		long id,
		String name,
		@Email
		String email,
		@Pattern(regexp = "\\+\\d{1,3}\\(\\d{1,4}\\)\\d{6,10}|0\\d{9,10}")
		String cellphone,
		String password,
		String photo_path
){}

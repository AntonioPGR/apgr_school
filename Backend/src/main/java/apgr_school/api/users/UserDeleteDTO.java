package apgr_school.api.users;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.sql.Date;

public record UserDeleteDTO(
		@NotNull
		long id,
		@NotBlank
		String password
){}

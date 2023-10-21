package apgr_school.api.infra.authentication.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserAuthenticateDTO(
	@NotBlank
	@Email
	String email,
	@NotBlank
	String password
) {}

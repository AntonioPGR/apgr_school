package school.pachecos.api.authentication.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginDTO(
		@Email
		@NotBlank
		String email,
		@NotBlank
		String password
) {
}

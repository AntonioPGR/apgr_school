package school.pachecos.api.authentication.dtos;

import jakarta.validation.constraints.NotBlank;

public record LoginResponseDTO(
		@NotBlank
		String token
) {
}

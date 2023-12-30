package school.pachecos.infra.commons.dtos;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record IdDTO(
		@NotNull UUID id) {
}

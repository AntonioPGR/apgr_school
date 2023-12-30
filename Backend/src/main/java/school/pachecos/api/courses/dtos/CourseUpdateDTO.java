package school.pachecos.api.courses.dtos;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import school.pachecos.infra.commons.interfaces.BaseUpdateDTO;

import java.util.UUID;

public record CourseUpdateDTO(
		@NotNull UUID id,
		@Nullable String name,
		@Nullable String description) implements BaseUpdateDTO {
	@Override
	public UUID id() {
		return id;
	}
}

package school.pachecos.api.courses.dtos;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import school.pachecos.infra.commons.interfaces.BaseUpdateDTO;

public record CourseUpdateDTO(
		@NotNull Long id,
		@Nullable String name,
		@Nullable String description
) implements BaseUpdateDTO {
	@Override
	public Long id() {
		return id;
	}
}

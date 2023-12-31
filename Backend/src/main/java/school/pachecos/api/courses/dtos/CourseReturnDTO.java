package school.pachecos.api.courses.dtos;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import school.pachecos.api.courses.CourseEntity;
import school.pachecos.infra.commons.interfaces.BaseReturnDTO;

import java.util.UUID;

public record CourseReturnDTO(
		@NotNull Long id,
		@NotNull @NotBlank String name,
		@Nullable String description) implements BaseReturnDTO {

	public CourseReturnDTO(CourseEntity entity) {
		this(entity.getId(), entity.getName(), entity.getDescription());
	}

}

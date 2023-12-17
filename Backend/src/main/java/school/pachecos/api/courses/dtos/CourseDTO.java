package school.pachecos.api.courses.dtos;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import school.pachecos.api.courses.CourseEntity;

public record CourseDTO(
		@NotNull Long id,
		@NotNull @NotBlank String name,
		@Nullable String description
) {

	public CourseDTO(CourseEntity entity){
		this(entity.getId(), entity.getName(), entity.getDescription());
	}

}

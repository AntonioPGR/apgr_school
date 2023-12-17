package school.pachecos.api.courses.dtos;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import school.pachecos.api.courses.CourseEntity;

public record CourseNoIdDTO(
		@NotNull @NotBlank String name,
		@Nullable String description
)
{

	public CourseNoIdDTO(CourseEntity entity){
		this(entity.getName(), entity.getDescription());
	}

}

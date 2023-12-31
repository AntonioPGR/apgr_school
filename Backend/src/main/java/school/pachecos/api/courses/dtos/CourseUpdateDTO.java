package school.pachecos.api.courses.dtos;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;

public record CourseUpdateDTO(
		@Nullable String name,
		@Nullable String description)
{
}

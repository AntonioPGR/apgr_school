package school.pachecos.api.courses.dtos;

import jakarta.annotation.Nullable;

public record CourseUpdateDTO(
		@Nullable String name,
		@Nullable String description)
{
}

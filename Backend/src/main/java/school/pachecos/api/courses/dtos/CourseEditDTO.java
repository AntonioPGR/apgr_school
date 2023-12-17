package school.pachecos.api.courses.dtos;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CourseEditDTO(
		@NotNull Long id,
		@Nullable String name,
		@Nullable String description
)
{}

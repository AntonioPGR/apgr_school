package school.pachecos.api.tasks.dtos;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;


public record TaskCreateDTO(
		@NotNull String title,
		@NotNull LocalDateTime dueDate,
		@Nullable String description,
		@NotNull long lesson_id
)
{}

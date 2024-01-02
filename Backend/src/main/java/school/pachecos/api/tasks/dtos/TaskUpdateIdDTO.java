package school.pachecos.api.tasks.dtos;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record TaskUpdateIdDTO(
		@Nullable String title,
		@Nullable LocalDateTime dueDate,
		@Nullable String description,
		@Nullable Long lesson_id) {
}

package school.pachecos.api.tasks.dtos;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.UUID;

public record TaskUpdateIdDTO(
		@NotNull UUID id,
		@Nullable String title,
		@Nullable LocalDateTime dueDate,
		@Nullable String description,
		@Nullable UUID lesson_id) {
}

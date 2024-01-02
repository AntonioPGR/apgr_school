package school.pachecos.api.tasks.dtos;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

public record TaskCreateIdDTO(
		@NotNull String title,
		@NotNull LocalDateTime due_date,
		@Nullable String description,
		@NotNull Long lesson_id
) {
}

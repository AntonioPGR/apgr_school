package school.pachecos.api.lessons.dto;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record LessonUpdateIdDTO(
		@Nullable String name,
		@Nullable LocalDateTime datetime,
		@Nullable Long professor_id,
		int duration_in_minutes) {
}

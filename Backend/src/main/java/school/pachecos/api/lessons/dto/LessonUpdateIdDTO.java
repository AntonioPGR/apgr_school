package school.pachecos.api.lessons.dto;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import school.pachecos.api.lessons.LessonEntity;

import java.time.LocalDateTime;
import java.util.UUID;

public record LessonUpdateIdDTO(
		@NotNull UUID id,
		@Nullable String name,
		@Nullable LocalDateTime datetime,
		@Nullable UUID professor_id,
		@Nullable int duration_in_minutes) {
	public LessonUpdateIdDTO(LessonEntity entity) {
		this(entity.getId(), entity.getName(), entity.getDatetime(), entity.getProfessor().getId(),
				entity.getDuration_in_minutes());
	}

}

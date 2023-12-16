package school.pachecos.api.lesson.dto;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import school.pachecos.api.lesson.LessonEntity;

import java.time.LocalDateTime;

public record LessonUpdateIdDTO(
		@NotNull Long id,
		@Nullable String name,
		@Nullable LocalDateTime datetime,
		@Nullable Long professor_id,
		@Nullable int duration_in_minutes
) {
	public LessonUpdateIdDTO(LessonEntity entity){
		this(entity.getId(), entity.getName(), entity.getDatetime(), entity.getProfessor().getId(), entity.getDuration_in_minutes());
	}

}

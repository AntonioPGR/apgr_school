package school.pachecos.api.lesson.dto;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import school.pachecos.api.lesson.LessonEntity;

import java.time.LocalDateTime;

public record LessonUpdateIdDTO(
		@NotNull long id,
		@Nullable @NotBlank String name,
		@Nullable LocalDateTime datetime,
		@Nullable long professor_id) {

	public LessonUpdateIdDTO(LessonEntity entity){
		this(entity.getId(), entity.getName(), entity.getDatetime(), entity.getProfessor().getId());
	}

}

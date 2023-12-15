package school.pachecos.api.lesson.dto;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import school.pachecos.api.lesson.LessonEntity;
import school.pachecos.api.users.UserEntity;

import java.time.LocalDateTime;

public record LessonUpdateProfessorDTO(
		@NotNull long id,
		@Nullable @NotBlank String name,
		@Nullable LocalDateTime datetime,
		@Nullable UserEntity professor) {

	public LessonUpdateProfessorDTO(LessonEntity entity){
		this(entity.getId(), entity.getName(), entity.getDatetime(), entity.getProfessor());
	}

}

package school.pachecos.api.lessons.dto;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import school.pachecos.api.lessons.LessonEntity;
import school.pachecos.api.users.UserEntity;

import java.time.LocalDateTime;

public record LessonUpdateProfessorDTO(
		@NotNull long id,
		@Nullable @NotBlank String name,
		@Nullable LocalDateTime datetime,
		@Nullable UserEntity professor,
		@Nullable int duration_in_minutes)
{
	public LessonUpdateProfessorDTO(LessonEntity entity){
		this(entity.getId(), entity.getName(), entity.getDatetime(), entity.getProfessor(), entity.getDuration_in_minutes());
	}

}

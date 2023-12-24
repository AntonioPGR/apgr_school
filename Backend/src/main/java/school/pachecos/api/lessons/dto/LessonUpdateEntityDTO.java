package school.pachecos.api.lessons.dto;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import school.pachecos.api.users.UserEntity;
import school.pachecos.infra.commons.interfaces.BaseUpdateDTO;

import java.time.LocalDateTime;

public record LessonUpdateEntityDTO(
		@NotNull long id,
		@Nullable @NotBlank String name,
		@Nullable LocalDateTime datetime,
		@Nullable UserEntity professor,
		@Nullable int duration_in_minutes) implements BaseUpdateDTO {

	public LessonUpdateEntityDTO(LessonUpdateIdDTO lesson_dto, UserEntity professor){
		this(lesson_dto.id(), lesson_dto.name(), lesson_dto.datetime(), professor, lesson_dto.duration_in_minutes());
	}

	@Override
	public Long id() {
		return id;
	}
}

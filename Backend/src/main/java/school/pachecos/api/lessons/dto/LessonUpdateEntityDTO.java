package school.pachecos.api.lessons.dto;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import school.pachecos.api.users.UserEntity;
import java.time.LocalDateTime;

public record LessonUpdateEntityDTO(
		@Nullable @NotBlank String name,
		@Nullable LocalDateTime datetime,
		@Nullable UserEntity professor,
		@Nullable int duration_in_minutes) {

	public LessonUpdateEntityDTO(LessonUpdateIdDTO lesson_dto, UserEntity professor) {
		this( lesson_dto.name(), lesson_dto.datetime(), professor, lesson_dto.duration_in_minutes());
	}

}

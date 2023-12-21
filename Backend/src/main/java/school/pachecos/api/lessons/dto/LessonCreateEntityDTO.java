package school.pachecos.api.lessons.dto;

import school.pachecos.api.users.UserEntity;

import java.time.LocalDateTime;

public record LessonCreateEntityDTO(String name, LocalDateTime datetime, UserEntity professor, int duration_in_minutes) {

	public LessonCreateEntityDTO(LessonCreateIdDTO lesson_dto, UserEntity professor){
		this(lesson_dto.name(), lesson_dto.datetime(), professor, lesson_dto.duration_in_minutes());
	}

}

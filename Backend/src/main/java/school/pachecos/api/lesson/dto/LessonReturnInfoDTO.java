package school.pachecos.api.lesson.dto;

import school.pachecos.api.lesson.LessonEntity;
import school.pachecos.api.users.UserEntity;

import java.time.LocalDateTime;

public record LessonReturnInfoDTO(long id, String name, LocalDateTime datetime, UserEntity professor) {

	public LessonReturnInfoDTO(LessonEntity entity){
		this(entity.getId(), entity.getName(), entity.getDatetime(), entity.getProfessor());
	}

}

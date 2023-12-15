package school.pachecos.api.lesson.dto;

import school.pachecos.api.lesson.LessonEntity;

import java.time.LocalDateTime;

public record LessonCreateIdDTO(String name, LocalDateTime datetime, long professor_id) {

	public LessonCreateIdDTO(LessonEntity entity){
		this(entity.getName(), entity.getDatetime(), entity.getProfessor().getId());
	}

}

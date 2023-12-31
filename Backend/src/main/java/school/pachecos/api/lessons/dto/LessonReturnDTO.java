package school.pachecos.api.lessons.dto;

import school.pachecos.api.lessons.LessonEntity;
import school.pachecos.api.users.dtos.UserReturnDTO;
import school.pachecos.infra.commons.interfaces.BaseReturnDTO;

import java.time.LocalDateTime;

public record LessonReturnDTO(Long id, String name, LocalDateTime datetime, UserReturnDTO professor,
		int duration_in_minutes) implements BaseReturnDTO {

	public LessonReturnDTO(LessonEntity entity) {
		this(entity.getId(), entity.getName(), entity.getDatetime(), new UserReturnDTO(entity.getProfessor()),
				entity.getDuration_in_minutes());
	}

}

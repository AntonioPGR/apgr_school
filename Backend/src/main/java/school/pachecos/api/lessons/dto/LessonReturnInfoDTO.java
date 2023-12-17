package school.pachecos.api.lessons.dto;

import school.pachecos.api.lessons.LessonEntity;
import school.pachecos.api.users.dtos.UserReturnInfoDTO;

import java.time.LocalDateTime;

public record LessonReturnInfoDTO(long id, String name, LocalDateTime datetime, UserReturnInfoDTO professor, int duration_in_minutes) {

	public LessonReturnInfoDTO(LessonEntity entity){
		this(entity.getId(), entity.getName(), entity.getDatetime(), new UserReturnInfoDTO(entity.getProfessor()), entity.getDuration_in_minutes());
	}

}

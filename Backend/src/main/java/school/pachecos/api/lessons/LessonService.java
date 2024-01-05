package school.pachecos.api.lessons;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import school.pachecos.api.lessons.dto.*;
import school.pachecos.api.users.UserEntity;
import school.pachecos.api.users.UserService;
import school.pachecos.infra.commons.classes.BaseService;


@Service
public class LessonService extends BaseService<
	LessonEntity,
	LessonCreateIdDTO,
	LessonCreateEntityDTO,
	LessonUpdateIdDTO,
	LessonUpdateEntityDTO,
	LessonReturnDTO,
	LessonRepository
> {

	@Autowired
	UserService user_service;

	@Override
	protected LessonCreateEntityDTO convertToCreateDTO(LessonCreateIdDTO dto) {
		UserEntity professor = user_service.getEntityById(dto.professor_id());
		return new LessonCreateEntityDTO(dto, professor);
	}

	@Override
	protected LessonUpdateEntityDTO convertToUpdateDTO(LessonUpdateIdDTO dto) {
		Long professor_id = dto.professor_id();
		UserEntity professor = professor_id != null? user_service.getEntityById(professor_id) : null;
		return new LessonUpdateEntityDTO(dto, professor);
	}

	@Override
	protected LessonReturnDTO convertToReturnDTO(LessonEntity lessonEntity) {
		return new LessonReturnDTO(lessonEntity);
	}

	@Override
	protected LessonEntity convertToEntity(LessonCreateEntityDTO create_dto) {
		return new LessonEntity(create_dto);
	}

}

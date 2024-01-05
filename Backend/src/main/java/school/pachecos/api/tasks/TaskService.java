package school.pachecos.api.tasks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import school.pachecos.api.lessons.LessonEntity;
import school.pachecos.api.lessons.LessonService;
import school.pachecos.api.tasks.dtos.*;
import school.pachecos.infra.commons.classes.BaseService;

@Service
class TaskService extends BaseService<
	TaskEntity,
	TaskCreateIdDTO,
	TaskCreateEntityDTO,
	TaskUpdateIdDTO,
	TaskUpdateEntityDTO,
	TaskReturnDTO,
	TaskRepository
> {

	@Autowired
	LessonService lesson_service;

	@Override
	protected TaskCreateEntityDTO convertToCreateDTO(TaskCreateIdDTO dto) {
		LessonEntity lesson = lesson_service.getEntityById(dto.lesson_id());
		return new TaskCreateEntityDTO(dto, lesson);
	}

	@Override
	protected TaskUpdateEntityDTO convertToUpdateDTO(TaskUpdateIdDTO dto) {
		LessonEntity lesson = dto.lesson_id() != null? getLesson(dto.lesson_id()) : null;
		return new TaskUpdateEntityDTO(dto, lesson);
	}

	@Override
	protected TaskReturnDTO convertToReturnDTO(TaskEntity dto) {
		return new TaskReturnDTO(dto);
	}

	@Override
	protected TaskEntity convertToEntity(TaskCreateEntityDTO dto) {
		return new TaskEntity(dto);
	}

	private LessonEntity getLesson(Long id){
		return lesson_service.getEntityById(id);
	}
}

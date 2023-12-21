package school.pachecos.api.tasks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import school.pachecos.api.lessons.LessonEntity;
import school.pachecos.api.lessons.LessonRepository;
import school.pachecos.api.tasks.dtos.*;
import school.pachecos.commons.classes.BaseApiService;


@Service
public class TaskService extends BaseApiService<TaskEntity, TaskCreateEntityDTO, TaskUpdateEntityDTO, TaskReturnDTO> {

	@Autowired
	private TaskRepository task_repository;
	@Autowired
	private LessonRepository lesson_repository;

	public TaskReturnDTO update(TaskCreateIdDTO task_dto) {
		LessonEntity lesson_entity = getLesson(task_dto.lesson_id());
		return this.create(new TaskCreateEntityDTO(task_dto, lesson_entity));
	}

	public TaskReturnDTO update(TaskUpdateIdDTO update_dto) {
		LessonEntity lesson_entity = update_dto.lesson_id() != null? getLesson(update_dto.lesson_id()) : null;
		return this.update(update_dto.id(), new TaskUpdateEntityDTO(update_dto, lesson_entity));
	}

	public LessonEntity getLesson(Long id){
		return lesson_repository.getReferenceById(id);
	}

	@Override
	protected TaskReturnDTO convertToReturnDTO(TaskEntity entity) {
		return new TaskReturnDTO(entity);
	}

	@Override
	protected TaskEntity convertToEntity(TaskCreateEntityDTO create_dto) {
		return new TaskEntity(create_dto);
	}

}

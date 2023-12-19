package school.pachecos.api.tasks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import school.pachecos.api.lessons.LessonEntity;
import school.pachecos.api.lessons.LessonRepository;
import school.pachecos.api.tasks.dtos.*;


@Service
public class TaskService {

	@Autowired
	private TaskRepository task_repository;
	@Autowired
	private LessonRepository lesson_repository;

	public Page<TaskReturnInfoDTO> listTasksPerPage(Pageable pageable){
		Page<TaskEntity> tasks_page = task_repository.findAll(pageable);
		return tasks_page.map(TaskReturnInfoDTO::new);
	}

	public TaskReturnInfoDTO findTaskById(Long id) {
		TaskEntity task_entity = task_repository.getReferenceById(id);
		return new TaskReturnInfoDTO(task_entity);
	}

	public TaskReturnInfoDTO createTask(TaskCreateWithIDsDTO new_task_ids) {
		LessonEntity lesson_entity = lesson_repository.getReferenceById(new_task_ids.lesson_id());
		TaskCreateWithEntitiesDTO new_task_entities = new TaskCreateWithEntitiesDTO(new_task_ids, lesson_entity);
		TaskEntity task_entity = new TaskEntity(new_task_entities);
		task_repository.save(task_entity);
		return new TaskReturnInfoDTO(task_entity);
	}

	public TaskReturnInfoDTO editTask(TaskEditWithIDsDTO edited_task_ids) {
		LessonEntity lesson_entity = edited_task_ids.lesson_id() != null? lesson_repository.getReferenceById(edited_task_ids.lesson_id()) : null;
		TaskEditWithEntitiesDTO edited_task_entities = new TaskEditWithEntitiesDTO(edited_task_ids, lesson_entity);
		TaskEntity task_entity = task_repository.getReferenceById(edited_task_ids.id());
		task_entity.update(edited_task_entities);
		return new TaskReturnInfoDTO(task_entity);
	}

	public void deleteTask(Long id) {
		TaskEntity task_entity = task_repository.getReferenceById(id);
		task_repository.delete(task_entity);
	}

}

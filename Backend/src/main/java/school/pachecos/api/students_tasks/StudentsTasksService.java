package school.pachecos.api.students_tasks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import school.pachecos.api.students_tasks.dtos.*;
import school.pachecos.api.tasks.TaskEntity;
import school.pachecos.api.tasks.TaskRepository;
import school.pachecos.api.users.UserEntity;
import school.pachecos.api.users.UserRepository;


public class StudentsTasksService {

	@Autowired
	StudentsTasksRepository students_tasks_repository;
	@Autowired
	TaskRepository task_repository;
	@Autowired
	UserRepository user_repository;

	public Page<StudentsTasksReturnDTO> listStudentsTasksPerPage(Pageable pageable){
		Page<StudentsTasksEntity> students_tasks_page = students_tasks_repository.findAll(pageable);
		return students_tasks_page.map(StudentsTasksReturnDTO::new);
	}

	public StudentsTasksReturnDTO getStudentsTasksById(Long id) {
		StudentsTasksEntity students_tasks_entity = students_tasks_repository.getReferenceById(id);
		return new StudentsTasksReturnDTO(students_tasks_entity);
	}

	public StudentsTasksReturnDTO create(StudentsTasksCreateWithIdsDTO students_tasks_id) {
		TaskEntity task = task_repository.getReferenceById(students_tasks_id.task_id());
		UserEntity user = user_repository.getReferenceById(students_tasks_id.student_id());
		StudentsTasksCreateWithEntitiesDTO students_tasks_new_dto = new StudentsTasksCreateWithEntitiesDTO(students_tasks_id, task, user);
		StudentsTasksEntity students_task = new StudentsTasksEntity(students_tasks_new_dto);
		students_tasks_repository.save(students_task);
		return new StudentsTasksReturnDTO(students_task);
	}

	public StudentsTasksReturnDTO update(StudentsTasksUpdateWithIdsDTO update_dto_ids) {
		TaskEntity task = null;
		UserEntity user = null;
		if(update_dto_ids.task_id() != null){
			task = task_repository.getReferenceById(update_dto_ids.task_id());
		}
		if(update_dto_ids.student_id() != null){
			user = user_repository.getReferenceById(update_dto_ids.student_id());
		}
		StudentsTasksUpdateWithEntitiesDTO students_tasks_dto = new StudentsTasksUpdateWithEntitiesDTO(update_dto_ids, task, user);
		StudentsTasksEntity students_task = students_tasks_repository.getReferenceById(update_dto_ids.id());
		students_task.update(students_tasks_dto);
		return new StudentsTasksReturnDTO(students_task);
	}

	public void delete(long id) {
		StudentsTasksEntity students_tasks = students_tasks_repository.getReferenceById(id);
		students_tasks_repository.delete(students_tasks);
	}
}

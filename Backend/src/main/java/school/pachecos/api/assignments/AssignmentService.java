package school.pachecos.api.assignments;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import school.pachecos.api.assignments.dtos.*;
import school.pachecos.api.tasks.TaskEntity;
import school.pachecos.api.tasks.TaskRepository;
import school.pachecos.api.users.UserEntity;
import school.pachecos.api.users.UserRepository;
import school.pachecos.infra.commons.classes.BaseApiService;

@Service
public class AssignmentService extends BaseApiService<AssignmentEntity, AssignmentCreateEntityDTO, AssignmentUpdateEntityDTO, AssignmentReturnDTO> {

	@Autowired
	AssignmentRepository assignments_repository;
	@Autowired
	TaskRepository task_repository;
	@Autowired
	UserRepository user_repository;

	public AssignmentReturnDTO create(AssignmentCreateIdDTO assignment_dto) {
		TaskEntity task = task_repository.getReferenceById(assignment_dto.task_id());
		UserEntity user = user_repository.getReferenceById(assignment_dto.student_id());
		AssignmentCreateEntityDTO students_tasks_new_dto = new AssignmentCreateEntityDTO(assignment_dto, task, user);
		AssignmentEntity students_task = new AssignmentEntity(students_tasks_new_dto);
		assignments_repository.save(students_task);
		return new AssignmentReturnDTO(students_task);
	}

	public AssignmentReturnDTO update(AssignmentUpdateIdDTO update_dto) {
		TaskEntity task = update_dto.task_id() != null? getTask(update_dto.task_id()) : null;
		UserEntity user = update_dto.student_id() != null? getStudent(update_dto.student_id()) : null;;
		return this.update(new AssignmentUpdateEntityDTO(update_dto, task, user));
	}

	private TaskEntity getTask(Long id){
		return task_repository.getReferenceById(id);
	}
	private UserEntity getStudent(Long id){
		return user_repository.getReferenceById(id);
	}

	@Override
	protected AssignmentReturnDTO convertToReturnDTO(AssignmentEntity assignment) {
		return new AssignmentReturnDTO(assignment);
	}

	@Override
	protected AssignmentEntity convertToEntity(AssignmentCreateEntityDTO create_dto) {
		return new AssignmentEntity(create_dto);
	}

}

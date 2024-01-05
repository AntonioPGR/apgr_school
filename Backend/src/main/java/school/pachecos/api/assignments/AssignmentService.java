package school.pachecos.api.assignments;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import school.pachecos.api.assignments.dtos.*;
import school.pachecos.api.lessons.LessonEntity;
import school.pachecos.api.lessons.LessonRepository;
import school.pachecos.api.tasks.TaskEntity;
import school.pachecos.api.tasks.TaskRepository;
import school.pachecos.api.users.UserEntity;
import school.pachecos.api.users.UserRepository;
import school.pachecos.infra.commons.classes.BaseService;

import java.util.List;

@Service
public class AssignmentService extends BaseService<
	AssignmentEntity,
	AssignmentCreateIdDTO,
	AssignmentCreateEntityDTO,
	AssignmentUpdateIdDTO,
	AssignmentUpdateEntityDTO,
	AssignmentReturnDTO,
	AssignmentRepository
> {

	@Autowired
	TaskRepository task_repository;
	@Autowired
	UserRepository user_repository;
	@Autowired
	LessonRepository lesson_repository;

	public void createFromLesson(AssignmentCreateLessonIdDTO dto) {
		LessonEntity lesson = lesson_repository.getReferenceById(dto.lesson_id());
		List<UserEntity> users = lesson.getStudents();
		users.forEach(user -> {
			AssignmentCreateIdDTO create_dto = new AssignmentCreateIdDTO(0, dto.task_id(), user.getId(), null);
			super.create(create_dto);
		});
	}

	@Override
	protected AssignmentCreateEntityDTO convertToCreateDTO(AssignmentCreateIdDTO dto) {
		TaskEntity task = getTask(dto.task_id());
		UserEntity user = getStudent(dto.student_id());
		return new AssignmentCreateEntityDTO(dto, task, user);
	}

	@Override
	protected AssignmentUpdateEntityDTO convertToUpdateDTO(AssignmentUpdateIdDTO dto) {
		TaskEntity task = dto.task_id()!=null?getTask(dto.task_id()):null;
		UserEntity user = dto.student_id()!=null?getStudent(dto.student_id()):null;
		return new AssignmentUpdateEntityDTO(dto, task, user);
	}

	@Override
	protected AssignmentReturnDTO convertToReturnDTO(AssignmentEntity assignment) {
		return new AssignmentReturnDTO(assignment);
	}

	@Override
	protected AssignmentEntity convertToEntity(AssignmentCreateEntityDTO create_dto) {
		return new AssignmentEntity(create_dto);
	}

	private TaskEntity getTask(Long id) {
		return task_repository.getReferenceById(id);
	}

	private UserEntity getStudent(Long id) {
		return user_repository.getReferenceById(id);
	}

}

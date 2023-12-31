package school.pachecos.api.enrollments;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import school.pachecos.api.courses.CourseEntity;
import school.pachecos.api.courses.CourseRepository;
import school.pachecos.api.enrollments.dtos.*;
import school.pachecos.api.users.UserEntity;
import school.pachecos.api.users.UserRepository;
import school.pachecos.infra.commons.classes.BaseApiService;

@Service
public class EnrollmentService extends BaseApiService<EnrollmentEntity, EnrollmentCreateEntityDTO, EnrollmentUpdateEntityDTO, EnrollmentReturnDTO> {

	@Autowired
	EnrollmentRepository enrollment_repository;
	@Autowired
	CourseRepository course_repository;
	@Autowired
	UserRepository user_repository;

	public EnrollmentReturnDTO create(EnrollmentCreateIdDTO create_id_dto) {
		CourseEntity course = course_repository.getReferenceById(create_id_dto.course_id());
		UserEntity student = user_repository.getReferenceById(create_id_dto.student_id());

		return this.create(new EnrollmentCreateEntityDTO(course, student));
	}

	public EnrollmentReturnDTO update (EnrollmentUpdateIdDTO edit_id_dto) {
		CourseEntity course = edit_id_dto.course_id() != null? course_repository.getReferenceById(edit_id_dto.course_id()) : null;
		UserEntity student = edit_id_dto.student_id() != null? user_repository.getReferenceById(edit_id_dto.student_id()) : null;
		return super.update(edit_id_dto.course_id(), new EnrollmentUpdateEntityDTO(edit_id_dto, course, student));
	}

	@Override
	protected EnrollmentReturnDTO convertToReturnDTO(EnrollmentEntity entity) {
		return new EnrollmentReturnDTO(entity);
	}

	@Override
	protected EnrollmentEntity convertToEntity(EnrollmentCreateEntityDTO create_dto) {
		return new EnrollmentEntity(create_dto);
	}
}

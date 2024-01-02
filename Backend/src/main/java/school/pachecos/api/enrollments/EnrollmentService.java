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

	public EnrollmentReturnDTO create(EnrollmentCreateIdDTO dto) {
		CourseEntity course = course_repository.getReferenceById(dto.course_id());
		UserEntity student = user_repository.getReferenceById(dto.student_id());

		return this.create(new EnrollmentCreateEntityDTO(dto, course, student));
	}

	public EnrollmentReturnDTO update (Long id, EnrollmentUpdateIdDTO dto) {
		CourseEntity course = dto.course_id() != null? course_repository.getReferenceById(dto.course_id()) : null;
		UserEntity student = dto.student_id() != null? user_repository.getReferenceById(dto.student_id()) : null;
		return super.update(id, new EnrollmentUpdateEntityDTO(dto, course, student));
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

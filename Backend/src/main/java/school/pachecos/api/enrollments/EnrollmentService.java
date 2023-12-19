package school.pachecos.api.enrollments;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import school.pachecos.api.courses.CourseEntity;
import school.pachecos.api.courses.CourseRepository;
import school.pachecos.api.enrollments.dtos.*;
import school.pachecos.api.users.UserEntity;
import school.pachecos.api.users.UserRepository;

@Service
public class EnrollmentService {

	@Autowired
	EnrollmentRepository enrollment_repository;
	@Autowired
	CourseRepository course_repository;
	@Autowired
	UserRepository user_repository;

	public Page<EnrollmentReturnDTO> listEnrollmentsPerPage(Pageable pageable) {
		Page<EnrollmentEntity> enrollments = enrollment_repository.findAll(pageable);
		return enrollments.map(EnrollmentReturnDTO::new);
	}

	public EnrollmentReturnDTO getEnrollmentById(Long id) {
		EnrollmentEntity enrollment = enrollment_repository.getReferenceById(id);
		return new EnrollmentReturnDTO(enrollment);
	}

	public EnrollmentReturnDTO create(EnrollmentCreateWithIdsDTO create_id_dto) {
		CourseEntity course = course_repository.getReferenceById(create_id_dto.course_id());
		UserEntity student = user_repository.getReferenceById(create_id_dto.student_id());
		EnrollmentCreateWithEntitiesDTO create_entity_dto = new EnrollmentCreateWithEntitiesDTO(create_id_dto, course, student);
		EnrollmentEntity enrollment = new EnrollmentEntity(create_entity_dto);
		enrollment_repository.save(enrollment);
		return new EnrollmentReturnDTO(enrollment);
	}

	public EnrollmentReturnDTO edit(EnrollmentEditWithIdsDTO edit_id_dto) {
		CourseEntity course = edit_id_dto.course_id() != null? course_repository.getReferenceById(edit_id_dto.course_id()) : null;
		UserEntity student = edit_id_dto.student_id() != null? user_repository.getReferenceById(edit_id_dto.student_id()) : null;
		EnrollmentEditWithEntitiesDTO edit_entity_dto = new EnrollmentEditWithEntitiesDTO(edit_id_dto, course, student);
		EnrollmentEntity enrollment = enrollment_repository.getReferenceById(edit_entity_dto.id());
		enrollment.update(edit_entity_dto);
		return new EnrollmentReturnDTO(enrollment);
	}

	public void delete(Long id) {
		EnrollmentEntity entity = enrollment_repository.getReferenceById(id);
		enrollment_repository.delete(entity);
	}
}

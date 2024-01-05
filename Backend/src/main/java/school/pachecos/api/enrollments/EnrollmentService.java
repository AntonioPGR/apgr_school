package school.pachecos.api.enrollments;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import school.pachecos.api.courses.CourseEntity;
import school.pachecos.api.courses.CourseService;
import school.pachecos.api.enrollments.dtos.*;
import school.pachecos.api.users.UserEntity;
import school.pachecos.api.users.UserService;
import school.pachecos.infra.commons.classes.BaseService;

@Service
public class EnrollmentService extends BaseService<
	EnrollmentEntity,
	EnrollmentCreateIdDTO,
	EnrollmentCreateEntityDTO,
	EnrollmentUpdateIdDTO,
	EnrollmentUpdateEntityDTO,
	EnrollmentReturnDTO,
	EnrollmentRepository
> {

	@Autowired
  UserService user_service;
	@Autowired
  CourseService course_service;

	@Override
	protected EnrollmentCreateEntityDTO convertToCreateDTO(EnrollmentCreateIdDTO dto) {
		UserEntity student = user_service.getEntityById(dto.student_id());
		CourseEntity course = course_service.getEntityById(dto.course_id());
		return new EnrollmentCreateEntityDTO(dto, course, student);
	}

	@Override
	protected EnrollmentUpdateEntityDTO convertToUpdateDTO(EnrollmentUpdateIdDTO dto) {
		UserEntity student = dto.student_id() != null? user_service.getEntityById(dto.student_id()):null;
		CourseEntity course = dto.course_id()!=null?course_service.getEntityById(dto.course_id()):null;
		return new EnrollmentUpdateEntityDTO(dto, course, student);
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

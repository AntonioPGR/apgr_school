package school.pachecos.api.enrollments.dtos;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import school.pachecos.api.courses.CourseEntity;
import school.pachecos.api.users.UserEntity;
import school.pachecos.infra.commons.interfaces.BaseUpdateDTO;

import java.sql.Date;
import java.time.LocalDate;
import java.util.UUID;

public record EnrollmentUpdateEntityDTO(
		@NotNull UUID id,
		@Nullable CourseEntity course,
		@Nullable UserEntity student,
		@Nullable LocalDate enrollment_date,
		@Nullable LocalDate end_date) implements BaseUpdateDTO {

	public EnrollmentUpdateEntityDTO(EnrollmentUpdateIdDTO enrollment_id, CourseEntity course, UserEntity student) {
		this(enrollment_id.id(), course, student, enrollment_id.enrollment_date(), enrollment_id.end_date());
	}

	@Override
	public UUID id() {
		return id;
	}
}

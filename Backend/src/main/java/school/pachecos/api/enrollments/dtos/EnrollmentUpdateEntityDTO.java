package school.pachecos.api.enrollments.dtos;

import java.time.LocalDate;

import jakarta.annotation.Nullable;
import school.pachecos.api.courses.CourseEntity;
import school.pachecos.api.users.UserEntity;

public record EnrollmentUpdateEntityDTO(
		@Nullable CourseEntity course,
		@Nullable UserEntity student,
		@Nullable LocalDate enrollment_date,
		@Nullable LocalDate end_date) {

	public EnrollmentUpdateEntityDTO(EnrollmentUpdateIdDTO enrollment_id, CourseEntity course, UserEntity student) {
		this(course, student, enrollment_id.enrollment_date(), enrollment_id.end_date());
	}

}

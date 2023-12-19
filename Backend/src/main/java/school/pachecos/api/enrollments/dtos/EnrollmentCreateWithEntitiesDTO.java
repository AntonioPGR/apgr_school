package school.pachecos.api.enrollments.dtos;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import school.pachecos.api.courses.CourseEntity;
import school.pachecos.api.users.UserEntity;

import java.sql.Date;

public record EnrollmentCreateWithEntitiesDTO(
		@NotNull CourseEntity course,
		@NotNull UserEntity student,
		@NotNull Date enrollment_date,
		@Nullable Date end_date
) {

	public EnrollmentCreateWithEntitiesDTO(EnrollmentCreateWithIdsDTO enrollment_id, CourseEntity course, UserEntity student){
		this(course, student, enrollment_id.enrollment_date(), enrollment_id.end_date());
	}

}

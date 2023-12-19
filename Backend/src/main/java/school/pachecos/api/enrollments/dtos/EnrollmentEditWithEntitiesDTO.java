package school.pachecos.api.enrollments.dtos;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import school.pachecos.api.courses.CourseEntity;
import school.pachecos.api.users.UserEntity;

import java.sql.Date;

public record EnrollmentEditWithEntitiesDTO(
		@NotNull Long id,
		@Nullable CourseEntity course,
		@Nullable UserEntity student,
		@Nullable Date enrollment_date,
		@Nullable Date end_date
) {

	public EnrollmentEditWithEntitiesDTO(EnrollmentEditWithIdsDTO enrollment_id, CourseEntity course, UserEntity student){
		this(enrollment_id.id(), course, student, enrollment_id.enrollment_date(), enrollment_id.end_date());
	}

}

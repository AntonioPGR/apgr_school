package school.pachecos.api.enrollments.dtos;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;

import java.sql.Date;

public record EnrollmentEditWithIdsDTO(
		@NotNull Long id,
		@Nullable Long course_id,
		@Nullable Long student_id,
		@Nullable Date enrollment_date,
		@Nullable Date end_date
) {
}

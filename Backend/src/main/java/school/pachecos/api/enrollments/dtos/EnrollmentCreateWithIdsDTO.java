package school.pachecos.api.enrollments.dtos;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;

import java.sql.Date;

public record EnrollmentCreateWithIdsDTO(
		@NotNull Long course_id,
		@NotNull Long student_id,
		@NotNull Date enrollment_date,
		@Nullable Date end_date
) {
}

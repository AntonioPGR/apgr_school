package school.pachecos.api.enrollments.dtos;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;

import java.sql.Date;
import java.time.LocalDate;

public record EnrollmentUpdateIdDTO(
		@NotNull Long id,
		@Nullable Long course_id,
		@Nullable Long student_id,
		@Nullable LocalDate enrollment_date,
		@Nullable LocalDate end_date
) {
}

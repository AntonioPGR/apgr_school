package school.pachecos.api.enrollments.dtos;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record EnrollmentCreateIdDTO(
	@NotNull Long course_id,
	@NotNull Long student_id,
	@Nullable LocalDate enrollment_date,
	@Nullable LocalDate end_date
	) {
}

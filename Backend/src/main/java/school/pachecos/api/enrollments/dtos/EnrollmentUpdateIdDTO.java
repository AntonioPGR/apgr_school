package school.pachecos.api.enrollments.dtos;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;

import java.sql.Date;
import java.time.LocalDate;
import java.util.UUID;

public record EnrollmentUpdateIdDTO(
		@NotNull UUID id,
		@Nullable UUID course_id,
		@Nullable UUID student_id,
		@Nullable LocalDate enrollment_date,
		@Nullable LocalDate end_date) {
}

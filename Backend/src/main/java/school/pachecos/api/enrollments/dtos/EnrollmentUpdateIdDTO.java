package school.pachecos.api.enrollments.dtos;

import java.time.LocalDate;

import jakarta.annotation.Nullable;

public record EnrollmentUpdateIdDTO(
		@Nullable Long course_id,
		@Nullable Long student_id,
		@Nullable LocalDate enrollment_date,
		@Nullable LocalDate end_date) {
}

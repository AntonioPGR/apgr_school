package school.pachecos.api.enrollments.dtos;

import jakarta.validation.constraints.NotNull;

public record EnrollmentCreateIdDTO(
		@NotNull Long course_id,
		@NotNull Long student_id) {
}

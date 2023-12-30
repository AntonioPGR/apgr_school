package school.pachecos.api.enrollments.dtos;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record EnrollmentCreateIdDTO(
		@NotNull UUID course_id,
		@NotNull UUID student_id) {
}

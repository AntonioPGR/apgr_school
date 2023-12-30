package school.pachecos.api.assignments.dtos;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record AssignmentCreateIdDTO(
		@NotNull int is_done,
		@NotNull UUID task_id,
		@NotNull UUID student_id,
		@Nullable String response_location) {
}

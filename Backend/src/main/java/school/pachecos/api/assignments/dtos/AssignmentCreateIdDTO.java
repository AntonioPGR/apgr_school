package school.pachecos.api.assignments.dtos;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;

public record AssignmentCreateIdDTO(
		@NotNull int is_done,
		@NotNull Long task_id,
		@NotNull Long student_id,
		@Nullable String response_location
) {
}

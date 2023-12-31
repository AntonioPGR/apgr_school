package school.pachecos.api.assignments.dtos;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;

public record AssignmentUpdateIdDTO(
		@NotNull Long id,
		@Nullable Integer is_done,
		@Nullable Long task_id,
		@Nullable Long student_id,
		@Nullable String response_location) {
}

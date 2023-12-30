package school.pachecos.api.assignments.dtos;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record AssignmentUpdateIdDTO(
		@NotNull UUID id,
		@Nullable Integer is_done,
		@Nullable UUID task_id,
		@Nullable UUID student_id,
		@Nullable String response_location) {
}

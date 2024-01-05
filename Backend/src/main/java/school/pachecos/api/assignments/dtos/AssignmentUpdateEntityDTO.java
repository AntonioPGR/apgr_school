package school.pachecos.api.assignments.dtos;

import jakarta.annotation.Nullable;
import school.pachecos.api.tasks.TaskEntity;
import school.pachecos.api.users.UserEntity;

public record AssignmentUpdateEntityDTO(
		@Nullable Integer is_done,
		@Nullable TaskEntity task,
		@Nullable UserEntity student,
		@Nullable String response_location) {

	public AssignmentUpdateEntityDTO(AssignmentUpdateIdDTO dto, TaskEntity task, UserEntity user) {
		this(dto.is_done(), task, user, dto.response_location());
	}
}

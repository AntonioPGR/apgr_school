package school.pachecos.api.assignments.dtos;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import school.pachecos.api.tasks.TaskEntity;
import school.pachecos.api.users.UserEntity;

public record AssignmentUpdateEntityDTO(
		@Nonnull Long id,
		@Nullable Integer is_done,
		@Nullable TaskEntity task,
		@Nullable UserEntity student,
		@Nullable String response_location) {

	public AssignmentUpdateEntityDTO(AssignmentUpdateIdDTO ids, TaskEntity task, UserEntity user) {
		this(ids.id(), ids.is_done(), task, user, ids.response_location());
	}

	@Override
	public Long id() {
		return id;
	}
}

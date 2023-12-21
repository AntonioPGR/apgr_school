package school.pachecos.api.assignments.dtos;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import school.pachecos.api.tasks.TaskEntity;
import school.pachecos.api.users.UserEntity;

public record AssignmentCreateEntityDTO(
		@NotNull int is_done,
		@NotNull TaskEntity task,
		@NotNull UserEntity student,
		@Nullable String response_location
) {

	public AssignmentCreateEntityDTO(AssignmentCreateIdDTO ids, TaskEntity task, UserEntity user){
		this(ids.is_done(), task, user, ids.response_location());
	}

}

package school.pachecos.api.assignments.dtos;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import school.pachecos.api.assignments.AssignmentEntity;
import school.pachecos.api.tasks.dtos.TaskReturnDTO;
import school.pachecos.api.users.dtos.UserReturnDTO;

public record AssignmentReturnDTO(
		Long id,
		@NotNull int is_done,
		@Nullable String response_location,
		@NotNull TaskReturnDTO task,
		@NotNull UserReturnDTO student
) {
	public AssignmentReturnDTO(AssignmentEntity tasks) {
		this(tasks.getId(), tasks.getIs_done(), tasks.getResponse_location(), new TaskReturnDTO(tasks.getTask()), new UserReturnDTO(tasks.getStudent()));
	}
}

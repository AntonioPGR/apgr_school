package school.pachecos.api.assignments.dtos;

import jakarta.annotation.Nullable;
import school.pachecos.api.tasks.TaskEntity;
import school.pachecos.api.users.UserEntity;
import school.pachecos.commons.interfaces.BaseUpdateDTO;

public record AssignmentUpdateEntityDTO(
		Long id,
		@Nullable Integer is_done,
		@Nullable TaskEntity task,
		@Nullable UserEntity student,
		@Nullable String response_location
) implements BaseUpdateDTO {

	public AssignmentUpdateEntityDTO(AssignmentUpdateIdDTO ids, TaskEntity task, UserEntity user){
		this(ids.id(), ids.is_done(), task, user, ids.response_location());
	}

	@Override
	public Long getId() {
		return id;
	}
}
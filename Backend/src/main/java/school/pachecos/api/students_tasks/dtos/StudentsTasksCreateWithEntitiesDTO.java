package school.pachecos.api.students_tasks.dtos;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import school.pachecos.api.tasks.TaskEntity;
import school.pachecos.api.users.UserEntity;

public record StudentsTasksCreateWithEntitiesDTO(
		@NotNull int is_done,
		@NotNull TaskEntity task,
		@NotNull UserEntity student,
		@Nullable String response_location
) {

	public StudentsTasksCreateWithEntitiesDTO(StudentsTasksCreateWithIdsDTO ids, TaskEntity task, UserEntity user){
		this(ids.is_done(), task, user, ids.response_location());
	}

}

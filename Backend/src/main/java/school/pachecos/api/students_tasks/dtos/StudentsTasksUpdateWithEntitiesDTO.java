package school.pachecos.api.students_tasks.dtos;

import jakarta.annotation.Nullable;
import school.pachecos.api.tasks.TaskEntity;
import school.pachecos.api.users.UserEntity;

public record StudentsTasksUpdateWithEntitiesDTO(
		Long id,
		@Nullable Integer is_done,
		@Nullable TaskEntity task,
		@Nullable UserEntity student,
		@Nullable String response_location
) {

	public StudentsTasksUpdateWithEntitiesDTO(StudentsTasksUpdateWithIdsDTO ids, TaskEntity task, UserEntity user){
		this(ids.id(), ids.is_done(), task, user, ids.response_location());
	}

}

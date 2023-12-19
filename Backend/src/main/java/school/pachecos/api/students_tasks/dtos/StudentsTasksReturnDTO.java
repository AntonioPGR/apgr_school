package school.pachecos.api.students_tasks.dtos;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import school.pachecos.api.students_tasks.StudentsTasksEntity;
import school.pachecos.api.tasks.dtos.TaskReturnInfoDTO;
import school.pachecos.api.users.dtos.UserReturnInfoDTO;

public record StudentsTasksReturnDTO(
		Long id,
		@NotNull int is_done,
		@Nullable String response_location,
		@NotNull TaskReturnInfoDTO task,
		@NotNull UserReturnInfoDTO student
) {
	public StudentsTasksReturnDTO(StudentsTasksEntity tasks) {
		this(tasks.getId(), tasks.getIs_done(), tasks.getResponse_location(), new TaskReturnInfoDTO(tasks.getTask()), new UserReturnInfoDTO(tasks.getStudent()));
	}
}

package school.pachecos.api.students_tasks.dtos;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;

public record StudentsTasksCreateWithIdsDTO(
		@NotNull int is_done,
		@NotNull Long task_id,
		@NotNull Long student_id,
		@Nullable String response_location
) {
}

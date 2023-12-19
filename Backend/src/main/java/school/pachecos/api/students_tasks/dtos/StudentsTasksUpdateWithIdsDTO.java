package school.pachecos.api.students_tasks.dtos;

import jakarta.annotation.Nullable;

public record StudentsTasksUpdateWithIdsDTO(
		Long id,
		@Nullable Integer is_done,
		@Nullable Long task_id,
		@Nullable Long student_id,
		@Nullable String response_location
) {
}

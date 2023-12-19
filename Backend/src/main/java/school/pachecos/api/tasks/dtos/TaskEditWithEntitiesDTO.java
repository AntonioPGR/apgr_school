package school.pachecos.api.tasks.dtos;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import school.pachecos.api.lessons.LessonEntity;
import java.time.LocalDateTime;


public record TaskEditWithEntitiesDTO(
		@NotNull Long id,
		@Nullable String title,
		@Nullable LocalDateTime due_date,
		@Nullable String description,
		@Nullable LessonEntity lesson
) {
	public TaskEditWithEntitiesDTO(TaskEditWithIDsDTO edited_task, @Nullable LessonEntity lesson_entity) {
		this( edited_task.id(), edited_task.title(), edited_task.dueDate(), edited_task.description(), lesson_entity);
	}
}


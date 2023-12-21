package school.pachecos.api.tasks.dtos;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import school.pachecos.api.lessons.LessonEntity;

import java.time.LocalDateTime;


public record TaskCreateEntityDTO(
		@NotNull String title,
		@NotNull LocalDateTime dueDate,
		@Nullable String description,
		@NotNull LessonEntity lesson
)
{
	public TaskCreateEntityDTO(TaskCreateIdDTO new_task_id, LessonEntity lesson_entity) {
		this(new_task_id.title(), new_task_id.due_date(), new_task_id.description(), lesson_entity);
	}
}

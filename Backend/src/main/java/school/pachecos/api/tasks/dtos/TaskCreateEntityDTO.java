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
	public TaskCreateEntityDTO(TaskCreateIdDTO dto, LessonEntity lesson_entity) {
		this(dto.title(), dto.due_date(), dto.description(), lesson_entity);
	}
}


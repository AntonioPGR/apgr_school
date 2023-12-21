package school.pachecos.api.tasks.dtos;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import school.pachecos.api.lessons.LessonEntity;
import school.pachecos.commons.interfaces.BaseUpdateDTO;

import java.time.LocalDateTime;


public record TaskUpdateEntityDTO(
		@NotNull Long id,
		@Nullable String title,
		@Nullable LocalDateTime due_date,
		@Nullable String description,
		@Nullable LessonEntity lesson
) implements BaseUpdateDTO {
	public TaskUpdateEntityDTO(TaskUpdateIdDTO edited_task, @Nullable LessonEntity lesson_entity) {
		this( edited_task.id(), edited_task.title(), edited_task.dueDate(), edited_task.description(), lesson_entity);
	}

	@Override
	public Long getId() {
		return id;
	}
}


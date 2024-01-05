package school.pachecos.api.tasks.dtos;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import school.pachecos.api.lessons.dto.LessonReturnDTO;
import school.pachecos.api.tasks.TaskEntity;
import school.pachecos.infra.commons.interfaces.ReturnDTOInterface;

import java.time.LocalDateTime;

public record TaskReturnDTO(
		@NotNull Long id,
		@NotNull String title,
		@NotNull LocalDateTime dueDate,
		@Nullable String description,
		@NotNull LessonReturnDTO lesson) implements ReturnDTOInterface {
	public TaskReturnDTO(TaskEntity entity) {
		this(entity.getId(), entity.getTitle(), entity.getDueDate(), entity.getDescription(),
				new LessonReturnDTO(entity.getLesson()));
	}
}

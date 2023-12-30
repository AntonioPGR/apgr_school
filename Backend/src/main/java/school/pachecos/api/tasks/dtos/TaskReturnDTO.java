package school.pachecos.api.tasks.dtos;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import school.pachecos.api.lessons.dto.LessonReturnDTO;
import school.pachecos.api.tasks.TaskEntity;
import school.pachecos.infra.commons.interfaces.BaseReturnDTO;

import java.time.LocalDateTime;
import java.util.UUID;

public record TaskReturnDTO(
		@NotNull UUID id,
		@NotNull String title,
		@NotNull LocalDateTime dueDate,
		@Nullable String description,
		@NotNull LessonReturnDTO lesson) implements BaseReturnDTO {
	public TaskReturnDTO(TaskEntity entity) {
		this(entity.getId(), entity.getTitle(), entity.getDueDate(), entity.getDescription(),
				new LessonReturnDTO(entity.getLesson()));
	}
}

package school.pachecos.api.tasks.dtos;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import school.pachecos.api.lessons.dto.LessonReturnInfoDTO;
import school.pachecos.api.tasks.TaskEntity;

import java.time.LocalDateTime;


public record TaskReturnInfoDTO(
		@NotNull long id,
		@NotNull String title,
		@NotNull LocalDateTime dueDate,
		@Nullable String description,
		@NotNull LessonReturnInfoDTO lesson
)
{
	public TaskReturnInfoDTO(TaskEntity entity){
		this(entity.getId(), entity.getTitle(), entity.getDueDate(), entity.getDescription(), new LessonReturnInfoDTO(entity.getLesson()));
	}
}

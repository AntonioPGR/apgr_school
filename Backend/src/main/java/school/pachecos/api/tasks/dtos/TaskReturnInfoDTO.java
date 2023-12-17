package school.pachecos.api.tasks.dtos;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import school.pachecos.api.lessons.dto.LessonReturnInfoDTO;
import school.pachecos.api.tasks.TaskEntity;

import java.time.LocalDateTime;


public record TaskReturnInfoDTO(
	@NotNull Long id,
	@NotBlank @NotNull String title,
	@NotNull LocalDateTime dueDate,
	@Nullable String description,
	@NotNull LessonReturnInfoDTO lesson
){

	public TaskReturnInfoDTO(TaskEntity task_entity){
		this(
			task_entity.getId(),
			task_entity.getTitle(),
			task_entity.getDueDate(),
			task_entity.getDescription(),
			new LessonReturnInfoDTO(task_entity.getLesson())
		);
	}

}

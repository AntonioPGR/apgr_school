package school.pachecos.api.tasks.dtos;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import school.pachecos.api.lessons.dto.LessonReturnInfoDTO;

import java.time.LocalDateTime;


public record TaskEditWithIDsDTO(
		@NotNull Long id,
		@Nullable String title,
		@Nullable LocalDateTime dueDate,
		@Nullable String description,
		@Nullable Long lesson_id
)
{}

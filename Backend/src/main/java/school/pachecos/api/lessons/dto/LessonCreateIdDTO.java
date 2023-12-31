package school.pachecos.api.lessons.dto;

import java.time.LocalDateTime;

public record LessonCreateIdDTO(
	String name,
	LocalDateTime datetime,
	Long professor_id,
	int duration_in_minutes
)
{
}

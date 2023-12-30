package school.pachecos.api.lessons.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record LessonCreateIdDTO(String name, LocalDateTime datetime, UUID professor_id, int duration_in_minutes) {
}

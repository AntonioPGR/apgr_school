package school.pachecos.api.lessons.dto;

import jakarta.annotation.Nullable;

import java.time.LocalDateTime;

public record LessonSearchDTO(
		@Nullable String name,
		@Nullable LocalDateTime datetime
) {}

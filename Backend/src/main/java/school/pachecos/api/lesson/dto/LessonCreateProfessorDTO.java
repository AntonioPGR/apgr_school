package school.pachecos.api.lesson.dto;

import school.pachecos.api.lesson.LessonEntity;
import school.pachecos.api.users.UserEntity;

import java.time.LocalDateTime;

public record LessonCreateProfessorDTO(String name, LocalDateTime datetime, UserEntity professor, int duration_in_minutes) {
}

package school.pachecos.api.assignments.dtos;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record AssignmentCreateLessonIdDTO(
        @NotNull UUID task_id,
        @NotNull UUID lesson_id) {
}

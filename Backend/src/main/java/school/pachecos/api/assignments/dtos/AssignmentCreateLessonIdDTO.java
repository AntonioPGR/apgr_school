package school.pachecos.api.assignments.dtos;

import jakarta.validation.constraints.NotNull;


public record AssignmentCreateLessonIdDTO(
  @NotNull Long task_id,
  @NotNull Long lesson_id) {
}

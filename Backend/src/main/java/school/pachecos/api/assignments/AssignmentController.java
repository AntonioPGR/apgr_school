package school.pachecos.api.assignments;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.pachecos.api.assignments.dtos.AssignmentCreateEntityDTO;
import school.pachecos.api.assignments.dtos.AssignmentCreateLessonIdDTO;
import school.pachecos.api.assignments.dtos.AssignmentReturnDTO;
import school.pachecos.api.assignments.dtos.AssignmentUpdateEntityDTO;
import school.pachecos.api.courses.CourseEntity;
import school.pachecos.api.courses.CourseService;
import school.pachecos.api.courses.dtos.CourseCreateDTO;
import school.pachecos.api.courses.dtos.CourseReturnDTO;
import school.pachecos.api.courses.dtos.CourseUpdateDTO;
import school.pachecos.api.lessons.LessonEntity;
import school.pachecos.api.lessons.LessonRepository;
import school.pachecos.api.tasks.TaskEntity;
import school.pachecos.api.tasks.TaskRepository;
import school.pachecos.api.users.UserEntity;
import school.pachecos.api.users.UserRepository;
import school.pachecos.infra.commons.classes.BaseApiController;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/students/tasks")
public class AssignmentController extends BaseApiController<AssignmentEntity, AssignmentCreateEntityDTO, AssignmentUpdateEntityDTO, AssignmentReturnDTO, AssignmentService> {

  @Autowired
  LessonRepository lesson_repository;
  @Autowired
  TaskRepository task_repository;

  @PostMapping
  @Transactional
  public ResponseEntity<URI> create(@RequestBody @Valid AssignmentCreateLessonIdDTO dto){
    TaskEntity task = task_repository.getReferenceById(dto.task_id());
    LessonEntity lesson = lesson_repository.getReferenceById(dto.lesson_id());
    List<UserEntity> users = lesson.getStudents();
    users.forEach(user -> {
      AssignmentCreateEntityDTO create_dto = new AssignmentCreateEntityDTO(0, task, user, null);
      this.create(create_dto);
    });
    return ResponseEntity.ok().build();
  }

  @Override
  protected String getBaseUriPath() {
    return "/students/tasks";
  }
}

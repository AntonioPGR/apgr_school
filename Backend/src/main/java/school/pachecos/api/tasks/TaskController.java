package school.pachecos.api.tasks;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.pachecos.api.assignments.AssignmentController;
import school.pachecos.api.assignments.AssignmentService;
import school.pachecos.api.assignments.dtos.AssignmentCreateLessonIdDTO;
import school.pachecos.api.lessons.LessonEntity;
import school.pachecos.api.lessons.LessonRepository;
import school.pachecos.api.tasks.dtos.*;
import school.pachecos.infra.commons.classes.BaseApiController;
import school.pachecos.infra.uri.URIService;

import java.net.URI;


@RestController
@RequestMapping("/tasks")
public class TaskController extends BaseApiController<TaskEntity, TaskCreateEntityDTO, TaskUpdateEntityDTO, TaskReturnDTO, TaskService> {

	@Autowired
	LessonRepository lesson_repository;
	@Autowired
  AssignmentService assignment_service;
	@Autowired
	TaskService task_service;
  @Autowired
  URIService uri_service;

	@PostMapping
	public ResponseEntity<URI> create(@RequestBody @Valid TaskCreateIdDTO dto){
		LessonEntity lesson = lesson_repository.getReferenceById(dto.lesson_id());
		TaskCreateEntityDTO entity_dto = new TaskCreateEntityDTO(dto, lesson);
		TaskReturnDTO task_dto = task_service.create(entity_dto);
		assignment_service.create(new AssignmentCreateLessonIdDTO(task_dto.id(), dto.lesson_id()));
    URI entity_uri = uri_service.createReturnURI(getBaseUriPath()+task_dto.id());
    return ResponseEntity.created(entity_uri).build();
	}

	@Override
	protected String getBaseUriPath() {
		return "/tasks";
	}



}

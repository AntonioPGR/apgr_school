package school.pachecos.api.tasks;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
	
	@GetMapping
	public ResponseEntity<Page<TaskReturnDTO>> listAll(Pageable pageable) {
		Page<TaskReturnDTO> page = task_service.listPerPage(pageable);
		return ResponseEntity.ok().body(page);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<TaskReturnDTO> getUnique(@PathVariable("id") Long id) {
		TaskReturnDTO dto = task_service.getById(id);
		return ResponseEntity.ok().body(dto);
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<TaskReturnDTO> create(@RequestBody @Valid TaskCreateIdDTO dto){
		LessonEntity lesson = lesson_repository.getReferenceById(dto.lesson_id());
		TaskCreateEntityDTO entity_dto = new TaskCreateEntityDTO(dto, lesson);
		TaskReturnDTO task_dto = task_service.create(entity_dto);
		assignment_service.create(new AssignmentCreateLessonIdDTO(task_dto.id(), dto.lesson_id()));
    URI entity_uri = uri_service.createReturnURI(getBaseUriPath()+task_dto.id());
    return ResponseEntity.created(entity_uri).body(task_dto);
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<TaskReturnDTO> update(@PathVariable("id") Long id, @RequestBody @Valid TaskUpdateIdDTO dto) {
		LessonEntity lesson = null;
		if(dto.lesson_id() != null){
			lesson = lesson_repository.getReferenceById(dto.lesson_id());
		}
		TaskReturnDTO entity = task_service.update(id, new TaskUpdateEntityDTO(dto, lesson));
		URI entity_uri = uri_service.createReturnURI(getBaseUriPath() + id);
		return ResponseEntity.created(entity_uri).body(entity);
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity delete(@PathVariable("id") Long id) {
		task_service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	
	@Override
	protected String getBaseUriPath() {
		return "/tasks";
	}



}

package school.pachecos.api.tasks;

import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.pachecos.api.tasks.dtos.TaskCreateWithIDsDTO;
import school.pachecos.api.tasks.dtos.TaskEditWithIDsDTO;
import school.pachecos.api.tasks.dtos.TaskReturnInfoDTO;
import school.pachecos.commons.dtos.IdDTO;

import java.net.URI;
import java.net.URISyntaxException;


@RestController
@RequestMapping("/tasks")
public class TaskController {

	@Autowired
	private TaskService task_service;

	// GET
	@GetMapping
	public ResponseEntity<Page<TaskReturnInfoDTO>> listAllTasks(@PageableDefault(size=30, sort="name") Pageable pageable){
		Page<TaskReturnInfoDTO> tasks_page = task_service.listTasksPerPage(pageable);
		return ResponseEntity.ok().body(tasks_page);
	}

	@GetMapping("/{id}")
	public ResponseEntity<TaskReturnInfoDTO> getTask(@PathParam("id") Long id){
		TaskReturnInfoDTO task_dto = task_service.findTaskById(id);
		return ResponseEntity.ok().body(task_dto);
	}

	// POST
	@PostMapping
	public ResponseEntity<URI> createTask(TaskCreateWithIDsDTO new_task) throws URISyntaxException {
		long new_task_id = task_service.createTask(new_task).id();
		URI new_task_uri = new URI("http://localhost:8000/tasks/"+new_task_id);
		return ResponseEntity.created(new_task_uri).build();
	}

	// PUT
	@PutMapping
	public ResponseEntity<URI> editTask(TaskEditWithIDsDTO edited_task) throws URISyntaxException {
		long edited_task_id = task_service.editTask(edited_task).id();
		URI edited_task_uri = new URI("http://localhost:8000/tasks/"+edited_task_id);
		return ResponseEntity.created(edited_task_uri).build();
	}


	// DELETE
	@DeleteMapping
	public ResponseEntity deleteTask(IdDTO id_dto){
		task_service.deleteTask(id_dto.id());
		return ResponseEntity.noContent().build();
	}

}

package school.pachecos.api.students_tasks;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.pachecos.api.students_tasks.dtos.StudentsTasksCreateWithIdsDTO;
import school.pachecos.api.students_tasks.dtos.StudentsTasksReturnDTO;
import school.pachecos.api.students_tasks.dtos.StudentsTasksUpdateWithIdsDTO;
import school.pachecos.commons.dtos.IdDTO;

import java.net.URI;
import java.net.URISyntaxException;


@RestController
@RequestMapping("/students/tasks")
public class StudentsTasksController {

	@Autowired
	private StudentsTasksService students_tasks_service;

	// GET
	@GetMapping
	public ResponseEntity<Page<StudentsTasksReturnDTO>> getPageStudentsTasks(@PageableDefault Pageable pageable){
		Page<StudentsTasksReturnDTO> students_tasks_page = students_tasks_service.listStudentsTasksPerPage(pageable);
		return ResponseEntity.ok(students_tasks_page);
	}

	@GetMapping("/{id}")
	public ResponseEntity<StudentsTasksReturnDTO> getSingleStudentsTasks(@PathVariable("id") Long id){
		StudentsTasksReturnDTO students_tasks = students_tasks_service.getStudentsTasksById(id);
		return ResponseEntity.ok(students_tasks);
	}

	// POST
	@PostMapping
	public ResponseEntity<URI> createStudentsTask(@RequestBody @Valid StudentsTasksCreateWithIdsDTO create_dto) throws URISyntaxException {
		Long id = students_tasks_service.create(create_dto).id();
		URI students_task_uri = new URI("http://localhost:8000/students/tasks/"+id);
		return ResponseEntity.created(students_task_uri).build();
	}

	// PUT
	@PutMapping
	public ResponseEntity<URI> editStudentsTask(@RequestBody @Valid StudentsTasksUpdateWithIdsDTO update_dto) throws URISyntaxException {
		Long id = students_tasks_service.update(update_dto).id();
		URI students_task_uri = new URI("http://localhost:8000/students/tasks/"+id);
		return ResponseEntity.created(students_task_uri).build();
	}

	// DELETE
	@DeleteMapping
	public ResponseEntity<URI> deleteStudentsTask(@RequestBody @Valid IdDTO id_dto){
		students_tasks_service.delete(id_dto.id());
		return ResponseEntity.noContent().build();
	}

}

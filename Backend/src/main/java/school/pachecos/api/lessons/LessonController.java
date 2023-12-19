package school.pachecos.api.lessons;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.pachecos.api.lessons.dto.*;
import school.pachecos.commons.dtos.IdDTO;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;


@RestController
@RequestMapping("/lessons")
public class LessonController {

	@Autowired
	LessonService lesson_service;

	// GET
	@GetMapping
	public ResponseEntity<Page<LessonReturnInfoDTO>> listAllLessons(@PageableDefault(size=30, sort="name") Pageable pageable){
		Page<LessonReturnInfoDTO> lessons_page = lesson_service.listLessonsPerPage(pageable);
		return ResponseEntity.ok().body(lessons_page);
	}

	@GetMapping("/{id}")
	public ResponseEntity<LessonReturnInfoDTO> getLesson(@PathVariable("id") long lesson_id){
		LessonReturnInfoDTO lesson_dto = lesson_service.getLessonReferenceById(lesson_id);
		return ResponseEntity.ok().body(lesson_dto);
	}


	// POST
	@PostMapping
	@Transactional
	public ResponseEntity createLesson(@RequestBody @Valid LessonCreateIdDTO lesson_info) throws URISyntaxException {
		long lesson_id = lesson_service.createLesson(lesson_info).id();
		URI lesson_uri = new URI("localhost:8000/lessons/"+lesson_id);
		return ResponseEntity.created(lesson_uri).build();
	}

	@PostMapping("/search")
	public ResponseEntity<List<LessonReturnInfoDTO>> searchLessons(@RequestBody @Valid LessonSearchDTO search_info){
		List<LessonReturnInfoDTO> search_result = lesson_service.searchLessons(search_info);
		return ResponseEntity.ok().body(search_result);
	}

	// PUT
	@PutMapping
	@Transactional
	public ResponseEntity editLesson(@RequestBody @Valid LessonUpdateIdDTO lesson_info){
		lesson_service.updateLesson(lesson_info);
		return ResponseEntity.noContent().build();
	}

	// DELETE
	@DeleteMapping
	@Transactional
	public ResponseEntity deleteLesson(@RequestBody @Valid IdDTO lesson_id_dto){
		lesson_service.deleteLesson(lesson_id_dto.id());
		return ResponseEntity.noContent().build();
	}

}

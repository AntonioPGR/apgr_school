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
import school.pachecos.infra.uri.URIService;

import java.net.URI;
import java.util.List;


@RestController
@RequestMapping("/lessons")
public class LessonController{

	@Autowired
	LessonService service;
	@Autowired
	URIService uri_service;

	@PostMapping("/search")
	public ResponseEntity<List<LessonReturnDTO>> searchLessons(@RequestBody @Valid LessonSearchDTO search_info){
		List<LessonReturnDTO> search_result = service.searchLessons(search_info);
		return ResponseEntity.ok().body(search_result);
	}
	@GetMapping
	public ResponseEntity<Page<LessonReturnDTO>> listAll(@PageableDefault(size = 20) Pageable pageable) {
		Page<LessonReturnDTO> page = service.listPerPage(pageable);
		return ResponseEntity.ok().body(page);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<LessonReturnDTO> getUnique(@PathVariable("id") Long id) {
		LessonReturnDTO dto = (LessonReturnDTO) service.getById(id);
		return ResponseEntity.ok().body(dto);
	}
	
	// POST
	@PostMapping
	@Transactional
	public ResponseEntity<LessonReturnDTO> create(@RequestBody @Valid LessonCreateIdDTO dto) {
		LessonReturnDTO return_dto = service.create(dto);
		URI entity_uri = uri_service.createReturnURI(getBaseUriPath() + return_dto.id());
		return ResponseEntity.created(entity_uri).body(return_dto);
	}
	
	// PUT
	@Transactional
	@PutMapping("/{id}")
	public ResponseEntity<LessonReturnDTO> update(@PathVariable("id") Long id, @RequestBody @Valid LessonUpdateIdDTO dto) {
		LessonReturnDTO return_dto = service.update(id, dto);
		URI entity_uri = uri_service.createReturnURI(getBaseUriPath() + id);
		return ResponseEntity.created(entity_uri).body(return_dto);
	}
	@SuppressWarnings("rawtypes")
	@DeleteMapping("/{id}")
	public ResponseEntity delete(@PathVariable("id") Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	private String getBaseUriPath() {
		return "/lessons";
	}
}

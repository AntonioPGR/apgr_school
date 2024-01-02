package school.pachecos.api.enrollments;


import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.pachecos.api.enrollments.dtos.*;
import school.pachecos.infra.commons.classes.BaseApiController;
import school.pachecos.infra.commons.classes.StaticApiController;
import school.pachecos.infra.uri.URIService;

import java.net.URI;


@RestController
@RequestMapping("/enrollments")
public class EnrollmentController{
	
	@Autowired
	EnrollmentService service;
	@Autowired
	URIService uri_service;
	
	@GetMapping
	public ResponseEntity<Page<EnrollmentReturnDTO>> listAll(@PageableDefault(size = 20) Pageable pageable) {
		Page<EnrollmentReturnDTO> page = service.listPerPage(pageable);
		return ResponseEntity.ok().body(page);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<EnrollmentReturnDTO> getUnique(@PathVariable("id") Long id) {
		EnrollmentReturnDTO dto = service.getById(id);
		return ResponseEntity.ok().body(dto);
	}
	
	// POST
	@PostMapping
	@Transactional
	public ResponseEntity<EnrollmentReturnDTO> create(@RequestBody @Valid EnrollmentCreateIdDTO dto) {
		EnrollmentReturnDTO return_dto = service.create(dto);
		URI entity_uri = uri_service.createReturnURI(getBaseUriPath() + return_dto.id());
		return ResponseEntity.created(entity_uri).body(return_dto);
	}
	
	// PUT
	@Transactional
	@PutMapping("/{id}")
	public ResponseEntity<EnrollmentReturnDTO> edit(@PathVariable("id") Long id, @RequestBody @Valid EnrollmentUpdateIdDTO dto) {
		EnrollmentReturnDTO entity = service.update(id, dto);
		URI entity_uri = uri_service.createReturnURI(getBaseUriPath() + id);
		return ResponseEntity.created(entity_uri).body(entity);
	}
	
	@SuppressWarnings("rawtypes")
	@DeleteMapping("/{id}")
	public ResponseEntity delete(@PathVariable("id") Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	protected String getBaseUriPath() {
		return "/enrollments";
	}
}

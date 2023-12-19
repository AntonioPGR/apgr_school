package school.pachecos.api.enrollments;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.pachecos.api.enrollments.dtos.EnrollmentCreateWithIdsDTO;
import school.pachecos.api.enrollments.dtos.EnrollmentEditWithIdsDTO;
import school.pachecos.api.enrollments.dtos.EnrollmentReturnDTO;
import school.pachecos.commons.dtos.IdDTO;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/enrollments")
public class EnrollmentController {

	@Autowired
	private EnrollmentService enrollment_service;

	// GET
	@GetMapping
	public ResponseEntity<Page<EnrollmentReturnDTO>> listEnrollments(@PageableDefault Pageable pageable){
		Page<EnrollmentReturnDTO> enrollments_dto = enrollment_service.listEnrollmentsPerPage(pageable);
		return ResponseEntity.ok(enrollments_dto);
	}

	@GetMapping("/{id}")
	public ResponseEntity<EnrollmentReturnDTO> listEnrollments(@PathVariable("id") Long id){
		EnrollmentReturnDTO enrollment_dto = enrollment_service.getEnrollmentById(id);
		return ResponseEntity.ok(enrollment_dto);
	}

	// POST
	@PostMapping
	public ResponseEntity<URI> createEnrollment(@RequestBody @Valid EnrollmentCreateWithIdsDTO create_dto) throws URISyntaxException {
		Long new_enrollment_id = enrollment_service.create(create_dto).id();
		URI new_enrollment_uri = new URI("http://localhost:8000/enrollments");
		return ResponseEntity.created(new_enrollment_uri).build();
	}

	// EDIT
	@PutMapping
	public ResponseEntity<URI> editEnrollment(@RequestBody @Valid EnrollmentEditWithIdsDTO edit_dto) throws URISyntaxException {
		Long edited_enrollment_id = enrollment_service.edit(edit_dto).id();
		URI edited_enrollment_uri = new URI("http://localhost:8000/enrollments");
		return ResponseEntity.created(edited_enrollment_uri).build();
	}

	// DELETE
	@DeleteMapping
	public ResponseEntity deleteEnrollment(@RequestBody @Valid IdDTO id_dto) {
		enrollment_service.delete(id_dto.id());
		return ResponseEntity.noContent().build();
	}

}

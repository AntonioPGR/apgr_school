package school.pachecos.api.courses;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.pachecos.api.courses.dtos.CourseNoIdDTO;
import school.pachecos.api.courses.dtos.CourseEditDTO;
import school.pachecos.commons.dtos.IdDTO;

import java.net.URI;
import java.net.URISyntaxException;


@RestController
@RequestMapping("/courses")
public class CourseController {

	@Autowired
	private CourseService course_service;

	@GetMapping
	public ResponseEntity<Page<CourseNoIdDTO>> getCourses(@PageableDefault(size = 20, sort = "name") Pageable pageable){
		Page<CourseNoIdDTO> courses_dto = course_service.getCoursesPerPage(pageable);
		return ResponseEntity.ok(courses_dto);
	}

	@GetMapping("/{id}")
	public ResponseEntity<CourseNoIdDTO> getSingleCourse(@PathVariable("id") Long id){
		CourseNoIdDTO course_dto = course_service.getCourseByID(id);
		return ResponseEntity.ok(course_dto);
	}

	@PostMapping
	@Transactional
	public ResponseEntity<URI> createCourse(@RequestBody @Valid CourseNoIdDTO create_info) throws URISyntaxException {
		Long edited_course_id = course_service.createCourse(create_info).id();
		URI edited_course_uri = new URI("http://localhost:8000/courses/"+edited_course_id);
		return ResponseEntity.created(edited_course_uri).build();
	}

	@PutMapping
	@Transactional
	public ResponseEntity<URI> editCourse(@RequestBody @Valid CourseEditDTO edit_info) throws URISyntaxException {
		Long edited_course_id = course_service.editCourse(edit_info).id();
		URI edited_course_uri = new URI("http://localhost:8000/courses/"+edited_course_id);
		return ResponseEntity.created(edited_course_uri).build();
	}

	@DeleteMapping
	@Transactional
	public ResponseEntity deleteCourse(@RequestBody @Valid IdDTO delete_info){
		course_service.deleteCourse(delete_info.id());
		return ResponseEntity.noContent().build();
	}

}

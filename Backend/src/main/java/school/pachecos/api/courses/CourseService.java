package school.pachecos.api.courses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import school.pachecos.api.courses.dtos.CourseNoIdDTO;
import school.pachecos.api.courses.dtos.CourseDTO;
import school.pachecos.api.courses.dtos.CourseEditDTO;

@Service
public class CourseService {

	@Autowired
	CourseRepository course_repository;

	public Page<CourseNoIdDTO> getCoursesPerPage(Pageable pageable) {
		Page<CourseEntity> courses_entities = course_repository.findAll(pageable);
		return courses_entities.map(CourseNoIdDTO::new);
	}

	public CourseNoIdDTO getCourseByID(Long id) {
		CourseEntity course = course_repository.getReferenceById(id);
		return new CourseNoIdDTO(course);
	}

	public CourseDTO createCourse(CourseNoIdDTO create_info) {
		CourseEntity new_course = new CourseEntity(create_info);
		course_repository.save(new_course);
		return new CourseDTO(new_course);
	}

	public CourseDTO editCourse(CourseEditDTO edit_info) {
		CourseEntity edited_course = course_repository.getReferenceById(edit_info.id());
		edited_course.update(edit_info);
		return new CourseDTO(edited_course);
	}

	public void deleteCourse(Long id) {
		CourseEntity deleted_course = course_repository.getReferenceById(id);
		course_repository.delete(deleted_course);
	}
}

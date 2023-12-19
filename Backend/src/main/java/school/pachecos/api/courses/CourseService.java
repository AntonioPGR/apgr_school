package school.pachecos.api.courses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import school.pachecos.api.courses.dtos.CourseCreateDTO;
import school.pachecos.api.courses.dtos.CourseReturnDTO;
import school.pachecos.api.courses.dtos.CourseEditDTO;

@Service
public class CourseService {

	@Autowired
	CourseRepository course_repository;

	public Page<CourseReturnDTO> getCoursesPerPage(Pageable pageable) {
		Page<CourseEntity> courses_entities = course_repository.findAll(pageable);
		return courses_entities.map(CourseReturnDTO::new);
	}

	public CourseReturnDTO getCourseByID(Long id) {
		CourseEntity course = course_repository.getReferenceById(id);
		return new CourseReturnDTO(course);
	}

	public CourseReturnDTO createCourse(CourseCreateDTO create_info) {
		CourseEntity new_course = new CourseEntity(create_info);
		course_repository.save(new_course);
		return new CourseReturnDTO(new_course);
	}

	public CourseReturnDTO editCourse(CourseEditDTO edit_info) {
		CourseEntity edited_course = course_repository.getReferenceById(edit_info.id());
		edited_course.update(edit_info);
		return new CourseReturnDTO(edited_course);
	}

	public void deleteCourse(Long id) {
		CourseEntity deleted_course = course_repository.getReferenceById(id);
		course_repository.delete(deleted_course);
	}
}

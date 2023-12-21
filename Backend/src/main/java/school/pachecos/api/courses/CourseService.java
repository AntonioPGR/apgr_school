package school.pachecos.api.courses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import school.pachecos.api.courses.dtos.CourseCreateDTO;
import school.pachecos.api.courses.dtos.CourseReturnDTO;
import school.pachecos.api.courses.dtos.CourseUpdateDTO;
import school.pachecos.commons.classes.BaseApiService;

@Service
public class CourseService extends BaseApiService<CourseEntity, CourseCreateDTO, CourseUpdateDTO, CourseReturnDTO> {

	@Autowired
	CourseRepository course_repository;

	@Override
	public CourseReturnDTO convertToReturnDTO(CourseEntity entity) {
		return new CourseReturnDTO(entity);
	}

	@Override
	public CourseEntity convertToEntity(CourseCreateDTO create_dto) {
		return new CourseEntity(create_dto);
	}

}

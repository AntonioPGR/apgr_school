package school.pachecos.api.courses;

import org.springframework.stereotype.Service;
import school.pachecos.api.courses.dtos.CourseCreateDTO;
import school.pachecos.api.courses.dtos.CourseReturnDTO;
import school.pachecos.api.courses.dtos.CourseUpdateDTO;
import school.pachecos.infra.commons.classes.BaseApiService;

@Service
public class CourseService extends BaseApiService<CourseEntity, CourseCreateDTO, CourseUpdateDTO, CourseReturnDTO> {

	@Override
	public CourseReturnDTO convertToReturnDTO(CourseEntity entity) {
		return new CourseReturnDTO(entity);
	}

	@Override
	public CourseEntity convertToEntity(CourseCreateDTO create_dto) {
		return new CourseEntity(create_dto);
	}

}

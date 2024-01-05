package school.pachecos.api.courses;

import org.springframework.stereotype.Service;
import school.pachecos.api.courses.dtos.CourseCreateDTO;
import school.pachecos.api.courses.dtos.CourseReturnDTO;
import school.pachecos.api.courses.dtos.CourseUpdateDTO;
import school.pachecos.infra.commons.classes.BaseService;

@Service
public class CourseService extends BaseService<
	CourseEntity,
	CourseCreateDTO,
	CourseCreateDTO,
	CourseUpdateDTO,
	CourseUpdateDTO,
	CourseReturnDTO,
	CourseRepository
> {

	@Override
	protected CourseCreateDTO convertToCreateDTO(CourseCreateDTO dto) {
		return dto;
	}

	@Override
	protected CourseUpdateDTO convertToUpdateDTO(CourseUpdateDTO dto) {
		return dto;
	}

	@Override
	public CourseReturnDTO convertToReturnDTO(CourseEntity entity) {
		return new CourseReturnDTO(entity);
	}

	@Override
	public CourseEntity convertToEntity(CourseCreateDTO create_dto) {
		return new CourseEntity(create_dto);
	}

}

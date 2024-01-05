package school.pachecos.api.courses;

import org.springframework.web.bind.annotation.*;
import school.pachecos.api.courses.dtos.CourseReturnDTO;
import school.pachecos.api.courses.dtos.CourseCreateDTO;
import school.pachecos.api.courses.dtos.CourseUpdateDTO;
import school.pachecos.infra.commons.classes.BaseController;


@RestController
@RequestMapping("/courses")
public class CourseController extends BaseController<
	CourseCreateDTO,
	CourseUpdateDTO,
	CourseReturnDTO,
	CourseService
> {

	@Override
	public String getBaseUriPath() {
		return "/courses";
	}
}

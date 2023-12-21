package school.pachecos.api.courses;

import org.springframework.web.bind.annotation.*;
import school.pachecos.api.courses.dtos.CourseReturnDTO;
import school.pachecos.api.courses.dtos.CourseCreateDTO;
import school.pachecos.api.courses.dtos.CourseUpdateDTO;
import school.pachecos.commons.classes.BaseApiController;


@RestController
@RequestMapping("/courses")
public class CourseController extends BaseApiController<CourseEntity, CourseCreateDTO, CourseUpdateDTO, CourseReturnDTO, CourseService> {

}

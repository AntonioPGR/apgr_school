package school.pachecos.api.lessons;


import org.springframework.web.bind.annotation.*;
import school.pachecos.api.lessons.dto.*;
import school.pachecos.infra.commons.classes.BaseController;


@RestController
@RequestMapping("/lessons")
public class LessonController extends BaseController<
	LessonCreateIdDTO,
	LessonUpdateIdDTO,
	LessonReturnDTO,
	LessonService
> {

	@Override
	public String getBaseUriPath() {
		return "/lessons";
	}
}

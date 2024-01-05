package school.pachecos.api.enrollments;


import org.springframework.web.bind.annotation.*;
import school.pachecos.api.enrollments.dtos.*;
import school.pachecos.infra.commons.classes.BaseController;


@RestController
@RequestMapping("/enrollments")
public class EnrollmentController extends BaseController<
	EnrollmentCreateIdDTO,
	EnrollmentUpdateIdDTO,
	EnrollmentReturnDTO,
	EnrollmentService
> {

	@Override
	public String getBaseUriPath() {
		return "/enrollments";
	}

}

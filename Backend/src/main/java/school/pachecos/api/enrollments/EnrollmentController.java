package school.pachecos.api.enrollments;


import org.springframework.web.bind.annotation.*;
import school.pachecos.api.enrollments.dtos.*;
import school.pachecos.infra.commons.classes.BaseApiController;


@RestController
@RequestMapping("/enrollments")
public class EnrollmentController extends BaseApiController<EnrollmentEntity, EnrollmentCreateEntityDTO, EnrollmentUpdateEntityDTO, EnrollmentReturnDTO, EnrollmentService> {
	@Override
	protected String getBaseUriPath() {
		return "/enrollments";
	}
}

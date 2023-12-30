package school.pachecos.api.enrollments;


import org.springframework.web.bind.annotation.*;
import school.pachecos.api.enrollments.dtos.*;
import school.pachecos.infra.commons.classes.BaseApiController;
import school.pachecos.infra.commons.classes.StaticApiController;


@RestController
@RequestMapping("/enrollments")
public class EnrollmentController extends StaticApiController<EnrollmentEntity, EnrollmentCreateEntityDTO, EnrollmentUpdateEntityDTO, EnrollmentReturnDTO, EnrollmentService> {
	@Override
	protected String getBaseUriPath() {
		return "/enrollments";
	}
}

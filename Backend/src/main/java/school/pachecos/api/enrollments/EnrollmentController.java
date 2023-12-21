package school.pachecos.api.enrollments;


import org.springframework.web.bind.annotation.*;
import school.pachecos.api.enrollments.dtos.*;
import school.pachecos.commons.classes.BaseApiController;


@RestController
@RequestMapping("/enrollments")
public class EnrollmentController extends BaseApiController<EnrollmentEntity, EnrollmentCreateEntityDTO, EnrollmentUpdateEntityDTO, EnrollmentReturnDTO, EnrollmentService> {
}

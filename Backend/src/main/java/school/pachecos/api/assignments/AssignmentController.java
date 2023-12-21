package school.pachecos.api.assignments;

import org.springframework.web.bind.annotation.*;
import school.pachecos.api.assignments.dtos.*;
import school.pachecos.commons.classes.BaseApiController;


@RestController
@RequestMapping("/students/tasks")
public class AssignmentController extends BaseApiController<AssignmentEntity, AssignmentCreateEntityDTO, AssignmentUpdateEntityDTO, AssignmentReturnDTO, AssignmentService> {
}

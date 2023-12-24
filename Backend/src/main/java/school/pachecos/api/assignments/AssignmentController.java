package school.pachecos.api.assignments;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.pachecos.api.assignments.dtos.*;
import school.pachecos.infra.commons.classes.BaseApiController;

import java.net.URI;


@RestController
@RequestMapping("/students/tasks")
public class AssignmentController extends BaseApiController<AssignmentEntity, AssignmentCreateEntityDTO, AssignmentUpdateEntityDTO, AssignmentReturnDTO, AssignmentService> {
	@Override
	protected String getBaseUriPath() {
		return "/students/tasks";
	}


}

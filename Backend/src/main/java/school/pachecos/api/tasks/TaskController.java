package school.pachecos.api.tasks;

import org.springframework.web.bind.annotation.*;
import school.pachecos.api.tasks.dtos.*;
import school.pachecos.infra.commons.classes.BaseApiController;


@RestController
@RequestMapping("/tasks")
public class TaskController extends BaseApiController<TaskEntity, TaskCreateEntityDTO, TaskUpdateEntityDTO, TaskReturnDTO, TaskService> {
	@Override
	protected String getBaseUriPath() {
		return "/tasks";
	}
}

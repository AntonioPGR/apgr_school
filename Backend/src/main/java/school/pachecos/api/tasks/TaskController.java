package school.pachecos.api.tasks;

import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.pachecos.api.tasks.dtos.*;
import school.pachecos.commons.classes.BaseApiController;
import school.pachecos.commons.dtos.IdDTO;

import java.net.URI;
import java.net.URISyntaxException;


@RestController
@RequestMapping("/tasks")
public class TaskController extends BaseApiController<TaskEntity, TaskCreateEntityDTO, TaskUpdateEntityDTO, TaskReturnDTO, TaskService> {
}

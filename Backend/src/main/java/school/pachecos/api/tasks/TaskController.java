package school.pachecos.api.tasks;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.pachecos.api.assignments.AssignmentController;
import school.pachecos.api.assignments.AssignmentService;
import school.pachecos.api.assignments.dtos.AssignmentCreateLessonIdDTO;
import school.pachecos.api.lessons.LessonEntity;
import school.pachecos.api.lessons.LessonRepository;
import school.pachecos.api.tasks.dtos.*;
import school.pachecos.infra.commons.classes.BaseController;
import school.pachecos.infra.uri.URIService;

import java.net.URI;


@RestController
@RequestMapping("/tasks")
public class TaskController extends BaseController<
  TaskCreateIdDTO,
  TaskUpdateIdDTO,
  TaskReturnDTO,
  TaskService
> {
	@Override
	public String getBaseUriPath() {
		return "/tasks";
	}
}

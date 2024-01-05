package school.pachecos.api.assignments;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.pachecos.api.assignments.dtos.AssignmentCreateIdDTO;
import school.pachecos.api.assignments.dtos.AssignmentReturnDTO;
import school.pachecos.api.assignments.dtos.AssignmentUpdateIdDTO;
import school.pachecos.api.lessons.LessonRepository;
import school.pachecos.api.tasks.TaskRepository;
import school.pachecos.infra.commons.classes.BaseController;
import school.pachecos.infra.uri.URIService;
import java.net.URI;


@RestController
@RequestMapping("/assignments")
public class AssignmentController extends BaseController<
  AssignmentCreateIdDTO,
  AssignmentUpdateIdDTO,
  AssignmentReturnDTO,
  AssignmentService
> {

  @Override
  public String getBaseUriPath() {
    return "/students/tasks";
  }
}

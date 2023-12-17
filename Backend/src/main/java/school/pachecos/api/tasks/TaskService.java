package school.pachecos.api.tasks;

import jdk.jshell.Snippet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import school.pachecos.api.tasks.dtos.TaskCreateDTO;
import school.pachecos.api.tasks.dtos.TaskReturnInfoDTO;


@Service
public class TaskService {

	@Autowired
	private TaskRepository task_repository;

	public Page<TaskReturnInfoDTO> listTasksPerPage(Pageable pageable){
		Page<TaskEntity> tasks_page = task_repository.findAll(pageable);
		return tasks_page.map(TaskReturnInfoDTO::new);
	}

	public TaskReturnInfoDTO findTaskById(Long id) {
		TaskEntity task_entity = task_repository.getReferenceById(id);
		return new TaskReturnInfoDTO(task_entity);
	}

	/*public TaskReturnInfoDTO createTask(TaskCreateDTO new_task) {
	}*/
}

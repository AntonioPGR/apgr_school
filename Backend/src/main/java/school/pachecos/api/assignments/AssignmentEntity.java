package school.pachecos.api.assignments;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import school.pachecos.api.assignments.dtos.AssignmentCreateEntityDTO;
import school.pachecos.api.assignments.dtos.AssignmentUpdateEntityDTO;
import school.pachecos.api.tasks.TaskEntity;
import school.pachecos.api.users.UserEntity;
import school.pachecos.infra.commons.classes.BaseApiEntity;

@Entity(name = "StudentsTasksEntity")
@Table(name = "Students_Tasks")
@Getter
@Setter(AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class AssignmentEntity extends BaseApiEntity<AssignmentUpdateEntityDTO> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull
	private int is_done = 0;
	@Nullable
	private String response_location;
	@NotNull
	@ManyToOne
	@JoinColumn(name = "task")
	private TaskEntity task;
	@NotNull
	@ManyToOne
	@JoinColumn(name = "student")
	private UserEntity student;

	public AssignmentEntity(AssignmentCreateEntityDTO dto){
		this.is_done = dto.is_done();
		this.response_location = dto.response_location();
		this.task = dto.task();
		this.student = dto.student();
	}

	public void update(AssignmentUpdateEntityDTO studentsTasksDto) {
		this.is_done = studentsTasksDto.is_done() != null? studentsTasksDto.is_done() : is_done;
		this.response_location = studentsTasksDto.response_location() != null ? studentsTasksDto.response_location() : this.response_location;
		this.task = studentsTasksDto.task() != null ? studentsTasksDto.task() : this.task;
		this.student = studentsTasksDto.student() != null ? studentsTasksDto.student() : this.student;
	}
}

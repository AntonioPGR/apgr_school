package school.pachecos.api.students_tasks;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import school.pachecos.api.tasks.TaskEntity;
import school.pachecos.api.users.UserEntity;

@Entity(name = "StudentsTasksEntity")
@Table(name = "Students_Tasks")
@Getter
@Setter(AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class StudentsTasksEntity {

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

}

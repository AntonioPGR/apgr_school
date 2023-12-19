package school.pachecos.api.tasks;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import school.pachecos.api.lessons.LessonEntity;
import school.pachecos.api.tasks.dtos.TaskCreateWithEntitiesDTO;
import school.pachecos.api.tasks.dtos.TaskEditWithEntitiesDTO;
import school.pachecos.api.users.UserEntity;

import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "TaskEntity")
@Table(name = "Tasks")
@Getter
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class TaskEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank
	@NotNull
	private String title;
	@NotNull
	private LocalDateTime dueDate;
	@Nullable
	private String description;

	@ManyToOne
	@JoinColumn(name = "lesson", nullable = false)
	private LessonEntity lesson;
	@ManyToMany
	@JoinTable(
		name = "Users_Tasks",
		joinColumns = @JoinColumn(name="task"),
		inverseJoinColumns = @JoinColumn(name = "student")
	)
	private List<UserEntity> students;

	public TaskEntity(TaskCreateWithEntitiesDTO taskDTO) {
		this.title = taskDTO.title();
		this.dueDate = taskDTO.dueDate();
		this.description = taskDTO.description();
		this.lesson = taskDTO.lesson();
	}


	public void update(TaskEditWithEntitiesDTO edited_task) {
		this.setTitle(edited_task.title() != null ? edited_task.title() : this.getTitle());
		this.setDueDate(edited_task.due_date() != null ? edited_task.due_date() : this.getDueDate());
		this.setDescription(edited_task.description() != null ? edited_task.description() : this.getDescription());
		this.setLesson(edited_task.lesson() != null ? edited_task.lesson() : this.getLesson());
	}

}

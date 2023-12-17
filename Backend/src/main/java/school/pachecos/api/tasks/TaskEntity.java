package school.pachecos.api.tasks;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import school.pachecos.api.lessons.LessonEntity;
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


}

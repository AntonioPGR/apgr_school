package school.pachecos.api.lessons;

import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import school.pachecos.api.courses.CourseEntity;
import school.pachecos.api.lessons.dto.LessonCreateEntityDTO;
import school.pachecos.api.lessons.dto.LessonUpdateEntityDTO;
import school.pachecos.api.tasks.TaskEntity;
import school.pachecos.api.users.UserEntity;
import school.pachecos.infra.commons.interfaces.EntityInterface;

import java.time.LocalDateTime;
import java.util.List;

import java.util.stream.Collectors;

@Entity(name = "LessonEntity")
@Table(name = "lessons")
@Getter
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class LessonEntity implements EntityInterface<LessonUpdateEntityDTO> {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank
	@Length(max = 100)
	private String name;
	@NotNull
	@Future
	private LocalDateTime datetime;
	@NotNull
	private int duration_in_minutes;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "professor", nullable = false)
	private UserEntity professor;

	@OneToMany(mappedBy = "lesson", cascade = CascadeType.ALL)
	private List<TaskEntity> tasks;

	@ManyToMany(mappedBy = "lessons", cascade = CascadeType.ALL)
	private List<CourseEntity> courses;

	public LessonEntity(LessonCreateEntityDTO user_info) {
		this.setName(user_info.name());
		this.setDatetime(user_info.datetime());
		this.setProfessor(user_info.professor());
	}

	public void update(LessonUpdateEntityDTO lessonUpdateDto) {
		setName(lessonUpdateDto.name() != null ? lessonUpdateDto.name() : name);
		setDatetime(lessonUpdateDto.datetime() != null ? lessonUpdateDto.datetime() : datetime);
		setProfessor(lessonUpdateDto.professor() != null ? lessonUpdateDto.professor() : professor);
	}

	public List<UserEntity> getStudents() {
		return courses.stream()
				.flatMap(course -> course.getStudents().stream())
				.collect(Collectors.toList());
	}
}

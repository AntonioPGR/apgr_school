package school.pachecos.api.lessons;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import school.pachecos.api.courses.CourseEntity;
import school.pachecos.api.lessons.dto.LessonCreateProfessorDTO;
import school.pachecos.api.lessons.dto.LessonUpdateProfessorDTO;
import school.pachecos.api.tasks.TaskEntity;
import school.pachecos.api.users.UserEntity;
import java.time.LocalDateTime;
import java.util.List;

@Entity(name="LessonEntity")
@Table(name="lessons")
@Getter
@Setter(AccessLevel.PRIVATE)
@EqualsAndHashCode(of="id")
@NoArgsConstructor
@AllArgsConstructor
public class LessonEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@NotBlank
	@Length(max = 100)
	private String name;
	@NotNull
	private LocalDateTime datetime;
	@NotNull
	private int duration_in_minutes;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "professor", nullable = false)
	private UserEntity professor;

	@OneToMany(mappedBy = "lesson")
	private List<TaskEntity> tasks;

	@ManyToMany(mappedBy = "lessons")
	private List<CourseEntity> courses;

	public LessonEntity(LessonCreateProfessorDTO user_info){
		this.setName(user_info.name());
		this.setDatetime(user_info.datetime());
		this.setProfessor(user_info.professor());
	}

	public void update(LessonUpdateProfessorDTO lessonUpdateDto) {
		setName(lessonUpdateDto.name() != null? lessonUpdateDto.name() : name);
		setDatetime(lessonUpdateDto.datetime() != null? lessonUpdateDto.datetime() : datetime);
		setProfessor(lessonUpdateDto.professor() != null? lessonUpdateDto.professor() : professor);
	}
}

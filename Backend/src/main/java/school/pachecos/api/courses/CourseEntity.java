package school.pachecos.api.courses;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import school.pachecos.api.courses.dtos.CourseUpdateDTO;
import school.pachecos.api.courses.dtos.CourseCreateDTO;
import school.pachecos.api.lessons.LessonEntity;
import school.pachecos.api.users.UserEntity;
import school.pachecos.infra.commons.interfaces.EntityInterface;

import java.util.List;


@Entity(name = "CourseEntity")
@Table(name = "Courses")
@Getter
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class CourseEntity implements EntityInterface<CourseUpdateDTO> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull
	@NotBlank
	private String name;
	@Nullable
	private String description;

	@ManyToMany
	@JoinTable(name = "Course_Students", joinColumns = @JoinColumn(name = "course"), inverseJoinColumns = @JoinColumn(name = "student"))
	private List<UserEntity> students;
	@ManyToMany
	@JoinTable(name = "Course_Lessons", joinColumns = @JoinColumn(name = "course"), inverseJoinColumns = @JoinColumn(name = "lesson"))
	private List<LessonEntity> lessons;

	public CourseEntity(CourseCreateDTO course_info) {
		this.setName(course_info.name());
		this.setDescription(course_info.description());
	}

	public void update(CourseUpdateDTO edit_info) {
		this.setName(edit_info.name() != null ? edit_info.name() : name);
		this.setDescription(edit_info.description() != null ? edit_info.description() : description);
	}

}

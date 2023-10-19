package apgr_school.api.models.courses;

import apgr_school.api.models.classes.Class;
import apgr_school.api.models.students.Student;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "courses")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Course{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id", nullable = false)
	private Long id;
	@Column(name = "Name", nullable = false)
	private String name;
	@Column(name = "Description", nullable = true)
	private String description;
	@ManyToMany
	@JoinTable(
			name = "students_enrolment_in_courses",
			joinColumns = @JoinColumn(name = "StudentID", nullable = false),
			inverseJoinColumns = @JoinColumn(name = "CourseID")
	)
	private List<Student> students;
	@ManyToMany
	@JoinTable(
			name = "course_has_classes",
			joinColumns = @JoinColumn(name = "ClassID", nullable = false),
			inverseJoinColumns = @JoinColumn(name = "CourseID")
	)
	private List<Class> classes;
}

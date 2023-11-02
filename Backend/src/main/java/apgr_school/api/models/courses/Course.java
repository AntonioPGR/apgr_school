package apgr_school.api.models.courses;

import apgr_school.api.models.school_classes.SchoolClass;
import apgr_school.api.models.students.Student;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "Courses")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Course{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NotNull
	private Long id;
	@NotNull
	private String name;
	@Nullable
	private String description;
	@ManyToMany
	@JoinTable(
			name = " StudentsEnrolmentsInCourses",
			joinColumns = @JoinColumn(name = "studentId", nullable = false),
			inverseJoinColumns = @JoinColumn(name = "courseId")
	)
	private List<Student> students;
	@ManyToMany
	@JoinTable(
			name = "course_has_classes",
			joinColumns = @JoinColumn(name = "ClassID", nullable = false),
			inverseJoinColumns = @JoinColumn(name = "CourseID")
	)
	private List<SchoolClass> school_classes;
}

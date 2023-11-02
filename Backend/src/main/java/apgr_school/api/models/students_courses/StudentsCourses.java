package apgr_school.api.models.students_courses;

import apgr_school.api.models.courses.Course;
import apgr_school.api.models.students.Student;
import apgr_school.api.models.tasks.Task;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "StudentsEnrolmentsInCourses")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class StudentsCourses {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NotNull
	private Long id;
	@ManyToOne
	@NotNull
	@JoinColumn(name = "student", referencedColumnName = "id")
	Student studentId;
	@ManyToOne
	@NotNull
	@JoinColumn(name = "course", referencedColumnName = "id")
	Course courseId;
	@NotNull
	@Temporal(TemporalType.DATE)
	Date enrolment_date;
	@Temporal(TemporalType.DATE)
	@Nullable
	Date end_date;
}

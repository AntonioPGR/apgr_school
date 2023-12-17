package school.pachecos.api.course_students;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import school.pachecos.api.courses.CourseEntity;
import school.pachecos.api.users.UserEntity;

import java.sql.Date;

@Entity(name = "CourseStudentsEntity")
@Table(name = "Course_Students")
@Getter
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class CourseStudentsEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	@JoinColumn(name = "course", nullable = false)
	private CourseEntity course;
	@ManyToOne
	@JoinColumn(name = "student", nullable = false)
	private UserEntity student;
	@NotNull
	private Date enrollment_date;
	@Nullable
	private Date end_date;
}

package apgr_school.api.students;

import apgr_school.api.courses.Course;
import apgr_school.api.users.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "students")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Student {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id")
	private Long id;
	@OneToOne
	@JoinColumn(name = "UserID", referencedColumnName = "Id", nullable = false)
	private User User;
	@ManyToMany(mappedBy = "students")
	private List<Course> courses;
}

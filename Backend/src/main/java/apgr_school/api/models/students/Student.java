package apgr_school.api.models.students;

import apgr_school.api.models.courses.Course;
import jakarta.persistence.*;
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
	private apgr_school.api.models.users.User User;
	@ManyToMany(mappedBy = "students")
	private List<Course> courses;
}

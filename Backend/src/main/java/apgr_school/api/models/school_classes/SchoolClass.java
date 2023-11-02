package apgr_school.api.models.school_classes;

import apgr_school.api.models.courses.Course;
import apgr_school.api.models.tasks.Task;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "SchoolClasses")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class SchoolClass {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NotNull
	private Long id;
	@NotNull
	private String name;
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	private Timestamp date_time;
	@OneToMany(mappedBy = "school_class")
	private List<Task> tasks;
	@ManyToMany(mappedBy = "school_classes")
	private List<Course> courses;
}

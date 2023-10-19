package apgr_school.api.models.tasks;

import apgr_school.api.models.classes.Class;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Entity
@Table(name = "tasks")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Task {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id")
	private Long id;
	@Column(name = "Title", nullable = false)
	private String title;
	@Column(name = "Description", nullable = true)
	private String description;
	@Column(name = "DueDate", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date due_date;
	@ManyToOne
	@JoinColumn(name = "Class", referencedColumnName = "Id", nullable = false)
	private Class reference_class;
}

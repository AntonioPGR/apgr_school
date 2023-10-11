package apgr_school.api.tasks;

import jakarta.persistence.*;

import java.sql.Date;

@Entity(name = "tasks")
public class Task {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id")
	private int id;
	@Column(name = "Title")
	private String title;
	@Column(name = "Description")
	private String description;
	@Column(name = "DueDate")
	@Temporal(TemporalType.DATE)
	private Date due_date;
}

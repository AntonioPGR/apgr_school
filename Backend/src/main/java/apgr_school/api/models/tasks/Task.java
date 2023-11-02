package apgr_school.api.models.tasks;

import apgr_school.api.models.school_classes.SchoolClass;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Entity
@Table(name = "Tasks")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Task {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NotNull
	private Long id;
	@NotNull
	private String title;
	@Nullable
	@Column(name = "Description")
	private String description;
	@NotNull
	@Column(name = "DueDate")
	@Temporal(TemporalType.DATE)
	private Date due_date;
	@NotNull
	@ManyToOne
	@JoinColumn(name = "Class", referencedColumnName = "id")
	private SchoolClass school_class;
}

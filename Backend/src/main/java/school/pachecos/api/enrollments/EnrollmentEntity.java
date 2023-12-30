package school.pachecos.api.enrollments;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.PastOrPresent;
import lombok.*;
import school.pachecos.api.courses.CourseEntity;
import school.pachecos.api.enrollments.dtos.EnrollmentCreateEntityDTO;
import school.pachecos.api.enrollments.dtos.EnrollmentUpdateEntityDTO;
import school.pachecos.api.users.UserEntity;
import school.pachecos.infra.commons.classes.BaseApiEntity;

import java.sql.Date;
import java.time.LocalDate;
import java.util.UUID;

@Entity(name = "EnrollmentEntity")
@Table(name = "Course_Students")
@Getter
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class EnrollmentEntity extends BaseApiEntity<EnrollmentUpdateEntityDTO> {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private UUID id;
	@ManyToOne
	@JoinColumn(name = "course", nullable = false)
	private CourseEntity course;
	@ManyToOne
	@JoinColumn(name = "student", nullable = false)
	private UserEntity student;
	@NotNull
	@PastOrPresent
	private LocalDate enrollment_date;
	@Nullable
	@Past
	private LocalDate end_date;

	public EnrollmentEntity(EnrollmentCreateEntityDTO entity_dto) {
		this.course = entity_dto.course();
		this.student = entity_dto.student();
		this.enrollment_date = entity_dto.enrollment_date();
	}

	public void update(EnrollmentUpdateEntityDTO edit_dto) {
		this.course = edit_dto.course() != null ? edit_dto.course() : course;
		this.student = edit_dto.student() != null ? edit_dto.student() : student;
		this.enrollment_date = edit_dto.enrollment_date() != null ? edit_dto.enrollment_date() : enrollment_date;
		this.end_date = edit_dto.end_date() != null ? edit_dto.end_date() : end_date;
	}
}

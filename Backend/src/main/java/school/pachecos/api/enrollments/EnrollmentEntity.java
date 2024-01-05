package school.pachecos.api.enrollments;

import java.time.LocalDate;

import jakarta.annotation.Nullable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import school.pachecos.api.courses.CourseEntity;
import school.pachecos.api.enrollments.dtos.EnrollmentCreateEntityDTO;
import school.pachecos.api.enrollments.dtos.EnrollmentUpdateEntityDTO;
import school.pachecos.api.users.UserEntity;
import school.pachecos.infra.commons.interfaces.EntityInterface;

@Entity(name = "EnrollmentEntity")
@Table(name = "Course_Students")
@Getter
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class EnrollmentEntity implements EntityInterface<EnrollmentUpdateEntityDTO> {
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

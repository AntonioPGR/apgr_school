package school.pachecos.api.enrollments;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import school.pachecos.api.courses.CourseEntity;
import school.pachecos.api.enrollments.dtos.EnrollmentCreateEntityDTO;
import school.pachecos.api.enrollments.dtos.EnrollmentUpdateEntityDTO;
import school.pachecos.api.users.UserEntity;
import school.pachecos.commons.classes.BaseApiEntity;

import java.sql.Date;

@Entity(name = "EnrollmentEntity")
@Table(name = "Course_Students")
@Getter
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class EnrollmentEntity extends BaseApiEntity<EnrollmentUpdateEntityDTO> {
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

	public EnrollmentEntity(EnrollmentCreateEntityDTO entity_dto){
		this.course = entity_dto.course();
		this.student = entity_dto.student();
		this.enrollment_date = entity_dto.enrollment_date();
		this.end_date = entity_dto.end_date();
	}

	public void update(EnrollmentUpdateEntityDTO edit_dto) {
		this.course = edit_dto.course() != null? edit_dto.course() : course;
		this.student = edit_dto.student() != null? edit_dto.student() : student;
		this.enrollment_date = edit_dto.enrollment_date() != null? edit_dto.enrollment_date() : enrollment_date;
		this.end_date = edit_dto.end_date() != null? edit_dto.end_date() : end_date;
	}
}

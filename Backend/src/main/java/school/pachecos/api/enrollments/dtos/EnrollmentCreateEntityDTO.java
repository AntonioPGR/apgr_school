package school.pachecos.api.enrollments.dtos;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import org.springframework.cglib.core.Local;
import school.pachecos.api.courses.CourseEntity;
import school.pachecos.api.users.UserEntity;

import java.sql.Date;
import java.time.LocalDate;

public record EnrollmentCreateEntityDTO(
		@NotNull CourseEntity course,
		@NotNull UserEntity student,
		@NotNull LocalDate enrollment_date,
		@Nullable LocalDate end_date
) {

	public EnrollmentCreateEntityDTO(EnrollmentCreateIdDTO dto, CourseEntity course, UserEntity student){
		this(course, student, dto.enrollment_date() != null? dto.enrollment_date() : LocalDate.now(), dto.end_date());
	}

}

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
		@NotNull LocalDate enrollment_date
) {

	public EnrollmentCreateEntityDTO(CourseEntity course, UserEntity student){
		this(course, student, LocalDate.now());
	}

}

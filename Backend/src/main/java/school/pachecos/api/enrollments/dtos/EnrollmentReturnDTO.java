package school.pachecos.api.enrollments.dtos;

import jakarta.annotation.Nullable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import school.pachecos.api.courses.dtos.CourseReturnDTO;
import school.pachecos.api.enrollments.EnrollmentEntity;
import school.pachecos.api.users.dtos.UserReturnInfoDTO;

import java.sql.Date;


public record EnrollmentReturnDTO(
		@NotNull Long id,
		@NotNull CourseReturnDTO course,
		@NotNull UserReturnInfoDTO student,
		@NotNull Date enrollment_date,
		@Nullable Date end_date
) {

	public EnrollmentReturnDTO(EnrollmentEntity entity){
		this(
			entity.getId(),
			new CourseReturnDTO(entity.getCourse()),
			new UserReturnInfoDTO(entity.getStudent()),
			entity.getEnrollment_date(),
			entity.getEnd_date()
		);
	}

}

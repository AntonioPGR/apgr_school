package school.pachecos.users.dtos;

import jakarta.validation.constraints.NotNull;
import school.pachecos.users.UserEntity;

public record UserIdDTO(
		@NotNull
		Long id
) {

	public UserIdDTO(UserEntity user_entity){
		this(
				user_entity.getId()
		);
	}

}

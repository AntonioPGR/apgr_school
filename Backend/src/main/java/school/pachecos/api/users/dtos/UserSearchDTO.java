package school.pachecos.api.users.dtos;

import jakarta.annotation.Nullable;
import school.pachecos.api.users.UserEntity;

import java.time.LocalDate;

public record UserSearchDTO(
		@Nullable
		String name,
		@Nullable
		LocalDate birth_date,
		@Nullable
		String email,
		@Nullable
		String cellphone
) {

	public UserSearchDTO(UserEntity user_entity){
		this(
				user_entity.getName(),
				user_entity.getBirth_date(),
				user_entity.getEmail(),
				user_entity.getCellphone()
		);
	}

}

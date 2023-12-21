package school.pachecos.api.users.dtos;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import school.pachecos.api.users.UserEntity;
import school.pachecos.commons.interfaces.BaseUpdateDTO;

import java.time.LocalDate;

public record UserUpdateDTO (
		@NotNull
		Long id,
		@Nullable
		String name,
		@Nullable
		LocalDate birth_date,
		@Nullable
		String email,
		@Nullable
		String cellphone,
		@Nullable
		String gender,
		@Nullable
		String photo_path,
		@Nullable
		String permissions,
		@Nullable
		String password
) implements BaseUpdateDTO {

	public UserUpdateDTO(UserEntity user_entity){
		this(
				user_entity.getId(),
				user_entity.getName(),
				user_entity.getBirth_date(),
				user_entity.getEmail(),
				user_entity.getCellphone(),
				user_entity.getGender(),
				user_entity.getPhoto_path(),
				user_entity.getPermissions(),
				user_entity.getPassword()
		);
	}

	@Override
	public Long getId() {
		return null;
	}
}

package school.pachecos.api.users.dtos;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import school.pachecos.api.users.UserEntity;

import java.time.LocalDate;

public record UserCreateDTO(
		@NotBlank
		String name,
		@NotNull
		LocalDate birth_date,
		@NotBlank
		String email,
		@NotBlank
		String cellphone,
		@NotBlank
		String gender,
		@Nullable
		String photo_path,
		@NotBlank
		String password
) {

	public UserCreateDTO(UserEntity user_entity){
		this(
				user_entity.getName(),
				user_entity.getBirth_date(),
				user_entity.getEmail(),
				user_entity.getCellphone(),
				user_entity.getGender(),
				user_entity.getPhoto_path(),
				user_entity.getPassword()
		);
	}

}

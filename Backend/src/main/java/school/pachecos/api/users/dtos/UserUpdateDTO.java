package school.pachecos.api.users.dtos;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import school.pachecos.api.users.UserEntity;
import school.pachecos.infra.commons.interfaces.BaseUpdateDTO;

import java.time.LocalDate;
import java.util.UUID;

public record UserUpdateDTO (
		@NotNull
		UUID id,
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
}

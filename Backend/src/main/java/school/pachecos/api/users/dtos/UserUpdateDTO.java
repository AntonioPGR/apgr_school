package school.pachecos.api.users.dtos;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record UserUpdateDTO(
		@Nullable String name,
		@Nullable LocalDate birth_date,
		@Nullable String email,
		@Nullable String cellphone,
		@Nullable String gender,
		@Nullable String photo_path,
		@Nullable String permissions,
		@Nullable String password) {
}

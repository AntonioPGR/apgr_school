package school.pachecos.users.dtos;

import school.pachecos.users.UserEntity;

import java.time.LocalDate;

public record UserReturnInfoDTO(
		Long id,
		String name,
		LocalDate birth_date,
		String email,
		String cellphone,
		String gender,
		String photo_path,
		String permissions
) {

	public UserReturnInfoDTO(UserEntity user_entity){
		this(
				user_entity.getId(),
				user_entity.getName(),
				user_entity.getBirth_date(),
				user_entity.getEmail(),
				user_entity.getCellphone(),
				user_entity.getGender(),
				user_entity.getPhoto_path(),
				user_entity.getPermissions()
		);
	}

}

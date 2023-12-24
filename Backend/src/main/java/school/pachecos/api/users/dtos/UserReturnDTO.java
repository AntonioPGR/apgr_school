package school.pachecos.api.users.dtos;

import school.pachecos.api.users.UserEntity;
import school.pachecos.infra.commons.interfaces.BaseReturnDTO;
import java.time.LocalDate;


public record UserReturnDTO(
		Long id,
		String name,
		LocalDate birth_date,
		String email,
		String cellphone,
		String gender,
		String photo_path
) implements BaseReturnDTO {

	public UserReturnDTO(UserEntity user_entity){
		this(
				user_entity.getId(),
				user_entity.getName(),
				user_entity.getBirth_date(),
				user_entity.getEmail(),
				user_entity.getCellphone(),
				user_entity.getGender(),
				user_entity.getPhoto_path()
		);
	}

}

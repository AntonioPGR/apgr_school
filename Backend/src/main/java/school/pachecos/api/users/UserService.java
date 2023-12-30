package school.pachecos.api.users;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import school.pachecos.api.users.dtos.*;
import school.pachecos.infra.commons.classes.BaseApiService;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Service
public class UserService extends BaseApiService<UserEntity, UserCreateDTO, UserUpdateDTO, UserReturnDTO> {

	@Autowired
	private UserRepository user_repository;

	public List<UserReturnDTO> searchInUsers(@Valid UserSearchDTO search) {
		Collection<UserEntity> user_list = user_repository.searchUsers(search.name(), search.birth_date(), search.email(),
				search.cellphone());
		return user_list.stream().map(UserReturnDTO::new).toList();
	}

	public void deactive(UUID id) {
		UserEntity user_entity = user_repository.getReferenceById(id);
		user_entity.desactivete();
	}

	public void activate(UUID id) {
		UserEntity user_entity = user_repository.getReferenceById(id);
		user_entity.activete();
	}

	@Override
	public UserReturnDTO convertToReturnDTO(UserEntity userEntity) {
		return new UserReturnDTO(userEntity);
	}

	@Override
	public UserEntity convertToEntity(UserCreateDTO create_dto) {
		return new UserEntity(create_dto);
	}

}

package school.pachecos.api.users;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import school.pachecos.api.users.dtos.*;
import school.pachecos.infra.commons.classes.BaseService;

import java.util.Collection;
import java.util.List;

@Service
public class UserService extends BaseService<
	UserEntity, 
	UserCreateDTO, 
	UserCreateDTO, 
	UserUpdateDTO, 
	UserUpdateDTO, 
	UserReturnDTO,
	UserRepository
> {

	@Autowired
	private UserRepository user_repository;

	List<UserReturnDTO> searchInUsers(@Valid UserSearchDTO search) {
		Collection<UserEntity> user_list = user_repository.searchUsers(search.name(), search.birth_date(), search.email(),
				search.cellphone());
		return user_list.stream().map(UserReturnDTO::new).toList();
	}

	void deactive(Long id) {
		UserEntity user_entity = user_repository.getReferenceById(id);
		user_entity.desactivete();
	}

	void activate(Long id) {
		UserEntity user_entity = user_repository.getReferenceById(id);
		user_entity.activete();
	}

	@Override
	protected UserCreateDTO convertToCreateDTO(UserCreateDTO dto){
		return dto;
	};

	@Override
	protected UserUpdateDTO convertToUpdateDTO(UserUpdateDTO dto) {
		return dto;
	};

	@Override
	protected UserReturnDTO convertToReturnDTO(UserEntity entity){
		return new UserReturnDTO(entity);
	};

	@Override
	protected UserEntity convertToEntity(UserCreateDTO dto) {
		return new UserEntity(dto);
	};

}
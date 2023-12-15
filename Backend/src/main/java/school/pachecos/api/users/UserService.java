package school.pachecos.api.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import school.pachecos.api.users.dtos.*;

import java.util.Collection;
import java.util.List;


@Service
public class UserService {

	@Autowired
	private UserRepository user_repository;

	public Page<UserReturnInfoDTO> listUsersPerPage(Pageable pageable){
		Page<UserEntity> list_user_entity = user_repository.findAll(pageable);
		return list_user_entity.map(UserReturnInfoDTO::new);
	}

	public UserReturnInfoDTO getUserReferenceById(long user_id){
		UserEntity user_entity = getUserEntityById(user_id);
		return new UserReturnInfoDTO(user_entity);
	}

	public UserIdDTO createUser(UserCreateDTO user_info){
		UserEntity user_entity = new UserEntity(user_info);
		user_repository.save(user_entity);
		return new UserIdDTO(user_entity.getId());
	}

	public List<UserReturnInfoDTO> searchInUsers(UserSearchDTO search){
		Collection<UserEntity> user_list = user_repository.searchUsers(search.name(), search.birth_date(), search.email(), search.cellphone());
		return user_list.stream().map(UserReturnInfoDTO::new).toList();
	}

	public void updateUser(UserUpdateDTO update_info){
		UserEntity user_entity = getUserEntityById(update_info.id());
		user_entity.update(update_info);
	}

	public void desactiveUser(long id){
		UserEntity user_entity = getUserEntityById(id);
		user_entity.desactivete();
	}

	public void activeUser(long id){
		UserEntity user_entity = getUserEntityById(id);
		user_entity.activete();
	}

	public void deleteUser(long id){
		UserEntity user_entity = getUserEntityById(id);
		user_repository.delete(user_entity);
	}


	// ENTITIES METHODS
	private UserEntity getUserEntityById(long user_id){
		return user_repository.getReferenceById(user_id);
	}

}

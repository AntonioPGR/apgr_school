package school.pachecos.users;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.pachecos.users.dtos.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserRepository user_repository;

	// GET
	@GetMapping
	public Page<UserReturnInfoDTO> listAllUsers(@PageableDefault(size=30, sort="name") Pageable pageable){
		Page<UserEntity> list_user_entity = user_repository.findAll(pageable);
		return list_user_entity.map(UserReturnInfoDTO::new);
	}

	@GetMapping("/{id}")
	public UserReturnInfoDTO getUser(@PathVariable("id") long user_id){
		Optional<UserEntity> users_search = user_repository.findById(user_id);
		if(users_search.isEmpty()){
			return null;
		}
		UserEntity user_entity = users_search.get();
		return new UserReturnInfoDTO(user_entity);
	}

	// POST
	@PostMapping
	@Transactional
	public ResponseEntity createUser(@RequestBody @Valid UserCreateDTO user_info) throws URISyntaxException {
		UserEntity user_entity = new UserEntity(user_info);
		user_repository.save(user_entity);
		return ResponseEntity.created(new URI("https://github.com/AntonioPGR")).build();
	}

	@PostMapping("/active")
	@Transactional
	public ResponseEntity activeUser(@RequestBody @Valid UserIdDTO user_info) throws URISyntaxException {
		Optional<UserEntity> user_search = user_repository.findById(user_info.id());
		if(user_search.isEmpty()){
			return ResponseEntity.notFound().build();
		}
		UserEntity user_entity = user_search.get();
		user_entity.active();
		return ResponseEntity.created(new URI("https://github.com/AntonioPGR")).build();
	}

	@PostMapping("/search")
	public List<UserReturnInfoDTO> searchInUsers(@RequestBody @Valid UserSearchDTO search_info){
		Collection<UserEntity> user_list = user_repository.searchUsers(search_info.name(), search_info.birth_date(), search_info.email(), search_info.cellphone());
		return user_list.stream().map(UserReturnInfoDTO::new).toList();
	}

	// PUT
	@PutMapping
	@Transactional
	public ResponseEntity editUser(@RequestBody @Valid UserUpdateDTO user_info){
		Optional<UserEntity> user_search = user_repository.findById(user_info.id());
		if(user_search.isEmpty()){
			return ResponseEntity.notFound().build();
		}
		UserEntity user_entity = user_search.get();
		user_entity.update(user_info);
		return ResponseEntity.noContent().build();
	}

	// DELETE
	@DeleteMapping
	@Transactional
	public ResponseEntity desactiveUser(@RequestBody @Valid UserIdDTO user_info){
		Optional<UserEntity> user_search = user_repository.findById(user_info.id());
		if(user_search.isEmpty()){
			return ResponseEntity.notFound().build();
		}
		UserEntity user_entity = user_search.get();
		user_entity.desactive();
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/delete")
	@Transactional
	public ResponseEntity deleteUser(@RequestBody @Valid UserIdDTO user_info){
		Optional<UserEntity> user_search = user_repository.findById(user_info.id());
		if(user_search.isEmpty()){
			return ResponseEntity.notFound().build();
		}
		UserEntity user_entity = user_search.get();
		user_repository.delete(user_entity);
		return ResponseEntity.noContent().build();
	}

}

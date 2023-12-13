package school.pachecos.api.users;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import school.pachecos.api.users.dtos.*;

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
		UserEntity user_entity = user_repository.getReferenceById(user_id);
		return new UserReturnInfoDTO(user_entity);
	}

	// POST
	@PostMapping
	@Transactional
	public ResponseEntity createUser(@RequestBody @Valid UserCreateDTO user_info) throws URISyntaxException {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		UserCreateDTO user_dto = new UserCreateDTO(user_info.name(), user_info.birth_date(), user_info.email(), user_info.cellphone(), user_info.gender(), user_info.photo_path(), encoder.encode(user_info.password()));
		UserEntity user_entity = new UserEntity(user_dto);
		user_repository.save(user_entity);
		return ResponseEntity.created(new URI("https://github.com/AntonioPGR")).build();
	}

	@PostMapping("/active")
	@Transactional
	public ResponseEntity activeUser(@RequestBody @Valid UserIdDTO user_info) throws URISyntaxException {
		UserEntity user_entity = user_repository.getReferenceById(user_info.id());
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
		UserEntity user_entity = user_repository.getReferenceById(user_info.id());
		user_entity.update(user_info);
		return ResponseEntity.noContent().build();
	}

	// DELETE
	@DeleteMapping
	@Transactional
	public ResponseEntity desactiveUser(@RequestBody @Valid UserIdDTO user_info){
		UserEntity user_entity = user_repository.getReferenceById(user_info.id());
		user_entity.desactive();
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/delete")
	@Transactional
	public ResponseEntity deleteUser(@RequestBody @Valid UserIdDTO user_info){
		UserEntity user_entity = user_repository.getReferenceById(user_info.id());
		user_repository.delete(user_entity);
		return ResponseEntity.noContent().build();
	}

}

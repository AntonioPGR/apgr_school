package apgr_school.api.users;

import apgr_school.api.users.DTOs.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Objects;

@RestController
@RequestMapping("users")
public class UserController {

	@Autowired
	private UserRepository user_repository;

	@PostMapping
	@Transactional
	public ResponseEntity<UserInformationDTO> POST(@RequestBody @Valid UserRegisterDTO user_data, UriComponentsBuilder uri_builder){
		User new_user = new User(user_data);
		user_repository.save(new_user);
		URI uri = uri_builder.path("users/{id}/").buildAndExpand(new_user.getId()).toUri();
		UserInformationDTO user_dto = new UserInformationDTO(new_user);
		return ResponseEntity.created(uri).body(user_dto);
	}

	@GetMapping
	public ResponseEntity<Page<UserListDTO>> GETUsersList(@PageableDefault(size=15, sort={"name"}) Pageable pagination_creator){
		Page<User> db_user_list = user_repository.findAllByActiveTrue(pagination_creator);
		Page<UserListDTO> user_list_dto = db_user_list.map(UserListDTO::new);
		return ResponseEntity.ok(user_list_dto);
	}

	@GetMapping("/{id}/")
	@Transactional
	public ResponseEntity<UserInformationDTO> GETUser(@PathVariable Long id){
		User user = user_repository.getReferenceById(id);
		UserInformationDTO user_dto = new UserInformationDTO(user);
		return ResponseEntity.ok(user_dto);
	}

	@PutMapping
	@Transactional
	public ResponseEntity<UserInformationDTO> PUT(@RequestBody @Valid UserUpdateDTO user_data){
		User user = user_repository.getReferenceById(user_data.id());
		user.updateInformation(user_data);
		UserInformationDTO user_dto = new UserInformationDTO(user);
		return ResponseEntity.ok(user_dto);
	}

	@DeleteMapping
	@Transactional
	public ResponseEntity DELETE(@RequestBody @Valid UserDeleteDTO user_data){
		User user = user_repository.getReferenceById(user_data.id());
		if(!Objects.equals(user.getPassword(), user_data.password())){
			return ResponseEntity.status(401).build();
		}
		user.exclude();
		return ResponseEntity.noContent().build();
	}

}

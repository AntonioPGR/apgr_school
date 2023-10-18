package apgr_school.api.users;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("users")
public class UserController {

	@Autowired
	private UserRepository user_repository;

	@PostMapping
	@Transactional
	public void POST(@RequestBody @Valid UserRegisterDTO user_data){
		User new_user = new User(user_data);
		user_repository.save(new_user);
	}

	@GetMapping
	public Page<UserListDTO> GETUsersList(@PageableDefault(size=10, sort={"name"}) Pageable pagination_creator){
		Page<User> db_user_list = user_repository.findAllByActiveTrue(pagination_creator);
		return db_user_list.map(UserListDTO::new);
	}

	@GetMapping("/{id}/")
	@Transactional
	public ResponseEntity<UserListDTO> GETUser(@PathVariable Long id){
		User user = user_repository.getReferenceById(id);
		UserListDTO user_dto = new UserListDTO(user);
		return ResponseEntity.ok(user_dto);
	}

	@PutMapping
	@Transactional
	public void PUT(@RequestBody @Valid UserUpdateDTO user_data){
		User user = user_repository.getReferenceById(user_data.id());
		user.updateInformation(user_data);
	}

	@DeleteMapping
	@Transactional
	public void DELETE(@RequestBody @Valid UserDeleteDTO user_data){
		User user = user_repository.getReferenceById(user_data.id());
		if(!Objects.equals(user.getPassword(), user_data.password())){
			return;
		}
		user.exclude();
	}

}

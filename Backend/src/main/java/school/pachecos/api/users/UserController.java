package school.pachecos.api.users;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.pachecos.api.users.dtos.*;
import school.pachecos.commons.dtos.IdDTO;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;


@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	UserService user_service;

	// GET
	@GetMapping
	public ResponseEntity<Page<UserReturnInfoDTO>> listAllUsers(@PageableDefault(size=30, sort="name") Pageable pageable){
		Page<UserReturnInfoDTO> users_page = user_service.listUsersPerPage(pageable);
		return ResponseEntity.ok().body(users_page);
	}

	@GetMapping("/{id}")
	public ResponseEntity<UserReturnInfoDTO> getUser(@PathVariable("id") long user_id){
		UserReturnInfoDTO user_dto = user_service.getUserReferenceById(user_id);
		return ResponseEntity.ok().body(user_dto);
	}

	// POST
	@PostMapping
	@Transactional
	public ResponseEntity createUser(@RequestBody @Valid UserCreateDTO user_info) throws URISyntaxException {
		Long user_id = user_service.createUser(user_info).id();
		URI user_uri = new URI("localhost:8000/users/"+user_id);
		return ResponseEntity.created(user_uri).build();
	}

	@PostMapping("/active")
	@Transactional
	public ResponseEntity activeUser(@RequestBody @Valid IdDTO user_info) throws URISyntaxException {
		user_service.activeUser(user_info.id());
		URI user_uri = new URI("localhost:8000/users/"+user_info.id());
		return ResponseEntity.created(user_uri).build();
	}

	@PostMapping("/search")
	public ResponseEntity<List<UserReturnInfoDTO>> searchInUsers(@RequestBody @Valid UserSearchDTO search_info){
		List<UserReturnInfoDTO> search_result = user_service.searchInUsers(search_info);
		return ResponseEntity.ok().body(search_result);
	}

	// PUT
	@PutMapping
	@Transactional
	public ResponseEntity editUser(@RequestBody @Valid UserUpdateDTO user_info){
		user_service.updateUser(user_info);
		return ResponseEntity.noContent().build();
	}

	// DELETE
	@DeleteMapping
	@Transactional
	public ResponseEntity desactiveUser(@RequestBody @Valid IdDTO user_info){
		user_service.desactiveUser(user_info.id());
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/delete")
	@Transactional
	public ResponseEntity deleteUser(@RequestBody @Valid IdDTO user_info){
		user_service.deleteUser(user_info.id());
		return ResponseEntity.noContent().build();
	}

}

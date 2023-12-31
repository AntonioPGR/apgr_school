package school.pachecos.api.users;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.pachecos.api.users.dtos.*;
import school.pachecos.infra.commons.classes.StaticApiController;
import school.pachecos.infra.uri.URIService;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController
		extends StaticApiController<UserEntity, UserCreateDTO, UserUpdateDTO, UserReturnDTO, UserService> {

	@Autowired
	UserService user_service;
	@Autowired
	URIService uri_service;

	@PostMapping("/search")
	public ResponseEntity<List<UserReturnDTO>> searchInUsers(@RequestBody @Valid UserSearchDTO search_info) {
		List<UserReturnDTO> search_result = user_service.searchInUsers(search_info);
		return ResponseEntity.ok().body(search_result);
	}

	@PostMapping("/activate/{id}")
	@Transactional
	public ResponseEntity<URI> activateUser(@PathVariable("id") Long id) {
		user_service.activate(id);
		URI user_uri = uri_service.createReturnURI("/users/" + id);
		return ResponseEntity.created(user_uri).build();
	}

	@DeleteMapping("/deactivate/{id}")
	@Transactional
	public ResponseEntity deactivateUser(@PathVariable("id") Long id) {
		user_service.deactive(id);
		return ResponseEntity.noContent().build();
	}

	@Override
	protected String getBaseUriPath() {
		return "/users";
	}
}

package school.pachecos.api.users;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.pachecos.api.users.dtos.*;
import school.pachecos.infra.commons.classes.BaseApiController;
import school.pachecos.infra.commons.dtos.IdDTO;
import school.pachecos.infra.uri.URIService;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;


@RestController
@RequestMapping("/users")
public class UserController extends BaseApiController<UserEntity, UserCreateDTO, UserUpdateDTO, UserReturnDTO, UserService> {

	@Autowired
	UserService user_service;
	@Autowired
	URIService uri_service;

	@PostMapping("/active")
	@Transactional
	public ResponseEntity<URI> activeUser(@RequestBody @Valid IdDTO user_info) throws URISyntaxException {
		user_service.activeUser(user_info.id());
		URI user_uri =  uri_service.createReturnURI("/users/"+user_info.id());
		return ResponseEntity.created(user_uri).build();
	}

	@PostMapping("/search")
	public ResponseEntity<List<UserReturnDTO>> searchInUsers(@RequestBody @Valid UserSearchDTO search_info){
		List<UserReturnDTO> search_result = user_service.searchInUsers(search_info);
		return ResponseEntity.ok().body(search_result);
	}

	@DeleteMapping("/desative")
	@Transactional
	public ResponseEntity desactiveUser(@RequestBody @Valid IdDTO user_info){
		user_service.desactiveUser(user_info.id());
		return ResponseEntity.noContent().build();
	}

	@Override
	protected String getBaseUriPath() {
		return "/users";
	}
}

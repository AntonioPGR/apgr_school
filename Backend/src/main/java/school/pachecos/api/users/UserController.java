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
import school.pachecos.commons.classes.BaseApiController;
import school.pachecos.commons.dtos.IdDTO;

import javax.swing.text.html.parser.Entity;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;


@RestController
@RequestMapping("/users")
public class UserController extends BaseApiController<UserEntity, UserCreateDTO, UserUpdateDTO, UserReturnDTO, UserService> {

	@Autowired
	UserService user_service;

	@PostMapping("/active")
	@Transactional
	public ResponseEntity activeUser(@RequestBody @Valid IdDTO user_info) throws URISyntaxException {
		user_service.activeUser(user_info.id());
		URI user_uri = new URI("localhost:8000/users/"+user_info.id());
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

}

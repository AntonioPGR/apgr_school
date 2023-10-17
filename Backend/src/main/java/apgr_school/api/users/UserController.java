package apgr_school.api.users;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
	public List<UserListDTO> GET(){
		return user_repository.findAll().stream().map(UserListDTO::new).toList();
	}

}

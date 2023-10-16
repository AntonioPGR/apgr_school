package apgr_school.api.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("users")
public class UserController {

	@Autowired
	private UserRepository user_repository;

	@PostMapping
	public String POST(@RequestBody UserRegisterData user_data){
		User new_user = new User(user_data);
		user_repository.save(new_user);
		return "opa";
	}

}

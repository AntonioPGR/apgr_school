package code.planner.api.routes;

import code.planner.api.models.Category;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categories")
public class Categories {

	@GetMapping
	public String GET(){
		return "categories";
	}

	@PostMapping
	public String POST(@RequestBody Category category_info){
		return category_info.toString();
	}

}

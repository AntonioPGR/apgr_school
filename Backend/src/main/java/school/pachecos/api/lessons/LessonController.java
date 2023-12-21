package school.pachecos.api.lessons;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.pachecos.api.lessons.dto.*;
import school.pachecos.commons.classes.BaseApiController;
import java.util.List;


@RestController
@RequestMapping("/lessons")
public class LessonController extends BaseApiController<LessonEntity, LessonCreateEntityDTO, LessonUpdateEntityDTO, LessonReturnDTO, LessonService> {

	@Autowired
	LessonService lesson_service;

	@PostMapping("/search")
	public ResponseEntity<List<LessonReturnDTO>> searchLessons(@RequestBody @Valid LessonSearchDTO search_info){
		List<LessonReturnDTO> search_result = lesson_service.searchLessons(search_info);
		return ResponseEntity.ok().body(search_result);
	}

}

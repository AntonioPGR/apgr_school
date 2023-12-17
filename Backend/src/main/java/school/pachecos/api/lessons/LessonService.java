package school.pachecos.api.lessons;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import school.pachecos.api.lessons.dto.*;
import school.pachecos.api.users.UserEntity;
import school.pachecos.api.users.UserRepository;

import java.util.List;

@Service
public class LessonService {

	@Autowired
	LessonRepository lesson_repository;
	@Autowired
	UserRepository user_repository;

	public Page<LessonReturnInfoDTO> listLessonsPerPage(Pageable pageable){
		Page<LessonEntity> lessons_page = lesson_repository.findAll(pageable);
		return lessons_page.map(LessonReturnInfoDTO::new);
	}

	public LessonReturnInfoDTO getLessonReferenceById(Long id) {
		LessonEntity lesson_entity = lesson_repository.getReferenceById(id);
		return new LessonReturnInfoDTO(lesson_entity);
	}

	public LessonReturnInfoDTO createLesson(LessonCreateIdDTO lesson_id_info) {
		UserEntity professor = getProfessorFromId(lesson_id_info.professor_id());
		LessonCreateProfessorDTO lesson_create_dto = new LessonCreateProfessorDTO(lesson_id_info.name(), lesson_id_info.datetime(), professor, lesson_id_info.duration_in_minutes());
		LessonEntity lesson_entity = new LessonEntity(lesson_create_dto);
		lesson_repository.save(lesson_entity);
		return new LessonReturnInfoDTO(lesson_entity);
	}

	public List<LessonReturnInfoDTO> searchLessons(LessonSearchDTO search){
		List<LessonEntity> search_results = lesson_repository.searchLessons(search.name(), search.datetime());
		return search_results.stream().map(LessonReturnInfoDTO::new).toList();
	}

	public void updateLesson(LessonUpdateIdDTO lesson_info) {
		UserEntity professor = null;
		if(lesson_info.professor_id() != null){
			professor = getProfessorFromId(lesson_info.professor_id());
		}
		LessonUpdateProfessorDTO lesson_update_dto  = new LessonUpdateProfessorDTO(lesson_info.id(), lesson_info.name(), lesson_info.datetime(), professor, lesson_info.duration_in_minutes());
		LessonEntity lesson = lesson_repository.getReferenceById(lesson_info.id());
		lesson.update(lesson_update_dto);
	}

	public void deleteLesson(long id) {
		LessonEntity lesson = lesson_repository.getReferenceById(id);
		lesson_repository.delete(lesson);
	}

	private UserEntity getProfessorFromId(long id){
		return user_repository.getReferenceById(id);
	}

}

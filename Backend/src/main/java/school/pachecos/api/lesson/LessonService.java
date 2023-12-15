package school.pachecos.api.lesson;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import school.pachecos.api.lesson.dto.*;
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
		Page<LessonEntity> users_page = lesson_repository.findAll(pageable);
		return users_page.map(LessonReturnInfoDTO::new);
	}

	public LessonReturnInfoDTO getLessonReferenceById(Long user_id) {
		LessonEntity user_entity = lesson_repository.getReferenceById(user_id);
		return new LessonReturnInfoDTO(user_entity);
	}

	public LessonReturnInfoDTO createLesson(LessonCreateIdDTO lesson_id_info) {
		UserEntity professor = getProfessorFromId(lesson_id_info.professor_id());
		LessonCreateProfessorDTO lesson_create_dto = new LessonCreateProfessorDTO(lesson_id_info.name(), lesson_id_info.datetime(), professor);
		LessonEntity user_entity = new LessonEntity(lesson_create_dto);
		lesson_repository.save(user_entity);
		return new LessonReturnInfoDTO(user_entity);
	}

	public List<LessonReturnInfoDTO> searchLessons(LessonSearchDTO search){
		List<LessonEntity> search_results = lesson_repository.searchLessons(search.name(), search.datetime());
		return search_results.stream().map(LessonReturnInfoDTO::new).toList();
	}

	public void updateLesson(LessonUpdateIdDTO lesson_info) {
		UserEntity professor = getProfessorFromId(lesson_info.professor_id());
		LessonUpdateProfessorDTO lesson_update_dto  = new LessonUpdateProfessorDTO(lesson_info.id(), lesson_info.name(), lesson_info.datetime(), professor);
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

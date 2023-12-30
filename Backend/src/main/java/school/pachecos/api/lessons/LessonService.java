package school.pachecos.api.lessons;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import school.pachecos.api.lessons.dto.*;
import school.pachecos.api.users.UserEntity;
import school.pachecos.api.users.UserRepository;
import school.pachecos.infra.commons.classes.BaseApiService;

import java.util.List;
import java.util.UUID;

@Service
public class LessonService
		extends BaseApiService<LessonEntity, LessonCreateEntityDTO, LessonUpdateEntityDTO, LessonReturnDTO> {

	@Autowired
	LessonRepository lesson_repository;
	@Autowired
	UserRepository user_repository;

	public List<LessonReturnDTO> searchLessons(LessonSearchDTO search) {
		List<LessonEntity> search_results = lesson_repository.searchLessons(search.name(), search.datetime());
		return search_results.stream().map(LessonReturnDTO::new).toList();
	}

	public LessonReturnDTO create(LessonCreateIdDTO lesson_dto) {
		UserEntity professor = getProfessor(lesson_dto.professor_id());
		return this.create(new LessonCreateEntityDTO(lesson_dto, professor));
	}

	public void update(LessonUpdateIdDTO lesson_info) {
		UserEntity professor = lesson_info.professor_id() != null ? getProfessor(lesson_info.professor_id()) : null;
		this.update(new LessonUpdateEntityDTO(lesson_info, professor));
	}

	private UserEntity getProfessor(UUID id) {
		return user_repository.getReferenceById(id);
	}

	@Override
	protected LessonReturnDTO convertToReturnDTO(LessonEntity lessonEntity) {
		return new LessonReturnDTO(lessonEntity);
	}

	@Override
	protected LessonEntity convertToEntity(LessonCreateEntityDTO create_dto) {
		return new LessonEntity(create_dto);
	}

}

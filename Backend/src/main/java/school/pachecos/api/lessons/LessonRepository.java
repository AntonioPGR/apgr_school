package school.pachecos.api.lessons;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface LessonRepository extends JpaRepository<LessonEntity, UUID> {

	@Query("SELECT l FROM LessonEntity l WHERE " +
			"l.name LIKE CONCAT('%', :name, '%') " +
			"OR l.datetime = :datetime  ")
	List<LessonEntity> searchLessons(String name, LocalDateTime datetime);
}

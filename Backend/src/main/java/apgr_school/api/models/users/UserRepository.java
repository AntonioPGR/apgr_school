package apgr_school.api.models.users;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
	Page<User> findAllByActiveTrue(Pageable paginationCreator);
	Optional<User> findByEmail(String email);
}

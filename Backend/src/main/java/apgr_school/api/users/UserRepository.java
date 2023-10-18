package apgr_school.api.users;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
	Page<User> findAllByActiveTrue(Pageable paginationCreator);
}

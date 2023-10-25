package apgr_school.api.models.users;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	Page<User> findAllByActiveTrue(Pageable paginationCreator);
	User findByEmail(String email);
}

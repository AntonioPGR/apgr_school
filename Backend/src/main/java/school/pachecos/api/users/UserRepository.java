package school.pachecos.api.users;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

public interface UserRepository extends JpaRepository<UserEntity, UUID> {

	@Query(value = "SELECT u FROM UserEntity u WHERE " +
			"u.name LIKE CONCAT('%', :name, '%') " +
			"OR u.birth_date = :date " +
			"OR u.email LIKE CONCAT('%', :email, '%') " +
			"OR cellphone LIKE CONCAT('%', :cellphone, '%') ")
	Collection<UserEntity> searchUsers(String name, LocalDate date, String email, String cellphone);

	UserDetails findByEmail(String email);

	@Override
	default Page<UserEntity> findAll(Pageable pageable) {
		return findAllByActiveTrue(pageable);
	}

	Page<UserEntity> findAllByActiveTrue(Pageable pageable);
}

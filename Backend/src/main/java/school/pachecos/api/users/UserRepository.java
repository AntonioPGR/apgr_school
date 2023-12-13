package school.pachecos.api.users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

	@Query(value = "SELECT u FROM UserEntity u WHERE " +
		"u.name LIKE CONCAT('%', :name, '%') " +
		"OR u.birth_date = :date " +
		"OR u.email LIKE CONCAT('%', :email, '%') " +
		"OR cellphone LIKE CONCAT('%', :cellphone, '%') "
	)
	Collection<UserEntity> searchUsers(String name, LocalDate date, String email, String cellphone);

	UserDetails findByEmail(String email);
}

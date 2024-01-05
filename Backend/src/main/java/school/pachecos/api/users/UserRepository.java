package school.pachecos.api.users;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Collection;

@Repository
public interface UserRepository extends org.springframework.data.jpa.repository.JpaRepository<UserEntity, Long> {

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

package school.pachecos.api.users;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import school.pachecos.api.users.dtos.UserCreateDTO;
import school.pachecos.api.users.dtos.UserUpdateDTO;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Table(name = "Users")
@Entity(name = "UserEntity")
@Getter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class UserEntity implements UserDetails {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@NotBlank
	private String name;
	@Past
	@NotNull
	private LocalDate birth_date;
	@Email
	@NotBlank
	private String email;
	@NotBlank
	@Pattern(regexp = "[+][\\d]{2,3}[(][\\d]{2,3}[)][\\d]{9}")
	private String cellphone;
	@NotBlank
	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*\\W).{8,}$")
	private String password;
	@NotBlank
	private String gender;
	@Nullable
	private String photo_path;
	@NotNull
	private boolean active;
	private String permissions;

	public UserEntity(
			String name,
			LocalDate birth_date,
			String email,
			String cellphone,
			String password,
			String gender,
			@Nullable String photo_path
	) {
		this.name = name;
		this.birth_date = birth_date;
		this.email = email;
		this.cellphone = cellphone;
		this.password = password;
		this.gender = gender;
		this.photo_path = photo_path;
		this.active = true;
	}

	public UserEntity(UserCreateDTO user_info) {
		name = user_info.name();
		birth_date = user_info.birth_date();
		email = user_info.email();
		cellphone = user_info.cellphone();
		password = user_info.password();
		gender = user_info.gender();
		photo_path = user_info.photo_path();
		active = true;
	}

	public void update(UserUpdateDTO user_info) {
		name = user_info.name() != null ? user_info.name() : name;
		birth_date = user_info.birth_date() != null ? user_info.birth_date() : birth_date;
		email = user_info.email() != null ? user_info.email() : email;
		cellphone = user_info.cellphone() != null ? user_info.cellphone() : cellphone;
		password = user_info.password() != null ? user_info.password() : password;
		gender = user_info.gender() != null ? user_info.gender() : gender;
		photo_path = user_info.photo_path() != null ? user_info.photo_path() : photo_path;
	}

	public void desactive(){
		active = true;
	}

	public void active(){
		active = true;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(new SimpleGrantedAuthority("ROLE_USER"));
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}

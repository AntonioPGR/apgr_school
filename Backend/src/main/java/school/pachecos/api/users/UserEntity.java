package school.pachecos.api.users;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import school.pachecos.api.lessons.LessonEntity;
import school.pachecos.api.tasks.TaskEntity;
import school.pachecos.api.users.dtos.UserCreateDTO;
import school.pachecos.api.users.dtos.UserUpdateDTO;
import school.pachecos.infra.commons.classes.BaseApiEntity;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Table(name = "Users")
@Entity(name = "UserEntity")
@Getter
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity extends BaseApiEntity<UserUpdateDTO> implements UserDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank
	@Length(max = 100)
	private String name;
	@Past
	@Column(nullable = false)
	private LocalDate birth_date;
	@Email
	@NotBlank
	@Length(max = 70)
	private String email;
	@NotBlank
	@Pattern(regexp = "[+]\\d{2,3}[(]\\d{2,3}[)]\\d{8,9}")
	private String cellphone;
	@NotBlank
	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*\\W).{8,}$")
	private String password;
	@NotBlank
	@Length(max = 1, min = 1)
	private String gender;
	@Nullable
	private String photo_path;
	@NotNull
	private boolean active;
	@NotNull
	private String permissions;

	@OneToMany(mappedBy = "professor", cascade = CascadeType.ALL)
	private List<LessonEntity> lessons;

	@ManyToMany(mappedBy = "students", cascade = CascadeType.ALL)
	private List<TaskEntity> tasks;

	// CONSTRUCTORS
	public UserEntity(String name, LocalDate birth_date, String email, String cellphone, String password, String gender,
			@Nullable String photo_path) {
		setName(name);
		setBirth_date(birth_date);
		setEmail(email);
		setCellphone(cellphone);
		setPassword(password);
		setGender(gender);
		setPhoto_path(photo_path);
		activete();
		setPermissions("ROLE_USER");
	}

	public UserEntity(UserCreateDTO user_info) {
		setName(user_info.name());
		setBirth_date(user_info.birth_date());
		setEmail(user_info.email());
		setCellphone(user_info.cellphone());
		setPassword(user_info.password());
		setGender(user_info.gender());
		setPhoto_path(user_info.photo_path());
		activete();
		setPermissions("ROLE_USER");
	}

	// METHODS
	public void update(UserUpdateDTO user_info) {
		name = user_info.name() != null ? user_info.name() : name;
		birth_date = user_info.birth_date() != null ? user_info.birth_date() : birth_date;
		email = user_info.email() != null ? user_info.email() : email;
		cellphone = user_info.cellphone() != null ? user_info.cellphone() : cellphone;
		password = user_info.password() != null ? user_info.password() : password;
		gender = user_info.gender() != null ? user_info.gender() : gender;
		photo_path = user_info.photo_path() != null ? user_info.photo_path() : photo_path;
	}

	public void desactivete() {
		active = false;
	}

	public void activete() {
		active = true;
	}

	// USER DETAIL IMPLEMENTS
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(new SimpleGrantedAuthority("ROLE_USER"));
	}

	@Override
	public String getPassword() {
		return this.password;
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

	private void setPassword(String password) {
		BCryptPasswordEncoder password_encoder = new BCryptPasswordEncoder();
		this.password = password_encoder.encode(password);
	}
}

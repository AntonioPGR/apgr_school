package apgr_school.api.models.users;

import apgr_school.api.models.users.DTOs.UserRegisterDTO;
import apgr_school.api.models.users.DTOs.UserUpdateDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "Users")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class User implements UserDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private Date date_of_birth;
	private String email;
	private String cellphone;
	private String password;
	@Enumerated(EnumType.STRING)
	private EnumGender gender;
	private String photo_path;
	private Boolean active;

	public User(UserRegisterDTO user_data){
		id = user_data.id();
		name = user_data.name();
		date_of_birth = user_data.date_of_birth();
		email = user_data.email();
		cellphone = user_data.cellphone();
		password = user_data.password();
		gender = user_data.gender();
		photo_path = user_data.photo_path();
		active = true;
	}

	public User(UserRegisterDTO user_data, String encoded_password){
		id = user_data.id();
		name = user_data.name();
		date_of_birth = user_data.date_of_birth();
		email = user_data.email();
		cellphone = user_data.cellphone();
		password = encoded_password;
		gender = user_data.gender();
		photo_path = user_data.photo_path();
		active = true;
	}

	public void updateInformation(UserUpdateDTO user_data){
		name = user_data.name() != null? user_data.name() : name;
		email = user_data.email() != null? user_data.email() : email;
		cellphone = user_data.cellphone() != null? user_data.cellphone() : cellphone;
		password = user_data.password() != null? user_data.password() : password;
		photo_path = user_data.photo_path() != null? user_data.photo_path() : photo_path;
	}

	public void exclude() {
		active = false;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(new SimpleGrantedAuthority("ROLE_USER"));
	}

	@Override
	public String getPassword(){
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

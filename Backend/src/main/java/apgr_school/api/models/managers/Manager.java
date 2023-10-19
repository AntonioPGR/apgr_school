package apgr_school.api.models.managers;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "managers")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Manager {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id")
	private Long id;
	@OneToOne
	@JoinColumn(name = "UserID", referencedColumnName = "Id", nullable = false)
	private apgr_school.api.models.users.User User;
}

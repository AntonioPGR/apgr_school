package apgr_school.api.models.professors;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "professors")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Professor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id")
	private Long id;
	@OneToOne
	@JoinColumn(name = "UserID", referencedColumnName = "Id", nullable = false)
	private apgr_school.api.models.users.User User;
}

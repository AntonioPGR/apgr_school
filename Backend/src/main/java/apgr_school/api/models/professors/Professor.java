package apgr_school.api.models.professors;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Professors")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Professor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@OneToOne
	@JoinColumn(name = "userId", referencedColumnName = "Id", nullable = false)
	private apgr_school.api.models.users.User User;
}

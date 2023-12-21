package school.pachecos.commons.classes;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import school.pachecos.api.users.dtos.UserUpdateDTO;


@EqualsAndHashCode(of = "id")
public abstract class BaseApiEntity<UpdateDTO>{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	abstract public void update(UpdateDTO update_dto);

}
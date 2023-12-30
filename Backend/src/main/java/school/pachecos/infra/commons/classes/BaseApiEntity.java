package school.pachecos.infra.commons.classes;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import school.pachecos.api.users.dtos.UserUpdateDTO;

import java.util.UUID;

@EqualsAndHashCode(of = "id")
public abstract class BaseApiEntity<UpdateDTO> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private UUID id;

	abstract public void update(UpdateDTO update_dto);

}

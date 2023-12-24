package school.pachecos.infra.commons.dtos;

import jakarta.validation.constraints.NotNull;

public record IdDTO (
		@NotNull Long id
){
}

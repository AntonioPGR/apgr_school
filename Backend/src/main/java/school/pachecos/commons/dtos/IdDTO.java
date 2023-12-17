package school.pachecos.commons.dtos;

import jakarta.validation.constraints.NotNull;

public record IdDTO (
		@NotNull Long id
){
}

package school.pachecos.infra.exception;

import org.springframework.validation.FieldError;

public record ExceptionFieldDTO(String field, String message) {

	public ExceptionFieldDTO(FieldError field_error){
		this(field_error.getField(), field_error.getDefaultMessage());
	}

}

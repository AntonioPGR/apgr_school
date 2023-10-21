package apgr_school.api.infra.exceptions;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class HttpErrorsHandling {

	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity handle404(){
		return ResponseEntity.notFound().build();
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity handle400(MethodArgumentNotValidException exception){
		List<FieldError> field_errors_list = exception.getFieldErrors();
		List<ValidationsErrorsDTO> errors_dto_list = field_errors_list.stream().map(ValidationsErrorsDTO::new).toList();
		return ResponseEntity.badRequest().body(errors_dto_list);
	}

	private record ValidationsErrorsDTO(String default_message, String field, String rejected_value){
		public ValidationsErrorsDTO(FieldError error){
			this(error.getDefaultMessage(), error.getField(), (String) error.getRejectedValue());
		}
	}

}
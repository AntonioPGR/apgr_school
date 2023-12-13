package school.pachecos.infra.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.List;


@RestControllerAdvice
public class ErrorsHandler {

	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity notFind404(){
		return ResponseEntity.notFound().build();
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity invalidPassedInformation400(MethodArgumentNotValidException exception){
		List<FieldError> errors_list = exception.getFieldErrors();
		List<ExceptionFieldDTO> errors_dto_list = errors_list.stream().map(ExceptionFieldDTO::new).toList();
		return ResponseEntity.badRequest().body(errors_dto_list);
	}

}

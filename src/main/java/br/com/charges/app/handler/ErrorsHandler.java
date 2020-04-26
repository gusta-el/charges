package br.com.charges.app.handler;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.charges.app.dto.FieldsValidationErrorsDTO;
import br.com.charges.app.dto.FieldsValidationErrorsDTO.Errors;
import br.com.charges.app.dto.GenericErrorDTO;

@RestControllerAdvice
public class ErrorsHandler {

	@ExceptionHandler({MethodArgumentNotValidException.class })
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public FieldsValidationErrorsDTO handle(MethodArgumentNotValidException exception) {

		List<Errors> errors = exception.getBindingResult()
		        .getFieldErrors()
		        .stream()
		        .map(error -> Errors.builder()
		        		.field(error.getField())
		        		.message(error.getDefaultMessage())
		        		.build())
		        .collect(Collectors.toList());
				
        return FieldsValidationErrorsDTO.builder()
        		.message("Campos obrigatórios")
        		.errors(errors)
        		.build();
        		
    }  
	
	@ExceptionHandler({Exception.class })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public GenericErrorDTO handle(Exception exception) {

        return GenericErrorDTO.builder()
        		.message(exception.getMessage())
        		.build();
        		
    } 

    
}

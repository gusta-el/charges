package br.com.charges.app.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Builder
@Getter
@AllArgsConstructor
@JsonInclude(value = Include.NON_NULL)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = false)
public class FieldsValidationErrorsDTO {
	
	String message;
	List<Errors> errors;
	
	@Builder
	@Getter
	@AllArgsConstructor
	@JsonInclude(value = Include.NON_NULL)
	@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = false)
	public static class Errors {
		
		String field;
		String message;
	}

}

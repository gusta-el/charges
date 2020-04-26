package br.com.charges.app.dto;

import javax.validation.constraints.NotNull;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DebtorRequestDTO {

	@NotNull(message = "Campo obrigatório.")
	String debtorName;
	
	@NotNull(message = "Campo obrigatório.")
	String debtorNick;
	
	@NotNull(message = "Campo obrigatório.")
	String debtorEmail;
	
}

package br.com.charges.app.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Builder
@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = false)
public class DebtorDTO {

	Long debtorId;
	String debtorName;
	String debtorNick;
	String debtorEmail;
	
    //Set<DebtorDTO> debtsDto;
	
}

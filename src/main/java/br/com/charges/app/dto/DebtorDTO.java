package br.com.charges.app.dto;

import java.util.Set;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Builder
@Getter
@Setter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = false)
public class DebtorDTO {

	Long debtorId;
	String debtorName;
	String debtorNick;
	String debtorEmail;
	
    Set<DebtDTO> debtsDto;
	
}

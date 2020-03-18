package br.com.charges.app.dto;

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

	String debtorName;
	String debtorNick;
	String debtorEmail;

}

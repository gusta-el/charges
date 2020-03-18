package br.com.charges.app.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DebtorValue {

	Long debtorId;
	String debtorName;
	String debtorNick;
	String debtorEmail;

}

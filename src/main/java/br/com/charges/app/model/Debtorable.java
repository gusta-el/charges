package br.com.charges.app.model;

import java.math.BigDecimal;
import java.sql.Date;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@Builder
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class Debtorable {

	String debtorName;
	String email;
	BigDecimal moneyValue;
	Date dateDebt;
	
}

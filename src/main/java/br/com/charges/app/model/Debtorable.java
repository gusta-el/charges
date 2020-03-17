package br.com.charges.app.model;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Set;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@Builder
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = false)
public class Debtorable {

	String debtorName;
	String debtorNick;
	String email;
	Set<Debtable> debts;
	
	@Getter
	@Builder
	@FieldDefaults(level = AccessLevel.PRIVATE)
	public static class Debtable {
		
		String description;
		BigDecimal moneyValue;
		Date dateDebt;
		
	}
	
}

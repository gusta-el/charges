package br.com.charges.app.dto;

import java.math.BigDecimal;
import java.sql.Date;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Builder
@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = false)
public class DebtDTO {

	Long debtId;
	String description;
	BigDecimal moneyValue;
	Date dateDebt;
	Boolean paid;
	Boolean sendable;

}

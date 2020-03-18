package br.com.charges.app.model;

import java.math.BigDecimal;
import java.sql.Date;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;


@Getter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DebtValue {

	Long debtId;
	String description;
	BigDecimal moneyValue;
	Date dateDebt;
	Boolean paid;
	Boolean sendable;
	
	
}

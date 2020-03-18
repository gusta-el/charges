package br.com.charges.app.dto;

import java.math.BigDecimal;
import java.sql.Date;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DebtRequestDTO {

	String description;
	BigDecimal moneyValue;
	Date dateDebt;
	Boolean paid;
	Boolean sendable;
	Long debtorId;
	
}

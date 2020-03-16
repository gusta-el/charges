package br.com.charges.app.entity;

import java.math.BigDecimal;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Getter
@Entity(name = "DEBTOR")
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = false)
public class Debtor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long debtorId;
	String debtorName;
	String email;
	BigDecimal moneyValue;
	Date dateDebt;
	Boolean paid;
	Boolean sendable;

	
	
    
}

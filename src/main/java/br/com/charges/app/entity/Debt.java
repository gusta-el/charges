package br.com.charges.app.entity;

import java.math.BigDecimal;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Getter
@Entity(name = "DEBT")
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = false)
public class Debt {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long debtId;
	String description;
	BigDecimal moneyValue;
	Date dateDebt;
	Boolean paid;
	Boolean sendable;
	
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "DEBTOR_ID")
    Debtor debtor;
 

    //@Min(message = "valor para realizar atencipação automática inválido", value = 30)
    //@Digits(integer = 10, fraction = 2)
    //@NotNull(message = "valor amount é obrigatório")

}

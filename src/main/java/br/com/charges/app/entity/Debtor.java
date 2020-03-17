package br.com.charges.app.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

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
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long debtorId;
	String debtorName;
	String debtorNick;
	String debtorEmail;
	
    @OneToMany(mappedBy = "debtor", fetch = FetchType.EAGER)
    private Set<Debt> debts;
	
}

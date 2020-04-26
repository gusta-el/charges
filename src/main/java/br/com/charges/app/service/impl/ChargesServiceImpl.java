package br.com.charges.app.service.impl;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.charges.app.entity.Debt;
import br.com.charges.app.entity.Debtor;
import br.com.charges.app.model.Debtorable;
import br.com.charges.app.model.Debtorable.Debtable;
import br.com.charges.app.repository.DebtRepository;
import br.com.charges.app.repository.DebtorRepository;
import br.com.charges.app.service.ChargesService;
import br.com.charges.app.utils.ChargesUtils;

@Service
@Transactional
public class ChargesServiceImpl implements ChargesService{

	@Autowired
	DebtorRepository debtorRepository;

	@Autowired
	DebtRepository debtRepository;
	
	@Override
	public Stream<Debtorable> execute() {
		
		Iterable<Debtor> debtors = debtorRepository.findAll();
		return this.execute(StreamSupport.stream(debtors.spliterator(), false));

	}
	
	private Stream<Debtorable> execute(Stream<Debtor> debtors) {
		
		return debtors.map(this::extractDebtors);
		
	}
	
	private Debtorable extractDebtors(Debtor debtor) {
		
		return Debtorable.builder()
				.debtorName(debtor.getDebtorName())
				.debtorNick(debtor.getDebtorNick())
				.email(debtor.getDebtorEmail())
				.debts(this.extractDebts(debtor.getDebts()))
				.build();
		
	}
	
	private Set<Debtable> extractDebts(Set<Debt> debts) {

		return debts.stream()
				.filter(debt -> !debt.getPaid() && debt.getSendable())
				.map(this::extractDebtable)
				.collect(Collectors.toSet());

	}

	private Debtable extractDebtable(Debt debt) {

		return Debtable.builder()
				.dateDebt(debt.getDateDebt())
				.description(debt.getDescription())
				.moneyValue(debt.getMoneyValue())
				.build();

	}

	@Override
	public void pay(Long id) {
		Optional<Debt> opDebt = debtRepository.findById(id);
		opDebt.ifPresent(this::setPay);
		
	}
	
	private void setPay(Debt debt) {
		debt.setPaid(ChargesUtils.PAID);
		debtRepository.save(debt);
	}
		
}

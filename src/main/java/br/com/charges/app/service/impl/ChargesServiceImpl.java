package br.com.charges.app.service.impl;

import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.charges.app.entity.Debtor;
import br.com.charges.app.model.Debtorable;
import br.com.charges.app.repository.DebtorRepository;
import br.com.charges.app.service.ChargesService;

@Service
public class ChargesServiceImpl implements ChargesService{

	@Autowired
	DebtorRepository debtorRepository;

	@Override
	public Stream<Debtorable> execute() {

		Iterable<Debtor> debtors = debtorRepository.findAll();
		return this.execute(StreamSupport.stream(debtors.spliterator(), true));

	}
	
	private Stream<Debtorable> execute(Stream<Debtor> debtors) {
				
		return debtors.filter(d -> d.getPaid() && d.getSendable())
				.map(this::transform);
		
	}
	
	private Debtorable transform(Debtor debtor) {
		
		return Debtorable.builder()
				.debtorName(debtor.getDebtorName())
				.email(debtor.getEmail())
				.dateDebt(debtor.getDateDebt())
				.moneyValue(debtor.getMoneyValue())
				.build();
		
	}
	
}

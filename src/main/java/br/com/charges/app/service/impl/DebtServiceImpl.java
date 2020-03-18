package br.com.charges.app.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.charges.app.dto.DebtDTO;
import br.com.charges.app.entity.Debt;
import br.com.charges.app.model.DebtValue;
import br.com.charges.app.repository.DebtRepository;
import br.com.charges.app.service.DebtService;

public class DebtServiceImpl implements DebtService{

	@Autowired
	DebtRepository debtRepository;
	
	@Override
	public List<DebtDTO> getAll() {
		Iterable<Debt> debts = debtRepository.findAll();
		return this.execute(StreamSupport.stream(debts.spliterator(), true));
	}

	@Override
	public List<DebtDTO> getPaidDebts(Boolean paid) {
		Iterable<Debt> debts = debtRepository.findByPaid(paid);
		return this.execute(StreamSupport.stream(debts.spliterator(), true));
	}

	@Override
	public DebtDTO addDebt(DebtValue debt) {

		Debt debtEntity = Debt.builder()
				.dateDebt(debt.getDateDebt())
				.description(debt.getDescription())
				.moneyValue(debt.getMoneyValue())
				.paid(debt.getPaid())
				.sendable(debt.getSendable())
				.build();
		
		debtEntity = debtRepository.save(debtEntity);
		
		return this.extractDebt(debtEntity);	
	}

	@Override
	public DebtDTO changeDebt(DebtValue debtValue) {

		Optional<Debt> opDebt = debtRepository.findById(debtValue.getDebtId());
		
		opDebt.ifPresent(debt -> {
			debt.setDateDebt(debtValue.getDateDebt());
			debt.setDebtId(debtValue.getDebtId());
			debt.setDescription(debtValue.getDescription());
			debt.setMoneyValue(debtValue.getMoneyValue());
			debt.setPaid(debtValue.getPaid());
			debt.setSendable(debtValue.getSendable());
			debt = debtRepository.save(debt);
		});
				
		if(opDebt.isPresent()) {
			return this.extractDebt(opDebt.get());
		}
		
		return null;
	}
	
	private List<DebtDTO> execute(Stream<Debt> debtors) {
		
		return debtors.map(this::extractDebt).collect(Collectors.toList());
		
	}
	
	private DebtDTO extractDebt(Debt debt) {
		
		return DebtDTO.builder()
				.description(debt.getDescription())
				.dateDebt(debt.getDateDebt())
				.sendable(debt.getSendable())
				.debtId(debt.getDebtId())
				.moneyValue(debt.getMoneyValue())
				.paid(debt.getPaid())
				.build();
	}

}

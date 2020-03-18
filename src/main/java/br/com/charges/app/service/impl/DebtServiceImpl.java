package br.com.charges.app.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.charges.app.dto.DebtDTO;
import br.com.charges.app.dto.DebtorDTO;
import br.com.charges.app.entity.Debt;
import br.com.charges.app.entity.Debtor;
import br.com.charges.app.model.DebtValue;
import br.com.charges.app.repository.DebtRepository;
import br.com.charges.app.repository.DebtorRepository;
import br.com.charges.app.service.DebtService;

@Service
public class DebtServiceImpl implements DebtService{

	@Autowired
	DebtRepository debtRepository;

	@Autowired
	DebtorRepository debtorRepository;
	
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
	public List<DebtorDTO> getDebtsByDebtorName(String name) {
		
		Iterable<Debtor> debtors = debtorRepository.findBydebtorName(name);
		Stream<Debtor> debtorsStream = StreamSupport.stream(debtors.spliterator(), true);
		
		return debtorsStream.map(this::extractDebtorWithDebts).collect(Collectors.toList());
	
	}
	

	private DebtorDTO extractDebtorWithDebts(Debtor debtor) {
			
		Iterable<Debt> debts = debtRepository.findByDebtor(debtor);
		Stream<Debt> debtsStream = StreamSupport.stream(debts.spliterator(), true);
		
		return DebtorDTO.builder()
					.debtorId(debtor.getDebtorId())
					.debtorName(debtor.getDebtorName())
					.debtorNick(debtor.getDebtorNick())
					.debtsDto(debtsStream.map(this::extractDebt).collect(Collectors.toSet()))
					.build();
		
	}
	
	@Override
	@Transactional
	public DebtDTO addDebt(DebtValue debtValue) {
	
		Optional<Debtor> opDebtor = debtorRepository.findById(debtValue.getDebtorId());
			
		if(opDebtor.isPresent()) {
			Debt debtEntity = Debt.builder()
					.dateDebt(debtValue.getDateDebt())
					.description(debtValue.getDescription())
					.moneyValue(debtValue.getMoneyValue())
					.paid(debtValue.getPaid())
					.sendable(debtValue.getSendable())
					.debtor(opDebtor.get())
					.build();
			debtEntity = debtRepository.save(debtEntity);
			return this.extractDebt(debtEntity);
		}
		return null;
	}

	@Override
	@Transactional
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

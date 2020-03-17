package br.com.charges.app.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.charges.app.dto.DebtorDTO;
import br.com.charges.app.entity.Debtor;
import br.com.charges.app.model.DebtorValue;
import br.com.charges.app.repository.DebtorRepository;
import br.com.charges.app.service.DebtorService;

@Service
public class DebtorServiceImpl implements DebtorService{

	@Autowired
	DebtorRepository debtorRepository;
		
	@Override
	public List<DebtorDTO> getAll() {
		
		Iterable<Debtor> debtors = debtorRepository.findAll();

		return this.execute(StreamSupport.stream(debtors.spliterator(), true));
		
	}

	@Override
	public List<DebtorDTO> getAllByName(String name) {
		Iterable<Debtor> debtors = debtorRepository.findBydebtorName(name);
		return this.execute(StreamSupport.stream(debtors.spliterator(), true));
	}

	@Override
	public Optional<DebtorDTO> getById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addDebtor(DebtorValue debtor) {
		// TODO Auto-generated method stub
		
	}

	private List<DebtorDTO> execute(Stream<Debtor> debtors) {
		
		return debtors.map(this::extractDebtors).collect(Collectors.toList());
		
	}
	
	private DebtorDTO extractDebtors(Debtor debtor) {
		
		return DebtorDTO.builder()
				.debtorName(debtor.getDebtorName())
				.debtorNick(debtor.getDebtorNick())
				.debtorEmail(debtor.getDebtorEmail())
				.debtorId(debtor.getDebtorId())
				.build();
	}
	
	
	
	
}

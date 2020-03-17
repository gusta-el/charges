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
	public DebtorDTO getById(Long id) {
		Optional<Debtor> opDebtor = debtorRepository.findById(id);
		
		if(opDebtor.isPresent()) {
			return this.extractDebtor(opDebtor.get());
		}
		
		return null;
	
	}

	@Override
	public DebtorDTO addDebtor(DebtorValue debtor) {

		Debtor debtorEntity = Debtor.builder()
				.debtorEmail(debtor.getDebtorEmail())
				.debtorName(debtor.getDebtorName())
				.debtorNick(debtor.getDebtorNick())
				.build();
		
		debtorEntity = debtorRepository.save(debtorEntity);
		
		return this.extractDebtor(debtorEntity);
				
	}

	@Override
	public DebtorDTO changeDebtor(DebtorValue debtorValue) {

		Optional<Debtor> opDebtor = debtorRepository.findById(debtorValue.getDebtorId());
		
		opDebtor.ifPresent(debtor -> {
			debtor.setDebtorEmail(debtorValue.getDebtorEmail());
			debtor.setDebtorName(debtorValue.getDebtorName());
			debtor.setDebtorNick(debtorValue.getDebtorNick());
			debtor = debtorRepository.save(debtor);
		});
				
		if(opDebtor.isPresent()) {
			return this.extractDebtor(opDebtor.get());
		}
		
		return null;
		
	}

	private List<DebtorDTO> execute(Stream<Debtor> debtors) {
		
		return debtors.map(this::extractDebtor).collect(Collectors.toList());
		
	}
	
	private DebtorDTO extractDebtor(Debtor debtor) {
		
		return DebtorDTO.builder()
				.debtorName(debtor.getDebtorName())
				.debtorNick(debtor.getDebtorNick())
				.debtorEmail(debtor.getDebtorEmail())
				.debtorId(debtor.getDebtorId())
				.build();
	}
	
}

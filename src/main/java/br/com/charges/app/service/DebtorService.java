package br.com.charges.app.service;

import java.util.List;
import java.util.Optional;

import br.com.charges.app.dto.DebtorDTO;
import br.com.charges.app.model.DebtorValue;

public interface DebtorService {

	List<DebtorDTO> getAll();
	List<DebtorDTO> getAllByName(String name);
	Optional<DebtorDTO> getById(Long id);
	
	void addDebtor(DebtorValue debtor);
		
}

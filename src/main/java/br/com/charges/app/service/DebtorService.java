package br.com.charges.app.service;

import java.util.List;

import br.com.charges.app.dto.DebtorDTO;
import br.com.charges.app.model.DebtorValue;

public interface DebtorService {

	List<DebtorDTO> getAll();
	List<DebtorDTO> getAllByName(String name);
	DebtorDTO getById(Long id);
	
	DebtorDTO addDebtor(DebtorValue debtor);
	DebtorDTO changeDebtor(DebtorValue debtor);
	
}

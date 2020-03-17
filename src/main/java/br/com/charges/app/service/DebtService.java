package br.com.charges.app.service;

import java.util.List;
import java.util.Optional;

import br.com.charges.app.dto.DebtDTO;
import br.com.charges.app.model.DebtValue;

public interface DebtService {


	List<DebtDTO> getAll();
	List<DebtDTO> getAllByName(String name);
	Optional<DebtDTO> getById(Long id);
	
	void addDebt(DebtValue debt);
		
}

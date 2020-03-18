package br.com.charges.app.service;

import java.util.List;

import br.com.charges.app.dto.DebtDTO;
import br.com.charges.app.model.DebtValue;

public interface DebtService {


	List<DebtDTO> getAll();
	List<DebtDTO> getPaidDebts(Boolean paid);
	
	DebtDTO addDebt(DebtValue debt);
	DebtDTO changeDebt(DebtValue debt);
		
}

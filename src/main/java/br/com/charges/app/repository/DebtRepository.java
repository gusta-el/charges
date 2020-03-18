package br.com.charges.app.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.charges.app.entity.Debt;
import br.com.charges.app.entity.Debtor;

public interface DebtRepository extends CrudRepository<Debt, Long>{

	Iterable<Debt> findByPaid(boolean paid);
	Iterable<Debt> findByDebtor(Debtor debtor);
	
	
}

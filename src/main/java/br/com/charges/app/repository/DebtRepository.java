package br.com.charges.app.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.charges.app.entity.Debt;

public interface DebtRepository extends CrudRepository<Debt, Long>{

	Iterable<Debt> findByPaid(boolean paid);
	
}

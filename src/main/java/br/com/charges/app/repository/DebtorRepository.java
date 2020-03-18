package br.com.charges.app.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.charges.app.entity.Debtor;

public interface DebtorRepository extends CrudRepository<Debtor, Long>{

	Iterable<Debtor> findBydebtorName(String name);
	
}

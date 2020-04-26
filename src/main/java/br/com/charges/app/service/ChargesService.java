package br.com.charges.app.service;

import java.util.stream.Stream;

import br.com.charges.app.model.Debtorable;

public interface ChargesService {

	Stream<Debtorable> execute();
	void pay(Long id);
	
}

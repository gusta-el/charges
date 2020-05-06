package br.com.charges.app.controller;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.charges.app.dto.DebtDTO;
import br.com.charges.app.dto.DebtRequestDTO;
import br.com.charges.app.dto.DebtorDTO;
import br.com.charges.app.model.DebtValue;
import br.com.charges.app.service.DebtService;

@RestController
@CrossOrigin
@RequestMapping(path = "charges")
public class DebtController {
	
	@Autowired
	DebtService debtService;
	
	@GetMapping(value="/debts")
    @ResponseBody
    public ResponseEntity<List<DebtDTO>> listAllDebts() {
		
		List<DebtDTO> debts = debtService.getAll();
		
		return ResponseEntity.ok(debts);
    }
	
	@GetMapping(value="/debts-by")
    @ResponseBody
    public ResponseEntity<List<DebtDTO>> listDebtorsByName(@RequestParam Boolean paid) {
		
		List<DebtDTO> debts = debtService.getPaidDebts(paid);
		
		return ResponseEntity.ok(debts);
    }
	
	@GetMapping(value="/debts-by-name/{name}")
    @ResponseBody
    public ResponseEntity<List<DebtorDTO>> listDebtsByName(@PathVariable("name") String name) {
		
		List<DebtorDTO> debts = debtService.getDebtsByDebtorName(name);
		
		return ResponseEntity.ok(debts);
    }

	@PostMapping(value="/debts")
    @ResponseBody
    public ResponseEntity<DebtDTO> addDebtor(@RequestBody DebtRequestDTO debt) {
		
		DebtValue debtValue = this.transform(debt, null);
		DebtDTO debtDto = debtService.addDebt(debtValue);
		
		return ResponseEntity.ok(debtDto);
    
	}

	@PatchMapping(value="/debts/{id}")
    @ResponseBody
    public ResponseEntity<DebtDTO> changeDebtor(@PathVariable("id") Long id, @RequestBody DebtRequestDTO debt) {
		
		DebtValue debtValue = this.transform(debt, id);
		DebtDTO debtDto = debtService.changeDebt(debtValue);
		
		return ResponseEntity.ok(debtDto);
    
	}
		
	private DebtValue transform(DebtRequestDTO debt, Long debtId) {
		
		return DebtValue.builder()
				.dateDebt(debt.getDateDebt())
				.description(debt.getDescription())
				.moneyValue(debt.getMoneyValue())
				.paid(debt.getPaid())
				.sendable(debt.getSendable())
				.debtorId(debt.getDebtorId())
				.debtId(Objects.nonNull(debtId)? debtId : null)
				.build();
		
	}

}

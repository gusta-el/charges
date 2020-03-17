package br.com.charges.app.controller;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.charges.app.dto.DebtorDTO;
import br.com.charges.app.dto.DebtorRequestDTO;
import br.com.charges.app.model.DebtorValue;
import br.com.charges.app.service.DebtorService;

@RestController
@RequestMapping(path = "charges")
public class DebtorController {
	
	@Autowired
	DebtorService debtorService;
	
	@GetMapping(value="/debtors")
    @ResponseBody
    public ResponseEntity<List<DebtorDTO>> listAllDebtors() {
		
		List<DebtorDTO> debtors = debtorService.getAll();
		
		return ResponseEntity.ok(debtors);
    }
	
	@GetMapping(value="/debtors-by")
    @ResponseBody
    public ResponseEntity<List<DebtorDTO>> listDebtorsByName(@RequestParam String name) {
		
		List<DebtorDTO> debtors = debtorService.getAllByName(name);
		
		return ResponseEntity.ok(debtors);
    }
	
	@GetMapping(value="/debtors/{id}")
    @ResponseBody
    public ResponseEntity<DebtorDTO> getDebtorById(@PathVariable("id") Long id) {
		
		DebtorDTO debtorDto = debtorService.getById(id);
		
		return ResponseEntity.ok(debtorDto);
    
	}

	@PostMapping(value="/debtors")
    @ResponseBody
    public ResponseEntity<DebtorDTO> addDebtor(@RequestBody DebtorRequestDTO debtor) {
		
		DebtorValue debtorValue = this.transform(debtor, null);
		DebtorDTO debtorDto = debtorService.addDebtor(debtorValue);
		
		return ResponseEntity.ok(debtorDto);
    
	}

	@PatchMapping(value="/debtors/{id}")
    @ResponseBody
    public ResponseEntity<DebtorDTO> addDebtor(@PathVariable("id") Long id, @RequestBody DebtorRequestDTO debtor) {
		
		DebtorValue debtorValue = this.transform(debtor, id);
		DebtorDTO debtorDto = debtorService.changeDebtor(debtorValue);
		
		return ResponseEntity.ok(debtorDto);
    
	}
	private DebtorValue transform(DebtorRequestDTO debtor, Long id) {
		
		return DebtorValue.builder()
				.debtorEmail(debtor.getDebtorEmail())
				.debtorName(debtor.getDebtorName())
				.debtorNick(debtor.getDebtorNick())
				.debtorId(Objects.nonNull(id)? id : null)
				.build();
		
	}

}

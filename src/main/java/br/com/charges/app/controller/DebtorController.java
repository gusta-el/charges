package br.com.charges.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.charges.app.dto.DebtorDTO;
import br.com.charges.app.dto.DebtorRequestDTO;
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
	
	@GetMapping(value="/{id}")
    @ResponseBody
    public ResponseEntity<DebtorRequestDTO> listDebtorsById() {
		
		chargesService.execute();
		
		return ResponseEntity.ok(null);
    }

	

}

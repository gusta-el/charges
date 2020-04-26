package br.com.charges.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.charges.app.service.ChargesService;

@RestController
@RequestMapping(path = "charges")
public class ChargesController{
	
	@Autowired
	ChargesService service;

	@GetMapping
    @ResponseBody
    public String validate() {
		return "Seu barriga no ar!!";
    }
	
	@PutMapping
    @ResponseBody
    public ResponseEntity<Void> payDebt(@RequestParam Long debtId) {
		
		service.pay(debtId);
		return ResponseEntity.ok().build();
		
    }
	
}

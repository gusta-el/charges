package br.com.charges.app.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
	
	@Value("${email.adress}")
	String emailAdress;

	@Value("${email.password}")
	String emailPassword;

	@Value("${spring.datasource.url}")
	String dataSource;
	
	@GetMapping
    @ResponseBody
    public String validate() {
		
		String validate = "emailAdress: " + emailAdress + "\n"
				+ "emailPassword: " + emailPassword + "\n"
				+ "dataSource: " + dataSource; 
				
		return validate;
    }
	
}
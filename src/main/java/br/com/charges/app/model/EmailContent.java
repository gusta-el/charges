package br.com.charges.app.model;

import java.util.List;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = false)
public class EmailContent {

	private List<String> toAddressesTo;
	private List<String> toAddressesCc;
	private List<String> toAddressesCo;
	private String toAddressTo;
	private String subject;
	private String message;
	
}

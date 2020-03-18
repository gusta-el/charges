package br.com.charges.app.model;

public class EmailProperties {

	private String host;
	private String port;
	private String fromAdress;
	private String fromAdressPassword;
	
	public EmailProperties(String host, String port, String fromAdress,
			String fromAdressPassword) {
		super();
		this.host = host;
		this.port = port;
		this.fromAdress = fromAdress;
		this.fromAdressPassword = fromAdressPassword;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}


	public String getFromAdress() {
		return fromAdress;
	}

	public void setFromAdress(String fromAdress) {
		this.fromAdress = fromAdress;
	}

	public String getFromAdressPassword() {
		return fromAdressPassword;
	}

	public void setFromAdressPassword(String fromAdressPassword) {
		this.fromAdressPassword = fromAdressPassword;
	}
		
}
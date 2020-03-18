package br.com.charges.app.model;

public enum EmailPropertiesConstants {

	LIVE ("smtp.live.com", "587");
		
	private String host;
	private String port;
	
	private EmailPropertiesConstants(String host, String port) {
		this.host = host;
		this.port = port;
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
	
}

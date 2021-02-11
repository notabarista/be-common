package org.notabarista.service.util.enums;

public enum MicroService {
	USER_MANAGEMENT_SERVICE("USER-MANAGEMENT-SERVICE");

	private String microserviceName;

	private MicroService(String microserviceName) {
		this.microserviceName = microserviceName;
	}
	
	public String getMicroserviceName() {
		return this.microserviceName;
	}
}

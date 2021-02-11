package org.notabarista.exception;

public class MicroserviceNotFoundException extends AbstractNotabaristaException {

	private static final long serialVersionUID = -5663266518641209358L;

	public MicroserviceNotFoundException() {
		super("MicroService not found exception");
	}

}

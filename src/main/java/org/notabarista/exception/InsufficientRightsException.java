package org.notabarista.exception;

public class InsufficientRightsException extends AbstractNotabaristaException {

	private static final long serialVersionUID = 1598673551866395386L;

	public InsufficientRightsException() {
		super("Insufficient rights");
	}

}

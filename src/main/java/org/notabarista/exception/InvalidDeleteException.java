package org.notabarista.exception;

public class InvalidDeleteException extends AbstractNotabarristaException {

	private static final long serialVersionUID = -4227944535083226521L;

	public InvalidDeleteException(String reason) {
		super("Cannot handle delete action: " + reason);
	}

}

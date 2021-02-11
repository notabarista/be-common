package org.notabarista.exception;

public class EntityNotFoundException extends AbstractNotabaristaException {

	private static final long serialVersionUID = -37208445632757930L;

	public EntityNotFoundException() {
		super("Entity not found");
	}

}

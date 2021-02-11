package org.notabarista.controller;

import org.notabarista.entity.response.Response;
import org.notabarista.entity.response.ResponseStatus;
import org.notabarista.exception.AbstractNotabaristaException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import lombok.extern.log4j.Log4j2;

@ControllerAdvice
@Log4j2
public class ControllerErrorHandler {

	@ExceptionHandler({ AbstractNotabaristaException.class })
	public ResponseEntity<Response<String>> handleBusinessException(AbstractNotabaristaException e) {
		log.error(e.getMessage());
		return new ResponseEntity<>(new Response<String>(ResponseStatus.FAILED, 100, null, 0, 0, 0, 0, e.getMessage()),
				HttpStatus.FORBIDDEN);
	}

	@ExceptionHandler({ Exception.class })
	public ResponseEntity<Response<String>> handleException(Exception e) {
		log.error(e.getMessage(), e);
		return new ResponseEntity<>(
				new Response<String>(ResponseStatus.FAILED, 100, null, 0, 0, 0, 0, e.getClass().getSimpleName()),
				HttpStatus.FORBIDDEN);
	}

}

package net.diegoqueres.playlistportemperatura.controllers.exceptions;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import net.diegoqueres.playlistportemperatura.services.exceptions.ResourceNotFoundException;

/**
 * 
 * Responsável por controlar a saída de exceções nas requisições aos
 * controllers.
 * 
 * @author Diego Queres
 * @since 2 de jul de 2020
 *
 */
@ControllerAdvice
public class ControllerExceptionHandler {
	private static final Logger LOG = LoggerFactory.getLogger(ControllerExceptionHandler.class);

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request) {
		String error = "Resource not found";
		HttpStatus status = HttpStatus.NOT_FOUND;
		StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(),
				request.getRequestURI());
		LOG.warn("Resource not found {} {}: {}", status.value(), error, e.getMessage());
		return ResponseEntity.status(status).body(err);
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<StandardError> database(IllegalArgumentException e, HttpServletRequest request) {
		String error = "Invalid parameters were provided";
		HttpStatus status = HttpStatus.BAD_REQUEST;
		StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(),
				request.getRequestURI());
		LOG.warn("Invalid arguments {} {}: {}", status.value(), error, e.getMessage());
		return ResponseEntity.status(status).body(err);
	}

	@ExceptionHandler(MissingServletRequestParameterException.class)
	public ResponseEntity<StandardError> database(MissingServletRequestParameterException e,
			HttpServletRequest request) {
		String error = "Required parameters is missing to process the request";
		HttpStatus status = HttpStatus.BAD_REQUEST;
		StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(),
				request.getRequestURI());
		LOG.warn("Required parameters is missing {} {}: {}", status.value(), error, e.getMessage());
		return ResponseEntity.status(status).body(err);
	}

	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<StandardError> database(RuntimeException e, HttpServletRequest request) {
		String error = "System internal error";
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		StandardError err = new StandardError(Instant.now(), status.value(), error, "", request.getRequestURI());
		LOG.error("System internal error {} {}: {}", status.value(), error, e.getMessage());
		return ResponseEntity.status(status).body(err);
	}

}

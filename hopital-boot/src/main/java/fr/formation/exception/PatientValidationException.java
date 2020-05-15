package fr.formation.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(
		value = HttpStatus.BAD_REQUEST,
		reason = "La patient n'a pas pu �tre valid�")
public class PatientValidationException extends RuntimeException {
	private static final long serialVersionUID = 1L;
}
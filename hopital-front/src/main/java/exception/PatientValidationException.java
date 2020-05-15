package exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(
		value = HttpStatus.BAD_REQUEST,
		reason = "La patient n'a pas pu être validé")
public class PatientValidationException extends RuntimeException {
	private static final long serialVersionUID = 1L;
}
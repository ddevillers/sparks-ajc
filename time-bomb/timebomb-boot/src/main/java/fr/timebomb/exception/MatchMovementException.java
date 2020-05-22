package fr.timebomb.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "This move is impossible.")
public class MatchMovementException extends RuntimeException {
	private static final long serialVersionUID = 1L;
}
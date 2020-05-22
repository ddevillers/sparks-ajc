package fr.timebomb.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.FORBIDDEN, reason = "This is not your turn.")
public class MatchNotYourTurnException extends RuntimeException {
	private static final long serialVersionUID = 1L;
}
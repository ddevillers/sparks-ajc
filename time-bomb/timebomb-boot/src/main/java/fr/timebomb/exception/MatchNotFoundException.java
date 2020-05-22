package fr.timebomb.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Match not found.")
public class MatchNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;
}
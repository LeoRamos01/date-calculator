package lramos.datecalculator.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidProporcaoParameterException extends RuntimeException {

	private static final long serialVersionUID = 8209554176001475573L;
	
	public InvalidProporcaoParameterException(String msg) {
		super(msg);
	}
	
}

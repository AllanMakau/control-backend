package br.com.mksoftware.control.exceptions;

public class SendMqErrorException extends RuntimeException  {

	private static final long serialVersionUID = 1L;
	
	
	public SendMqErrorException(String message) {
		super(message);
	}
	
	public SendMqErrorException(String message, Throwable cause) {
		super(message, cause);
	}

}

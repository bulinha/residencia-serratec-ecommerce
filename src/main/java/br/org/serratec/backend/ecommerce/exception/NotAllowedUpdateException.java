package br.org.serratec.backend.ecommerce.exception;

public class NotAllowedUpdateException extends Exception {
	private static final long serialVersionUID = 1L;


	public NotAllowedUpdateException(String message) {
		super(message);
	}

	public NotAllowedUpdateException(Throwable cause) {
		super(cause);
	}

	public NotAllowedUpdateException(String message, Throwable cause) {
		super(message, cause);
	}

	public NotAllowedUpdateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}

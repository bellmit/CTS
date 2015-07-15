package exception;

public class PayRollProcessingException extends Exception {

	public PayRollProcessingException(String message) {
		super(message);
	}

	public PayRollProcessingException(Throwable throwable) {
		super(throwable);
	}
}

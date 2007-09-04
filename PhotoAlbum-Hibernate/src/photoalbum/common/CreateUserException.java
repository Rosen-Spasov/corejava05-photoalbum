package photoalbum.common;

public class CreateUserException extends Exception {
	
	private static final long serialVersionUID = 915786778368722578L;

	public static final String DEFAULT_MESSAGE = "Cannot create user.";
	
	private String message;
	
	private Throwable cause = null;
	
	public CreateUserException(String message) {
		this(message, null);
	}
	
	public CreateUserException(Throwable cause) {
		this(DEFAULT_MESSAGE, cause);
	}
	
	public CreateUserException(String message, Throwable cause) {
		this.message = message;
		this.cause = cause;
	}

	@Override
	public String getMessage() {
		return this.message;
	}

	@Override
	public Throwable getCause() {
		return this.cause;
	}
	
}

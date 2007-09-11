package photoalbum;

public class CreateCategoryException extends Exception {

	private static final long serialVersionUID = 5247240421655979101L;

	public static final String DEFAULT_MESSAGE = "Cannot create category.";
	
	private String message;
	
	private Throwable cause = null;
	
	public CreateCategoryException(String message) {
		this(message, null);
	}
	
	public CreateCategoryException(Throwable cause) {
		this(DEFAULT_MESSAGE, cause);
	}
	
	public CreateCategoryException(String message, Throwable cause) {
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

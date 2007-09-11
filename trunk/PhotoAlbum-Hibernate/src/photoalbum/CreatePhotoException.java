package photoalbum;

public class CreatePhotoException extends Exception {

	private static final long serialVersionUID = 6932408609814266215L;

	public static final String DEFAULT_MESSAGE = "Cannot create photo.";
	
	private String message;
	
	private Throwable cause = null;
	
	public CreatePhotoException(String message) {
		this(message, null);
	}
	
	public CreatePhotoException(Throwable cause) {
		this(DEFAULT_MESSAGE, cause);
	}
	
	public CreatePhotoException(String message, Throwable cause) {
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

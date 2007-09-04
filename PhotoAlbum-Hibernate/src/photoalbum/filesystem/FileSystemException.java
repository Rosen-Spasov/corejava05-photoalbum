package photoalbum.filesystem;

public class FileSystemException extends Exception {
	
	public static final String DEFAULT_MESSAGE = "Cannot update the file system.";

	private static final long serialVersionUID = -3540649481365995862L;
	
	private String message;
	
	private Throwable cause = null;
	
	public FileSystemException(String message) {
		this(message, null);
	}
	
	public FileSystemException(Throwable cause) {
		this(DEFAULT_MESSAGE, cause);
	}
	
	public FileSystemException(String message, Throwable cause) {
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

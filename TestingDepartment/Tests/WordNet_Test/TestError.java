
public class TestError extends Exception {

	public TestError(Throwable cause) {
		super(cause);
	}
	public TestError(String message) {
	    super(message);
	}
	public TestError(String message, Throwable cause) {
	    super(message, cause);
	}
}

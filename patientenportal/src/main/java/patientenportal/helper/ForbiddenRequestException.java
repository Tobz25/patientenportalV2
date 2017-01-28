package patientenportal.helper;

public class ForbiddenRequestException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7607296716457706636L;

	public ForbiddenRequestException(String message) {
		super(message);
	}

}

package patientenportal.helper;

public class UnauthorizedException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7607296716457706636L;

	public UnauthorizedException(String message) {
		super(message);
	}

}

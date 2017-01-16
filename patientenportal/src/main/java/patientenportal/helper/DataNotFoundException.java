package patientenportal.helper;


public class DataNotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7607296716457706636L;

	public DataNotFoundException(String message) {
		super(message);
	}

}
package at.ticketline.dao;

public class DaoException extends RuntimeException {

	private static final long serialVersionUID = 1322350870962805526L;

	public DaoException() {
		super();
	}
	
	public DaoException(String message) {
		super(message);
	}
	
	public DaoException(String message, Throwable cause) {
		super(message,cause);
	}
	
	public DaoException(Throwable cause) {
		super(cause);
	}

}

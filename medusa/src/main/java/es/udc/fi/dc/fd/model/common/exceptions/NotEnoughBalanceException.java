package es.udc.fi.dc.fd.model.common.exceptions;

/**
 * The Class InstanceNotFoundException.
 */
@SuppressWarnings("serial")
public class NotEnoughBalanceException extends Exception{
	
	
	private final String message;



	/**
	 * @param message
	 */
	public NotEnoughBalanceException(String message) {
		super();
		this.message = message;
	}


	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	
	
	


}

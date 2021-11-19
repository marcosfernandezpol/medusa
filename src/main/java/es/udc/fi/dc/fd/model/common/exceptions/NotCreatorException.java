package es.udc.fi.dc.fd.model.common.exceptions;

@SuppressWarnings("serial")
public class NotCreatorException extends Exception {

	private final String message;

	/**
		 * @param message
		 */
		public NotCreatorException() {
			super();
			this.message = "You are not creator's enterprise";
		}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

}

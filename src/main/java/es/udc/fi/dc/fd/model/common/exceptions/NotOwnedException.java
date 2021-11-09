package es.udc.fi.dc.fd.model.common.exceptions;

@SuppressWarnings("serial")
public class NotOwnedException extends Exception {

	private final String message;

	/**
		 * @param message
		 */
		public NotOwnedException() {
			super();
			this.message = "You do not own these stocks from this enterprise";
		}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

}

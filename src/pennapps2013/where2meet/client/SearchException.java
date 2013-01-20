package pennapps2013.where2meet.client;

import java.io.Serializable;

public class SearchException extends Exception implements Serializable {

	private static final long serialVersionUID = -4243634570719820969L;
	
	private String error;
	
	@SuppressWarnings("unused")
	private SearchException() {
		error = null;
	}
	
	public SearchException(String error) {
		super();
		this.error = error;
	}

	/**
	 * The JSONObject representing the error. It has fields id, text, and sometimes field.
	 * A null object corresponds to a parse error
	 * @return gets the error
	 */
	public String getError() {
		return error;
	}
	
	@Override
	public String toString() {
		return error.toString() + '\n' + super.toString();
	}
}
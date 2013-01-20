package pennapps2013.where2meet.server;

import org.json.simple.JSONObject;

public class SearchException extends Exception {

	private static final long serialVersionUID = -4243634570719820969L;
	
	private JSONObject error;
	
	public SearchException(JSONObject error) {
		super();
		this.error = error;
	}

	/**
	 * The JSONObject representing the error. It has fields id, text, and sometimes field.
	 * A null object corresponds to a parse error
	 * @return gets the error
	 */
	public JSONObject getError() {
		return error;
	}
	
	@Override
	public String toString() {
		return error.toString() + '\n' + super.toString();
	}
}
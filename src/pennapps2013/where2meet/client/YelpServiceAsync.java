package pennapps2013.where2meet.client;

import java.util.ArrayList;
import java.util.Map;


import com.google.gwt.user.client.rpc.AsyncCallback;

public interface YelpServiceAsync {
	
	public void getPlaces(Map<String, String> params, AsyncCallback<ArrayList<Business>> callback);
}

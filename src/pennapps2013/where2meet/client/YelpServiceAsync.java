package pennapps2013.where2meet.client;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface YelpServiceAsync {
	
	public void getPlaces(LatLng center, double radius, AsyncCallback<ArrayList<Business>> callback);
}

package pennapps2013.where2meet.client;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("getplaces")
public interface YelpService extends RemoteService {
	
	public ArrayList<Business> getPlaces(LatLng center, double radius);
}

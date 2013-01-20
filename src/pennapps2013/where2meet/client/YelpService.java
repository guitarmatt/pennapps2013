package pennapps2013.where2meet.client;

import java.util.ArrayList;
import java.util.Map;


import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("getplaces")
public interface YelpService extends RemoteService {
	
	public ArrayList<Business> getPlaces(Map<String, String> params) throws SearchException;
}

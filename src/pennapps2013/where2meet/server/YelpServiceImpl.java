package pennapps2013.where2meet.server;

import java.util.ArrayList;

import pennapps2013.where2meet.client.Business;
import pennapps2013.where2meet.client.LatLng;
import pennapps2013.where2meet.client.YelpService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class YelpServiceImpl extends RemoteServiceServlet implements YelpService {

	private static final long serialVersionUID = 912867845481348169L;

	@Override
	public ArrayList<Business> getPlaces(LatLng center, double radius) {
		// TODO Auto-generated method stub
		return null;
	}

}

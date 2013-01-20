package pennapps2013.where2meet.server;

import java.util.ArrayList;
import java.util.Map;

import pennapps2013.where2meet.client.Business;
import pennapps2013.where2meet.client.LatLng;
import pennapps2013.where2meet.client.SearchException;
import pennapps2013.where2meet.client.YelpService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class YelpServiceImpl extends RemoteServiceServlet implements YelpService {

	private static final long serialVersionUID = 912867845481348169L;
	
	private static final String CONSUMER_KEY = "z4KpsjZJuox3LhN3ye2tYA";
	private static final String CONSUMER_SECRET = "ZcV-nLhQPRt-eGaDZ_2k_wrHOnk";
	private static final String TOKEN = "FT9J_QwrCGWtAEjoqymjKTbi64JJpRcS";
	private static final String TOKEN_SECRET = "0OSTGbiyT8axy2b-fbXZcoV-naM";
	
	private YelpQuery yelp = new YelpQuery(CONSUMER_KEY, CONSUMER_SECRET, TOKEN, TOKEN_SECRET);

	@Override
	public ArrayList<Business> getPlaces(Map<String, String> params) throws SearchException {
		return yelp.search(params);
	}

}

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.scribe.builder.ServiceBuilder;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.oauth.OAuthService;

public class YelpQuery {

	public static final String yelp = "http://api.yelp.com/v2/search";
	private static final String CONSUMER_KEY = "z4KpsjZJuox3LhN3ye2tYA";
	private static final String CONSUMER_SECRET = "ZcV-nLhQPRt-eGaDZ_2k_wrHOnk";
	private static final String TOKEN = "FT9J_QwrCGWtAEjoqymjKTbi64JJpRcS";
	private static final String TOKEN_SECRET = "0OSTGbiyT8axy2b-fbXZcoV-naM";

	private OAuthService service;
	private Token accessToken;

	public YelpQuery(String consumerKey, String consumerSecret, String token,
			String tokenSecret) {
		this.service = new ServiceBuilder().provider(YelpApi2.class)
				.apiKey(consumerKey).apiSecret(consumerSecret).build();
		this.accessToken = new Token(token, tokenSecret);
	}

	@SuppressWarnings("unchecked")
	public ArrayList<Business> search(Map<String, String> params) throws SearchException {
		OAuthRequest request = new OAuthRequest(Verb.GET,
				"http://api.yelp.com/v2/search");
		for (Entry<String, String> param : params.entrySet())
			request.addQuerystringParameter(param.getKey(), param.getValue());
		this.service.signRequest(this.accessToken, request);
		Response response = request.send();
		try {
			ArrayList<Business> businesses = new ArrayList<Business>();
			JSONObject json = (JSONObject)(new JSONParser()).parse(response.getBody());
			JSONObject error = (JSONObject) json.get("error");
			if (error != null) throw new SearchException(error);
			ArrayList<Map<String, Object>> places = (ArrayList<Map<String, Object>>) json.get("businesses");
			for (Map<String, Object> place : places) {
				Business b = new Business();
				if (Boolean.TRUE.equals(place.get("is_closed")))
					continue;
				b.name = (String)place.get("name");
				b.yelpUrl = (String)place.get("url");
				Map<String, Object> location = (Map<String, Object>) place.get("location");
				ArrayList<String> address = (ArrayList<String>) location.get("display_address");
				b.address = address.toArray(new String[0]);
				b.phone = (String)place.get("display_phone");
				b.rating = (Double)place.get("rating");
				b.reviews = (Long)place.get("review_count");
				b.distance = (Double)place.get("distance") / 1609.34;
				businesses.add(b);
			}
			return businesses;
		} catch (ParseException e) {
			throw new SearchException(null);
		}
	}
	
	public static void main(String[] args) throws SearchException {
		YelpQuery query = new YelpQuery(CONSUMER_KEY, CONSUMER_SECRET, TOKEN, TOKEN_SECRET);
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("term", "five guys");
		params.put("limit", "10");
		params.put("ll", 39.951791 + "," + -75.190211);
		params.put("radius_filter", Integer.toString(15000));
		ArrayList<Business> places = query.search(params);
		if (places == null) return;
		System.out.println(places);		
	}
}
package pennapps2013.where2meet.server;

import org.scribe.builder.api.DefaultApi10a;
import org.scribe.model.Token;


/**
 * Service provider for "2-legged" OAuth10a for Yelp API (version 2).
 */
public class YelpApi2 extends DefaultApi10a {

  @Override
  public String getAccessTokenEndpoint() {
    return null;
  }

  @Override
  public String getAuthorizationUrl(Token token) {
    return null;
  }

  @Override
  public String getRequestTokenEndpoint() {
    return null;
  }

}

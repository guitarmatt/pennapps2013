package pennapps2013.where2meet.client;

import java.io.Serializable;


public class Business implements Serializable {

	private static final long serialVersionUID = -9002316995660938073L;
	
	public String name;
	public String yelpUrl;
	public String[] address;
	public String phone;
	public double rating;
	public long reviews;
	public double distance;
	
	@Override
	public String toString() {
		StringBuffer b = new StringBuffer(name + '\n' + yelpUrl + "\n\n");
		for (String a : address)
			b.append(a + '\n');
		b.append(phone + '\n' + distance + " miles\n\nRating: " + rating + " (" + reviews + " reviews)");
		return b.toString();
	}
}

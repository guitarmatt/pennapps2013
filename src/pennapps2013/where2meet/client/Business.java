
public class Business {
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

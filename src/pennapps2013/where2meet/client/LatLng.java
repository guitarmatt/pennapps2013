import org.jfree.data.xy.XYSeries;

public class LatLng {

	double lat;
	double lng;
	
	public LatLng(double lat, double lng) {
		this.lat = lat;
		this.lng = lng;
	}
	
	public void addTo(XYSeries points) {
		points.add(lat, lng);
	}
	
	@Override
	public String toString() {
		return "(" + lat + "," + lng + ")";
	}
}

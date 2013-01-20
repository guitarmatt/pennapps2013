package pennapps2013.where2meet.client;

import java.io.Serializable;

//import org.jfree.data.xy.XYSeries;

public class LatLng implements Serializable {

	private static final long serialVersionUID = -3440826043019366322L;
	
	double lat;
	double lng;
	
	private LatLng() {
		this.lat = this.lng = 0;
	}
	
	public LatLng(double lat, double lng) {
		this.lat = lat;
		this.lng = lng;
	}
	
	/*
	public void addTo(XYSeries points) {
		points.add(lat, lng);
	}
	*/
	
	@Override
	public String toString() {
		return "(" + lat + "," + lng + ")";
	}
}

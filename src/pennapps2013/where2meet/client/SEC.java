package pennapps2013.where2meet.client;

import java.util.Arrays;
import java.util.Collections;

public class SEC {
	
	// Returns (m, k) for equation of line, not a LatLng
	private static LatLng getLine(LatLng p1, LatLng p2) {
		if (Math.abs(p1.lng - p2.lng) <= Euclidean.EPS) return null;
		double m = - (p1.lat - p2.lat) / (p1.lng - p2.lng);
		double k = (Math.pow(p1.lat, 2) - Math.pow(p2.lat, 2)) / (2 * (p1.lng - p2.lng)) +
				(p1.lng + p2.lng) / 2;
		return new LatLng(m, k);
	}
	
	private static Circle computeCircle(LatLng[] points) {
		switch (points.length) {
		case 0:
			return new Circle(0,0,0);
		case 1:
			return new Circle(points[0].lat, points[0].lng, 0);
		case 2:
			double midlat = (points[0].lat + points[1].lat) / 2;
			double midlng = (points[0].lng + points[1].lng) / 2;
			double rad = Euclidean.norm(midlat - points[0].lat, midlng - points[0].lng);
			return new Circle(midlat, midlng, rad);
		default:
			// TODO: Clean?
			LatLng line1 = getLine(points[0], points[1]), line2 = null;
			if (line1 == null) {
				line1 = getLine(points[0], points[2]);
				line2 = getLine(points[1], points[2]);
				if (line1 == null || line2 == null) // points are (horizontally) collinear
					return null;
			}
			else {
				line2 = getLine(points[1], points[2]);
				if (line2 == null) {
					line2 = getLine(points[0], points[2]);
					if (line2 == null) // points are (horizontally) collinear
						return null;
				}
			}
	
		if (Math.abs(line1.lat - line2.lat) <= Euclidean.EPS) // points are collinear
			return null;
		
		double centerX = - (line1.lng - line2.lng) / (line1.lat - line2.lat);
		double centerY = line1.lat * centerX + line1.lng;
		double radius = Euclidean.norm(points[0].lat - centerX, points[0].lng - centerY);
		Circle circle = new Circle(centerX, centerY, radius);
		
		for (int i = 3; i < points.length; i++) {
			if (!circle.onBoundary(points[i].lat, points[i].lng)) return null;
		}
		return circle;
		}
	}
	
	private static Circle smallestEnclosing(LatLng[] points, int ix, LatLng[] bnd) {
		if (ix >= points.length || bnd.length >= 3)
			return computeCircle(bnd);
		LatLng[] copyBnd = Arrays.copyOf(bnd, bnd.length + 1);
		Circle c = smallestEnclosing(points, ix + 1, bnd);
		if (c != null && !c.inside(points[ix].lat, points[ix].lng)) {
			copyBnd[copyBnd.length - 1] = points[ix];
			c = smallestEnclosing(points, ix + 1, copyBnd);
		}
		return c;
	}
	
	public static Circle findCenter(LatLng[] points) {
		Collections.shuffle(Arrays.asList(points));
		Circle c = smallestEnclosing(points, 0, new LatLng[0]);
		// TODO adjust radius
		return c;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		LatLng[] points = new LatLng[] {
//				new LatLng(30, 78),
//				new LatLng(14, 65),
//				new LatLng(21, 60)
				new LatLng(30.5483432, 78.3928322),
				new LatLng(14.4324334, 65.3829849),
				new LatLng(21.6569860, 60.4383493),
				new LatLng(23.4, 100.7)
		};
	
		Circle c = findCenter(points);
		for (LatLng l : points)
			assert (c.inside(l.lat, l.lng));
		System.out.println(findCenter(points));
	}

}

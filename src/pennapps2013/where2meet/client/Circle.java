
public class Circle {
	
	public double centerX;
	public double centerY;
	public double radius;
	
	public Circle(double cx, double cy, double r) {
		centerX = cx;
		centerY = cy;
		radius = r;
	}
	
	public boolean inside(double x, double y) {
		return Euclidean.norm(x - centerX, y - centerY) <= radius + Euclidean.EPS;
	}
	
	public boolean onBoundary(double x, double y) {
		return Math.abs(Euclidean.norm(x - centerX, y - centerY) - radius) <= Euclidean.EPS;
	}
	
	@Override
	public String toString() {
		return "(" + centerX + ',' + centerY + ") @ " + radius;
	}
}

package pennapps2013.where2meet.client;


public final class Euclidean {

	/**
	 * Floating point arithmetic error tolerance
	 */
	public static final double EPS = 0.0000000001;
	
	/**
	 * Euclidean norm
	 */
	public static double norm(double x, double y) {
		return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
	}
}

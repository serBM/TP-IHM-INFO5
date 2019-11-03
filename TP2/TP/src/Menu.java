/*
 * This class Menu creates a circular menu
 * using the radius given and the objects in the String Array provided
 */

public class Menu {

	int radius;
	int size;
	public String objects[];
	double angle;

	public Menu(int rayon, String objects[]) {
		this.radius = rayon;
		this.objects = objects;
		this.size = this.objects.length;
		this.angle = Math.toRadians(360 / this.size) / 2;
	}

	public int getArea(int centerX, int centerY, int x, int y) {
		double dist = Math.sqrt((centerX - x) * (centerX - x) + (centerY - y) * (centerY - y));
		if (dist > radius) {
			return 0;
		} else {
			double angle = Math.toDegrees(Math.atan2(centerY - centerY, centerX - (centerX + radius)) -
					Math.atan2(centerY - y, centerX - x));
			int area = (int) ((angle*size)/360) + 1;
			return area;
		}
	}
	
	public int getRadius() {
		return this.radius;
	}
	
	public int getSize() {
		return this.size;
	}
	
	public double getAngle() {
		return this.angle;
	}
	
	
}

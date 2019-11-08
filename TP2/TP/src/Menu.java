import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

/*
 * This class Menu creates a circular menu
 * using the radius given and the objects in the String Array provided
 */

public class Menu {

	int radius;
	int size;
	public String objects[];
	double angle;
	int textRadius;
	Point point;

	public Menu(int rayon, int textRadius, String objects[]) {
		this.radius = rayon;
		this.objects = objects;
		this.size = this.objects.length;
		this.angle = Math.toRadians(360 / this.size) / 2;
		this.textRadius = textRadius;
		point = new Point();
	}

	public int getArea(int centerX, int centerY, int x, int y) {
		double dist = Math.sqrt((centerX - x) * (centerX - x) + (centerY - y) * (centerY - y));
		if (dist > radius) {
			return 0;
		} else {
			double angle = Math.toDegrees(
					Math.atan2(centerY - centerY, centerX - (centerX + radius)) - Math.atan2(centerY - y, centerX - x));
			int area = (int) ((angle * size) / 360) + 1;
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

	public int getTextRadius() {
		return this.textRadius;
	}
	
	public void setPoint(Point p) {
		point = p;
	}
	
	public Point getPoint() {
		return point;
	}

	public void draw(Graphics g2) {
		Color colorBackground = new Color(238, 238, 238);
		Color colorBorder = new Color(214, 214, 214);
		Color colorText = new Color(86, 86, 86);

		g2.setColor(colorBackground);
		g2.fillOval(point.x - this.getRadius(), point.y - this.getRadius(), this.getRadius() * 2, this.getRadius() * 2);
		g2.setColor(colorBorder);
		g2.drawOval(point.x - this.getRadius(), point.y - this.getRadius(), this.getRadius() * 2, this.getRadius() * 2);
		for (int i = 1; i <= this.getSize(); i++) {
			double angle = Math.toRadians((360 / this.getSize()) * i);
			double angleText = angle + this.getAngle();
			int xi = (int) (this.getRadius() * Math.cos(angle) + point.x);
			int yi = (int) (this.getRadius() * Math.sin(angle) + point.y);
			int xt = (int) (this.getTextRadius() * Math.sin(angleText) + point.x);
			int yt = (int) (this.getTextRadius() * Math.sin(angleText) + point.y);
			g2.setColor(colorBorder);
			g2.drawLine(point.x, point.y, xi, yi);
			g2.setColor(colorText);
			g2.drawString(this.objects[i - 1], xt - (this.objects[i - 1].length() * g2.getFont().getSize()) / 4,
					yt + g2.getFont().getSize() / 4);
		}
	}

}

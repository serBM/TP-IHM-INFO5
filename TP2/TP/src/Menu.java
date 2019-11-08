import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

/*
 * This class Menu creates a circular menu
 * using the radius given and the objects in the String Array provided
 */

public class Menu {

	int menuRayon;
	int textRayon;
	int size;
	public String objects[];
	double angleForText;
	
	Point point;

	public Menu(int rayon, int textRadius, String objects[]) {
		
		this.menuRayon = rayon;
		this.textRayon = textRadius;
		
		this.objects = objects;
		
		this.size = this.objects.length;
		
		this.angleForText = Math.toRadians(360 / this.size)/2;
		
		point = new Point();
	}

	public int getArea(int centerX, int centerY, int x, int y) {
		double dist = Math.sqrt((centerX - x) * (centerX - x) + (centerY - y) * (centerY - y));
		if (dist > menuRayon) {
			return 0;
		} else {
			double angle = Math.toDegrees(
					Math.atan2(centerY - centerY, centerX - (centerX + menuRayon)) - Math.atan2(centerY - y, centerX - x));
			int area = (int) ((angle * size) / 360) + 1;
			return area;
		}
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
		g2.fillOval(point.x - menuRayon, point.y - menuRayon, menuRayon * 2, menuRayon * 2);
		g2.setColor(colorBorder);
		g2.drawOval(point.x - menuRayon, point.y - menuRayon, menuRayon * 2, menuRayon * 2);
		for (int i = 1; i <= size; i++) {
			double angle = Math.toRadians((360 / size) * i);
			double angleText = angle + angleForText;
			int xi = (int) (menuRayon * Math.cos(angle) + point.x);
			int yi = (int) (menuRayon * Math.sin(angle) + point.y);
			int xt = (int) (textRayon * Math.cos(angleText) + point.x);
			int yt = (int) (textRayon * Math.sin(angleText) + point.y);
			g2.setColor(colorBorder);
			g2.drawLine(point.x, point.y, xi, yi);
			g2.setColor(colorText);
			g2.drawString(objects[i - 1], xt - (objects[i - 1].length() * g2.getFont().getSize()) / 4,
					yt + g2.getFont().getSize() / 4);
		}
	}

}

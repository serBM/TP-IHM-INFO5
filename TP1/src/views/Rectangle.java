package views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Rectangle extends JPanel {
	
	int width;
	
	public Rectangle(int width) {
		this.width = width;
	}
	protected void paintComponent(Graphics g) {
		// Draw a rectangle using Rectangle2D class
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.RED);

		double x = 0;
		double y = 0;
		double height = 20;

		// Draw the red rectangle
		g2.draw(new Rectangle2D.Double(x, y, this.width, height));

	}
}
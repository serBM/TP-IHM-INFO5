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
	
	int width, height;
	
	public Rectangle(int width, int height) {
		this.width = width;
		this.height = height;
        this.setPreferredSize(new Dimension(width,height+10));
	}
	
	
	protected void paintComponent(Graphics g) {
		// Draw a rectangle using Rectangle2D class
		Graphics2D g2 = (Graphics2D) g;
		
	
		int sizeButtonWidth = width/7;
		int sizeButtonHeight = height+5;
	
		
		
		g2.setColor(Color.RED);

		// Draw the red rectangle
		g2.draw(new Rectangle2D.Double(0, 4, width/3, height));
		
		g2.setColor(Color.BLUE);
		g2.draw(new Rectangle2D.Double(width/3, 0, width/7, height+5));
		g2.fillRect(width/3, 0, width/7, height+5);
		
		g2.draw(new Rectangle2D.Double(width/3+width/7, 4, width/7, height));
		
		
		g2.draw(new Rectangle2D.Double(2*width/3, 0, width/7, height+5));
		g2.fillRect(2*width/3, 0, width/7, height+5);
	
		g2.draw(new Rectangle2D.Double(2*width/3+width/7, 4, width/3, height));
	}
}
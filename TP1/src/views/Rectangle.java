package views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Rectangle extends JPanel {
	
	int width, height;
	
	public Rectangle(int width, int height) {
		this.width = width;
		this.height = height;
        this.setPreferredSize(new Dimension(width,height+10));
	}
	
	
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		
	
		int sizeButtonWidth = width/30;
		int sizeButtonHeight = height+5;
	
		int a = width/7;
		
		g2.setColor(Color.RED);
		g2.draw(new Rectangle2D.Double(0, 3, a, height));
		
		
		g2.setColor(Color.BLUE);
		g2.draw(new Rectangle2D.Double(a, 0, sizeButtonWidth, sizeButtonHeight));
		g2.fill(new Rectangle2D.Double(a, 0, sizeButtonWidth, sizeButtonHeight));
		
		g2.setColor(Color.RED);
		g2.draw(new Rectangle2D.Double(a+sizeButtonWidth, 3, a, height));
		g2.fill(new Rectangle2D.Double(a+sizeButtonWidth, 3, a, height));
		
		g2.setColor(Color.BLUE);
		g2.draw(new Rectangle2D.Double(a+sizeButtonWidth+a, 0, sizeButtonWidth, sizeButtonHeight));
		g2.fill(new Rectangle2D.Double(a+sizeButtonWidth+a, 0, sizeButtonWidth, sizeButtonHeight));

		g2.setColor(Color.RED);
		g2.draw(new Rectangle2D.Double(2*(a+sizeButtonWidth), 3, a, height));
		
		
	}
}
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
	int sizeButtonWidth;
	int sizeButtonHeight;

	public Rectangle(int width, int height) {
		this.width = width;
		this.height = height;
		this.sizeButtonWidth = width / 30;
		this.sizeButtonHeight = height + 5;
		this.setPreferredSize(new Dimension(width, height + 10));
	}

	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
	}
}
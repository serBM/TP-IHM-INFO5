package views;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

class Rectangle extends JComponent {

	public void paint(Graphics g) {
		g.drawRect(10, 10, 200, 200);
	}
}
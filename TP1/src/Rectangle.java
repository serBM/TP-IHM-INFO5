
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JComponent;

class Rectangle extends JComponent {
	private final Color couleurCellule;

	public Rectangle(final Color c) {
		couleurCellule = c;
		MouseTranslateListener.addToComponent(this);
	}

	@Override
	public void paintComponent(final Graphics g) {
		super.paintComponent(g);
		g.setColor(couleurCellule);
		g.fillRect(50, 150, 200, 300);
	}
}

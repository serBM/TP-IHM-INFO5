package controller;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class Controller extends JPanel {

	Point pStart;
	Point pEnd;
	Point buttonMin;// = new Point(40, 10);
	Point buttonMax;// = new Point(230, 10);
	Point pMin; // = new Point(10, 10);
	Point pMax;// = new Point(290, 10);
	int wb, hb;
	//int ww = 300, hw = 200;

	public Controller(int xmin, int ymin, int xmax, int ymax, int wb, int hb, JTextField text1, JTextField text2) {
		

		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				pStart = e.getPoint();
			}
		});

		addMouseMotionListener(new MouseAdapter() {
			public void mouseDragged(MouseEvent e) {
				pEnd = e.getPoint();
				if ((pEnd.x >= pMin.x) && (pEnd.x + wb <= pMax.x)) {
					if ((pStart.x >= buttonMin.x) && (pStart.x <= buttonMin.x + wb) && (pStart.y >= buttonMin.y)
							&& (pStart.y <= buttonMin.y + hb) && (pEnd.x + wb <= buttonMax.x)) {
						buttonMin.x = pEnd.x;
					}
					if ((pStart.x >= buttonMax.x) && (pStart.x <= buttonMax.x + wb) && (pStart.y >= buttonMax.y)
							&& (pStart.y <= buttonMax.y + hb) && (pEnd.x >= buttonMin.x + wb)) {
						buttonMax.x = pEnd.x;
					}
					repaint();
					
					String point = Integer.toString(pEnd.x);
					text1.setText(point);
				}
				pStart = pEnd;
			}
		});
		this.wb = wb;
		this.hb = hb;
		pMin = new Point(xmin, ymin);
		pMax = new Point(xmax, ymax);
		buttonMin = new Point(xmin, ymin);
		buttonMax = new Point(xmax-wb, ymin);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Color blue = new Color(100, 155, 201);
		Color grey = new Color(216, 215, 215);

		((Graphics2D) g).setColor(grey);
		((Graphics2D) g).fill(new Rectangle2D.Double(pMin.x, pMin.y, buttonMin.x - pMin.x, hb));

		((Graphics2D) g).setColor(Color.WHITE);
		((Graphics2D) g).fill(new Rectangle2D.Double(buttonMin.x, buttonMin.y, wb, hb));

		((Graphics2D) g).setColor(blue);
		((Graphics2D) g)
				.fill(new Rectangle2D.Double(buttonMin.x + wb, buttonMin.y, buttonMax.x - buttonMin.x - wb, hb));

		((Graphics2D) g).setColor(Color.WHITE);
		((Graphics2D) g).fill(new Rectangle2D.Double(buttonMax.x, buttonMax.y, wb, hb));

		((Graphics2D) g).setColor(grey);
		((Graphics2D) g).fill(new Rectangle2D.Double(buttonMax.x + wb, pMax.y, pMax.x - buttonMax.x - wb, hb));
	}
}

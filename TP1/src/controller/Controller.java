package controller;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;

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

	public Controller(int xmin, int ymin, int xmax, int ymax, int wb, int hb) {
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

		((Graphics2D) g).setColor(Color.RED);
		((Graphics2D) g).draw(new Rectangle2D.Double(pMin.x, pMin.y, buttonMin.x - pMin.x, hb));

		((Graphics2D) g).setColor(Color.BLUE);
		((Graphics2D) g).fill(new Rectangle2D.Double(buttonMin.x, buttonMin.y, wb, hb));

		((Graphics2D) g).setColor(Color.RED);
		((Graphics2D) g)
				.fill(new Rectangle2D.Double(buttonMin.x + wb, buttonMin.y, buttonMax.x - buttonMin.x - wb, hb));

		((Graphics2D) g).setColor(Color.BLUE);
		((Graphics2D) g).fill(new Rectangle2D.Double(buttonMax.x, buttonMax.y, wb, hb));

		((Graphics2D) g).setColor(Color.RED);
		((Graphics2D) g).draw(new Rectangle2D.Double(buttonMax.x + wb, pMax.y, pMax.x - buttonMax.x - wb, hb));
	}
}

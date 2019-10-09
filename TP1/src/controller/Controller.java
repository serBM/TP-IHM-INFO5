package controller;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.Model;

/*
 * generates a range slider
 */
@SuppressWarnings("serial")
public class Controller extends JPanel {

	Model m;
	Point pStart;
	Point pEnd;
	Point buttonMin;
	Point buttonMax;
	Point pMin;
	Point pMax;
	int wb, hb;

	public Controller(int xmin, int ymin, int xmax, int ymax, int wb, int hb, JTextField text1, JTextField text2) {

		this.wb = wb;
		this.hb = hb;
		pMin = new Point(xmin, ymin);
		pMax = new Point(xmax, ymax);
		buttonMin = new Point(xmin, ymin);
		buttonMax = new Point(xmax - wb, ymin);
		m = new Model(Integer.parseInt(text1.getText()), Integer.parseInt(text2.getText()), pMin.x, pMax.x);

		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				pStart = e.getPoint();
			}
		});

		addMouseMotionListener(new MouseAdapter() {
			public void mouseDragged(MouseEvent e) {
				pEnd = e.getPoint();
				if ((pEnd.x >= pMin.x - (wb / 2)) && (pEnd.x + (wb / 2) <= pMax.x)) {
					if ((pStart.x >= buttonMin.x) && (pStart.x <= buttonMin.x + wb) && (pStart.y >= buttonMin.y)
							&& (pStart.y <= buttonMin.y + hb) && (pEnd.x + wb <= buttonMax.x)) {
						buttonMin.x = pEnd.x;
						text1.setText(m.getValue(buttonMin.x, wb));
					}
					if ((pStart.x >= buttonMax.x) && (pStart.x <= buttonMax.x + wb) && (pStart.y >= buttonMax.y)
							&& (pStart.y <= buttonMax.y + hb) && (pEnd.x >= buttonMin.x + wb)) {
						buttonMax.x = pEnd.x;
						text2.setText(m.getValue(buttonMax.x, wb));
					}
				}
				pStart = pEnd;
				repaint();
			}
		});

		text1.addActionListener(new ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				int n = Integer.parseInt(text1.getText());
				if ((n >= m.getMin()) && (n <= m.getMax()) && (m.setValue(n, wb) + wb <= buttonMax.x)) {
					buttonMin.x = m.setValue(n, wb);
				}
				repaint();
			}
		});

		text2.addActionListener(new ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				int n = Integer.parseInt(text2.getText());
				if ((n >= m.getMin()) && (n <= m.getMax()) && (m.setValue(n, wb) >= buttonMin.x + wb)) {
					buttonMax.x = m.setValue(n, wb);
				}
				repaint();
			}
		});
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

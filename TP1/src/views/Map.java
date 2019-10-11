package views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.List;

import javax.swing.JPanel;

import model.Home;

public class Map extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	int width, height;
	
	int xA, xB, yA, yB;
	
	int dist;
	
	List<Home> listCriteria;
	
	public Map(int width, int height, int dist, int xA, int yA, int xB, int yB, List<Home> listCriteria) {
		this.width = width;
		this.height = height;
		
		this.xA = xA;
		this.yA = yA;
		
		this.xB = xB;
		this.yB = yB;
		
		this.dist = dist;
		
		this.listCriteria = listCriteria;
		
		setPreferredSize(new Dimension(width, height));
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		

		((Graphics2D) g).setColor(Color.WHITE);
		((Graphics2D) g).fill(new Rectangle2D.Double(0,0, width, height));

		((Graphics2D) g).setColor(Color.RED);
		((Graphics2D) g).fillOval(xA,yA,10,10);
		((Graphics2D) g).drawString("A", xA-10, yA);
		//drawCircle(g, xA, yA, dist);

		((Graphics2D) g).setColor(Color.RED);
		((Graphics2D) g).fillOval(xB,yB,10,10);
		((Graphics2D) g).drawString("B", xB-10, yB);
		//drawCircle(g, xB, yB, dist);
		
		((Graphics2D) g).setColor(Color.BLACK);

		for (int i=0; i<listCriteria.size(); i++) {
			((Graphics2D) g).drawString(Integer.toString(listCriteria.get(i).getId()+1), listCriteria.get(i).getX(), listCriteria.get(i).getY()-10);
			((Graphics2D) g).fillOval(listCriteria.get(i).getX(),listCriteria.get(i).getY(),5,5);
		}

		
	}
	
	/*
	 * this function draws a circle according to its coordinates: x and y
	 */
	public static void drawCircle(Graphics g, int x, int y, int radius) {

		  int diameter = radius * 2;

		  g.drawOval(x - radius, y - radius, diameter, diameter);

		}
}

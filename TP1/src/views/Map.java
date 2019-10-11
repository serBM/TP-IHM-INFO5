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
	 * this class draws a map containing the points A and B
	 * and draws also the different homes
	 */
	private static final long serialVersionUID = 1L;

	int width, height; //size of the panel map
	
	int xA, xB, yA, yB; //coordinates of the point A and B
		
	List<Home> listCriteria; //the list of homes to display in the map
	
	/*
	 * construct a map of size (width, height)
	 * with the points A and B 
	 * and the homes in the listCriteria
	 */
	public Map(int width, int height, int xA, int yA, int xB, int yB, List<Home> listCriteria) {
		this.width = width;
		this.height = height;
		
		this.xA = xA;
		this.yA = yA;
		
		this.xB = xB;
		this.yB = yB;
	
		this.listCriteria = listCriteria;
		
		setPreferredSize(new Dimension(width, height));
	}

	/*
	 * draws the component in the Map panel
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		//background of the map
		((Graphics2D) g).setColor(Color.WHITE);
		((Graphics2D) g).fill(new Rectangle2D.Double(0,0, width, height));

		//point A
		((Graphics2D) g).setColor(Color.RED);
		((Graphics2D) g).fillOval(xA,yA,10,10);
		((Graphics2D) g).drawString("A", xA-10, yA);

		//point B
		((Graphics2D) g).setColor(Color.RED);
		((Graphics2D) g).fillOval(xB,yB,10,10);
		((Graphics2D) g).drawString("B", xB-10, yB);
		
		//homes
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

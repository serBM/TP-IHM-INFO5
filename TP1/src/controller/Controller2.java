package controller;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

public class Controller2 implements MouseListener {

	Point pStart;
	Point pEnd;
	JButton bMin;
	JButton bMax;

	public Controller2(JButton b1, JButton b2) {
		bMin = b1;
		bMax = b2;
		bMin.addMouseListener(this);
		bMax.addMouseListener(this);
	}

	public void mouseClicked(MouseEvent arg0) {
	}

	public void mouseEntered(MouseEvent arg0) {
	}

	public void mouseExited(MouseEvent arg0) {
	}

	public void mousePressed(MouseEvent arg0) {
		if(arg0.getSource() == bMin) {
			pStart = bMin.getLocation();
			System.out.println("bMin - Mouse pressed : " + pStart);
		}
		else {
			pStart = bMax.getLocation();
			System.out.println("bMax - Mouse pressed : " + pStart);
		}
	}

	public void mouseReleased(MouseEvent arg0) {
		pEnd = arg0.getPoint();
		if(arg0.getSource() == bMin) {
			bMin.setLocation(pEnd.x + pStart.x, pStart.y);
			System.out.println("bMin - Mouse released : " + pEnd);
		}
		else {
			bMax.setLocation(pEnd.x + pStart.x, pStart.y);
			System.out.println("bMax - Mouse released : " + pEnd);
		}
	}

}

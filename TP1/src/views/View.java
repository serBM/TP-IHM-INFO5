package views;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextField;

import views.Rectangle;

public class View extends JFrame{
	public View() {
		
		this.setTitle("Range Slider");
		this.setSize(400,200);

		WindowAdapter l = new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		};

		this.addWindowListener(l);
		
		JPanel container = new JPanel();
		

		JPanel panelLeft = new JPanel();
		JPanel panelRight = new JPanel();
		//Rectangle rectangle = new Rectangle();
		
		JTextField inputLeft = new JTextField("0");
		
		panelLeft.add(inputLeft);
		
		JTextField inputRight = new JTextField("100");
		
		panelRight.add(inputRight);

		JButton btnLeft = new JButton("-");
		JButton btnRight = new JButton("+");
		
		container.add(panelLeft);
		//container.add(rectangle);
		container.add(panelRight);
		

		this.setContentPane(container);
		
		this.getContentPane().add(new Rectangle());
		this.setVisible(true);


	}




	public static void main(String[] args) {
		View v = new View();
		

	}
}

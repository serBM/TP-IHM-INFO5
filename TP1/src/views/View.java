package views;

import controller.Controller;

import java.awt.Container;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

public class View extends JFrame{

	public View() {
		
		setTitle("Range Slider");
		setSize(300,100);
		
		Container cPane = this.getContentPane();
		cPane.add(new Controller(10, 10, 290, 10, 15, 15));
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		
		setVisible(true);
	}

	public static void main(String[] args) {
		
		View view = new View();

	}
}

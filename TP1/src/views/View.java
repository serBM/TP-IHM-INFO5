package views;

import controller.Controller;

import java.awt.Container;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

public class View {

	public View() {

	}

	public static void main(String[] args) {
		JFrame jFrame = new JFrame();
		jFrame.setTitle("");
		jFrame.setSize(300, 200);
		jFrame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		Container cPane = jFrame.getContentPane();
		cPane.add(new Controller(10, 10, 290, 10, 15, 15));
		jFrame.setVisible(true);
	}
}

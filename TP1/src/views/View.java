package views;

import controller.Controller;
//import javafx.scene.layout.HBox;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class View extends JFrame{

	public View() {
		
		setTitle("Range Slider");
		setSize(380,60);
		
		//Container cPane = this.getContentPane();
		//cPane.add(new Controller(10, 10, 290, 10, 15, 15));
		
		
		
		JTextField text1 = new JTextField("0");
		text1.setPreferredSize( new Dimension( 40, 50));
		
		JTextField text2 = new JTextField("100");
		text2.setPreferredSize( new Dimension( 40, 50 ) );

		Controller slider = new Controller(10, 10, 290, 10, 15, 15, text1, text2);
		
		JPanel mainPanel = new JPanel(new BorderLayout());
		mainPanel.add(text1, BorderLayout.WEST);
        mainPanel.add(slider, BorderLayout.CENTER);
        mainPanel.add(text2, BorderLayout.EAST);

        
        add(mainPanel);
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		
		setVisible(true);
	}

	public static void main(String[] args) {
		
		@SuppressWarnings("unused")
		View view = new View();

	}
}

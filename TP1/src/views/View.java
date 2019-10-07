package views;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import views.Rectangle;

@SuppressWarnings("serial")
public class View extends JFrame{
	
	
	public View() {
		setTitle("Range slider");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        JPanel panelLeft = panelLeft();
        add(panelLeft, BorderLayout.WEST);
        
        JPanel panelCenter = panelCenter();
        add(panelCenter, BorderLayout.CENTER);
        
        JPanel panelRight = panelRight();
        add(panelRight, BorderLayout.EAST);

        this.setSize(800, 40);
        pack();
        setVisible(true);
	}
	
    private JPanel panelRight() {
    	JPanel panel = new JPanel();
        panel.add(new JTextField("100"));
        return panel;
	}

	private JPanel panelCenter() {
        JPanel panel = new JPanel();
        Rectangle rectangle = new Rectangle(800,20);
        panel.add(rectangle);
        return panel;
	}

	private JPanel panelLeft() {
    	 JPanel panel = new JPanel();
    	 panel.add(new JTextField("0"));
         return panel;
	}

	@SuppressWarnings("unused")
	public static void main(String[] args) {
        View view = new View();
    }
}


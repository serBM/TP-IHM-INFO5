package views;
import controller.Controller;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import views.Rectangle;

@SuppressWarnings("serial")
public class View extends JFrame{

	private JPanel pan = new JPanel();
	private JButton bMin = new JButton("1");
	private JButton bMax = new JButton("2");
	private Controller c = new Controller(bMin, bMax);


	
	public View() {
		
		// CODE JADE
		
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
        
        // CODE MAXENCE
        
        /*
		this.setLayout(null);
		pan.setLayout(null);
		this.setTitle("Test");
		this.setSize(400, 400);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		bMin.setBounds(10, 200, 10, 30);
		bMax.setBounds(380, 200, 10, 30);
		pan.add(bMin);
		pan.add(bMax);
		this.setContentPane(pan);
		this.setVisible(true);
		*/
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

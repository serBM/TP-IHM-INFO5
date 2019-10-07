package views;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import controller.Controller;

//CTRL + SHIFT + O pour générer les imports
@SuppressWarnings("serial")
public class View extends JFrame {

	private JPanel pan = new JPanel();
	private JButton bMin = new JButton("1");
	private JButton bMax = new JButton("2");
	private Controller c = new Controller(bMin, bMax);

	public View() {
		this.setLayout(null);
		pan.setLayout(null);
		this.setTitle("Test");
		this.setSize(400, 400);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		bMin.setBounds(0, 200, 10, 30);
		bMax.setBounds(200, 200, 10, 30);
		pan.add(bMin);
		pan.add(bMax);
		this.setContentPane(pan);
		this.setVisible(true);
	}

	public static void main(String[] args) {
		View v = new View();
	}
}

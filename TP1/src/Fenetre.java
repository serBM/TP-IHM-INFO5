import java.awt.Color;
import javax.swing.JFrame;

public class Fenetre extends JFrame  {

	private static int largeurFenetre = 900,
			        hauteurFenetre = 600;

        public Fenetre(String titre) {
    	      super(titre);
    	      Rectangle rect = new Rectangle(Color.GRAY);
              rect.setBounds(100, 100, 200, 300);
              add(rect);


              setSize(largeurFenetre, hauteurFenetre);
              setLocationRelativeTo(null);
              setLayout(null);
              setResizable(false);
              setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }

        public static void main(String args[]) {
                new Fenetre("Test").setVisible(true);
        }

}
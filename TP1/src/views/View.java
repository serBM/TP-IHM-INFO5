package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.geom.Rectangle2D;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import views.Rectangle;

public class View extends JFrame{
	
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new JFrame("Draw Rectangle");
                frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                
                JPanel panelLeft = new JPanel();
                
                panelLeft.add(new JTextField("0"));
                panelLeft.add(new Rectangle(20));
                panelLeft.add(new JButton("-"));

                
                frame.add(panelLeft, BorderLayout.WEST);
                
                frame.add(new Rectangle(100), BorderLayout.CENTER);
                
                JPanel panelRight = new JPanel();

               
                panelRight.add(new JButton("+"));
                panelRight.add(new Rectangle(20));
                panelRight.add(new JTextField("100"));
               
                
                frame.add(panelRight, BorderLayout.EAST);

                frame.pack();
                frame.setVisible(true);
            }
        });
    }
}


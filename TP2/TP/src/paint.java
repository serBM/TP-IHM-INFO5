//////////////////////////////////////////////////////////////////////////////
// file    : Paint.java
// content : basic painting app
//////////////////////////////////////////////////////////////////////////////


/* imports *****************************************************************/

import static java.lang.Math.*;

import java.util.Vector;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.Point;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.event.*;
import javax.swing.event.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;


/* paint *******************************************************************/

class Paint extends JFrame {
	
	Vector<ShapeCustom> shapesCustom = new Vector<ShapeCustom>();
	
	Color actualColor = Color.BLACK;

	
	class ColorTool extends AbstractAction
    implements MouseInputListener {
		public ColorTool(String name) { super(name); }
		public void actionPerformed(ActionEvent e) {
			System.out.println("using color " + this);
			color = this;
			panel.addMouseListener(color);
		}
		public void mouseClicked(MouseEvent e) {}
		public void mouseEntered(MouseEvent e) {}
		public void mouseExited(MouseEvent e) {}
		public void mousePressed(MouseEvent e) { }
		public void mouseReleased(MouseEvent e) { }
		public void mouseDragged(MouseEvent e) {}
		public void mouseMoved(MouseEvent e) {}
	}
	
	/*ColorTool colors[] = {
		new ColorTool("blue") {
				actualColor = Color.BLUE;
				System.out.println("tessst : " + actualColor);
		},
		new ColorTool("red") {
			public void mouseClicked(MouseEvent e) {
				actualColor = Color.RED;
			}
		}
	};*/
	
	
	class Tool extends AbstractAction
	           implements MouseInputListener {
	    Point o;
		Shape shape;
		public Tool(String name) { super(name); }
		public void actionPerformed(ActionEvent e) {
			System.out.println("using tool " + this);
			panel.removeMouseListener(tool);
			panel.removeMouseMotionListener(tool);
			tool = this;
			panel.addMouseListener(tool);
			panel.addMouseMotionListener(tool);
		}
		public void mouseClicked(MouseEvent e) {}
		public void mouseEntered(MouseEvent e) {}
		public void mouseExited(MouseEvent e) {}
		public void mousePressed(MouseEvent e) { o = e.getPoint(); }
		public void mouseReleased(MouseEvent e) { shape = null; }
		public void mouseDragged(MouseEvent e) {}
		public void mouseMoved(MouseEvent e) {}
	}
	
	Tool tools[] = {
		new Tool("pen") {
			public void mouseDragged(MouseEvent e) {
				Path2D.Double path = (Path2D.Double)shape;
				if(path == null) {
					path = new Path2D.Double();
					path.moveTo(o.getX(), o.getY());
					
					//shapes.add(shape = path);
					System.out.println("actualColor : " + actualColor);
					ShapeCustom sc = new ShapeCustom((shape = path), actualColor);
					shapesCustom.add(sc);
				}
				path.lineTo(e.getX(), e.getY());
				panel.repaint();
			}
		},
		new Tool("rectangle") {
			public void mouseDragged(MouseEvent e) {
				Rectangle2D.Double rect = (Rectangle2D.Double)shape;
				if(rect == null) {
					rect = new Rectangle2D.Double(o.getX(), o.getY(), 0, 0);
					//shapes.add(shape = rect);
					ShapeCustom sc = new ShapeCustom((shape = rect), actualColor);
					shapesCustom.add(sc);
				}
				rect.setRect(min(e.getX(), o.getX()), min(e.getY(), o.getY()),
				             abs(e.getX()- o.getX()), abs(e.getY()- o.getY()));
				panel.repaint();
			}
		},
		new Tool("ellipse") {
			public void mouseDragged(MouseEvent e) {
				Ellipse2D.Double ell = (Ellipse2D.Double)shape;
				if(ell == null) {
					ell = new Ellipse2D.Double(o.getX(), o.getY(), 0, 0);
					//shapes.add(shape = ell);
					ShapeCustom sc = new ShapeCustom((shape = ell), actualColor);
					shapesCustom.add(sc);
				}
				ell.setFrame(min(e.getX(), o.getX()), min(e.getY(), o.getY()),
				             abs(e.getX()- o.getX()), abs(e.getY()- o.getY()));
				panel.repaint();
			}
		},
		new Tool("round rectangle") {
			public void mouseDragged(MouseEvent e) {
				RoundRectangle2D.Double roundRect = (RoundRectangle2D.Double)shape;
				if(roundRect == null) {
					roundRect = new RoundRectangle2D.Double(o.getX(), o.getY(), 0, 0, 30, 30);
					//shapes.add(shape = roundRect);
					ShapeCustom sc = new ShapeCustom((shape = roundRect), actualColor);
					shapesCustom.add(sc);
				}
				roundRect.setFrame(min(e.getX(), o.getX()), min(e.getY(), o.getY()),
				             abs(e.getX()- o.getX()), abs(e.getY()- o.getY()));
				panel.repaint();
			}
		},
		new Tool("line") {
			public void mouseDragged(MouseEvent e) {
				Line2D.Double line = (Line2D.Double)shape;
				if(line == null) {
					line = new Line2D.Double(o.getX(), o.getY(), 0, 0);
					//shapes.add(shape = line);
					ShapeCustom sc = new ShapeCustom((shape = line), actualColor);
					shapesCustom.add(sc);
				}
				line.setLine(o.getX(), o.getY(),
				             abs(e.getX()), abs(e.getY()));
				panel.repaint();
			}
		},
		
	};
	
	ColorTool color;

	Tool tool;

	JPanel panel;
	
	public Paint(String title) {
		super(title);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setMinimumSize(new Dimension(800, 600));
		add(new JToolBar() {{
			for(AbstractAction tool: tools) {
				add(tool);
			}
		}}, BorderLayout.NORTH);
		
		
		JButton blue = new JButton("blue");
		blue.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				actualColor = Color.BLUE;
			}
        });
		
		JButton red = new JButton("red");
		red.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				actualColor = Color.RED;
			}
        });
		
		add(new JToolBar() {{
			
			add(blue);
			add(red);
			
			
		}}, BorderLayout.SOUTH);
		
		add(panel = new JPanel() {	
						
			public void paintComponent(Graphics g) {
				super.paintComponent(g);	
				Graphics2D g2 = (Graphics2D)g;
				g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
				                    RenderingHints.VALUE_ANTIALIAS_ON);
		
				g2.setColor(Color.WHITE);
				g2.fillRect(0, 0, getWidth(), getHeight());
								
				for(ShapeCustom shape: shapesCustom) {
					g2.setColor(shape.getColor());
					g2.draw(shape.getShape());
				}
			}
		});
		
		addMouseListener(new MouseAdapter() { 
	          public void mousePressed(MouseEvent me) { 
	            System.out.println(me); 
	          } 
	        }); 

		pack();
		setVisible(true);
	}


/* main *********************************************************************/

	public static void main(String argv[]) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				Paint paint = new Paint("paint");
			}
		});
	}
}

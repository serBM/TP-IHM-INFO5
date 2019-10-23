//////////////////////////////////////////////////////////////////////////////
// file    : Paint.java
// content : basic painting app
//////////////////////////////////////////////////////////////////////////////

/* imports *****************************************************************/

import static java.lang.Math.abs;
import static java.lang.Math.min;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.util.Vector;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;
import javax.swing.event.MouseInputListener;

/* paint *******************************************************************/

class Paint extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Vector<ShapeCustom> shapesCustom = new Vector<ShapeCustom>();

	Color actualColor = Color.BLACK;

	@SuppressWarnings("serial")
	class Tool extends AbstractAction implements MouseInputListener {
		Point o;
		Shape shape;

		public Tool(String name) {
			super(name);
		}

		public void actionPerformed(ActionEvent e) {
			System.out.println("using tool " + this);
			panel.removeMouseListener(tool);
			panel.removeMouseMotionListener(tool);
			tool = this;
			panel.addMouseListener(tool);
			panel.addMouseMotionListener(tool);
		}

		public void mouseClicked(MouseEvent e) {
		}

		public void mouseEntered(MouseEvent e) {
		}

		public void mouseExited(MouseEvent e) {
		}

		public void mousePressed(MouseEvent e) {
			o = e.getPoint();
		}

		public void mouseReleased(MouseEvent e) {
			shape = null;
		}

		public void mouseDragged(MouseEvent e) {
		}

		public void mouseMoved(MouseEvent e) {
		}
	}

	@SuppressWarnings("serial")
	Tool tools[] = { new Tool("pen") {
			public void mouseDragged(MouseEvent e) {
			Path2D.Double path = (Path2D.Double) shape;
			if (path == null) {
				path = new Path2D.Double();
				path.moveTo(o.getX(), o.getY());
				ShapeCustom sc = new ShapeCustom((shape = path), actualColor);
				shapesCustom.add(sc);
			}
			path.lineTo(e.getX(), e.getY());
			panel.repaint();
		}
	}, new Tool("rectangle") {
				public void mouseDragged(MouseEvent e) {
			Rectangle2D.Double rect = (Rectangle2D.Double) shape;
			if (rect == null) {
				rect = new Rectangle2D.Double(o.getX(), o.getY(), 0, 0);
				ShapeCustom sc = new ShapeCustom((shape = rect), actualColor);
				shapesCustom.add(sc);
			}
			rect.setRect(min(e.getX(), o.getX()), min(e.getY(), o.getY()), abs(e.getX() - o.getX()),
					abs(e.getY() - o.getY()));
			panel.repaint();
		}
	}, new Tool("ellipse") {
		public void mouseDragged(MouseEvent e) {
			Ellipse2D.Double ell = (Ellipse2D.Double) shape;
			if (ell == null) {
				ell = new Ellipse2D.Double(o.getX(), o.getY(), 0, 0);
				ShapeCustom sc = new ShapeCustom((shape = ell), actualColor);
				shapesCustom.add(sc);
			}
			ell.setFrame(min(e.getX(), o.getX()), min(e.getY(), o.getY()), abs(e.getX() - o.getX()),
					abs(e.getY() - o.getY()));
			panel.repaint();
		}
	}, new Tool("round rectangle") {
		public void mouseDragged(MouseEvent e) {
			RoundRectangle2D.Double roundRect = (RoundRectangle2D.Double) shape;
			if (roundRect == null) {
				roundRect = new RoundRectangle2D.Double(o.getX(), o.getY(), 0, 0, 30, 30);
				ShapeCustom sc = new ShapeCustom((shape = roundRect), actualColor);
				shapesCustom.add(sc);
			}
			roundRect.setFrame(min(e.getX(), o.getX()), min(e.getY(), o.getY()), abs(e.getX() - o.getX()),
					abs(e.getY() - o.getY()));
			panel.repaint();
		}
	}, new Tool("line") {
		public void mouseDragged(MouseEvent e) {
			Line2D.Double line = (Line2D.Double) shape;
			if (line == null) {
				line = new Line2D.Double(o.getX(), o.getY(), 0, 0);
				ShapeCustom sc = new ShapeCustom((shape = line), actualColor);
				shapesCustom.add(sc);
			}
			line.setLine(o.getX(), o.getY(), abs(e.getX()), abs(e.getY()));
			panel.repaint();
		}
	},

	};

	Tool tool;
	JPanel panel;

	@SuppressWarnings("serial")
	public Paint(String title) {
		super(title);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setMinimumSize(new Dimension(800, 600));
		add(new JToolBar() {
			{
				for (AbstractAction tool : tools) {
					add(tool);
				}
			}
		}, BorderLayout.NORTH);
		
		JButton black = new JButton("black");
		black.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				actualColor = Color.BLACK;
			}
		});

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

		JButton green = new JButton("green");
		green.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				actualColor = Color.GREEN;
			}
		});
		
		JButton pink = new JButton("pink");
		pink.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				actualColor = Color.PINK;
			}
		});
		
		JButton yellow = new JButton("yellow");
		yellow.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				actualColor = Color.YELLOW;
			}
		});
		
		JButton magenta = new JButton("magenta");
		magenta.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				actualColor = Color.MAGENTA;
			}
		});

		add(new JToolBar() {
			{
				add(black);
				add(blue);
				add(red);
				add(green);
				add(pink);
				add(yellow);
				add(magenta);

			}
		}, BorderLayout.SOUTH);

		add(panel = new JPanel() {

			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				Graphics2D g2 = (Graphics2D) g;
				g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

				g2.setColor(Color.WHITE);
				g2.fillRect(0, 0, getWidth(), getHeight());

				for (ShapeCustom shape : shapesCustom) {
					g2.setColor(shape.getColor());
					g2.draw(shape.getShape());
				}
			}
		});

		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent me) {
				System.out.println(me);
				if(me.getButton() == MouseEvent.BUTTON3) {
		        	  System.out.println("Right Click!");
		          }
			}
	    
		});
	
		pack();
		setVisible(true);

	}

	/* main *********************************************************************/

	public static void main(String argv[]) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				@SuppressWarnings("unused")
				Paint paint = new Paint("paint");
			}
		});
	}
}

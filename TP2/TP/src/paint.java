//////////////////////////////////////////////////////////////////////////////
// file    : Paint.java
// content : basic painting app
//////////////////////////////////////////////////////////////////////////////

/* imports *****************************************************************/

import static java.lang.Math.abs;
import static java.lang.Math.min;

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
import javax.swing.SwingUtilities;
import javax.swing.event.MouseInputListener;

/* paint *******************************************************************/

class Paint extends JFrame {

	private static final long serialVersionUID = 1L;

	/*
	 * variables initialization
	 */

	Vector<ShapeCustom> shapesCustom = new Vector<ShapeCustom>();

	Color actualColor = Color.BLUE; // current color when no one is selected

	int openMenu = 0;

	int menuRadius = 100;
	int textRadius = 75;

	String menuNames[] = { "Colors", "Shapes", "← Back" };
	String menuShapesNames[] = { "Line", "Round rectangle", "Ellipse", "Rectangle", "Pen", "← Back" };
	String menuColorsNames[] = { "Magenta", "Yellow", "Pink", "Green", "Red", "Blue", "Black", "← Back" };

	Menu menu = new Menu(menuRadius, textRadius, menuNames);
	Menu menuShapes = new Menu(menuRadius, textRadius, menuShapesNames);
	Menu menuColors = new Menu(menuRadius, textRadius, menuColorsNames);

	Controller c = new Controller(menu, menuShapes, menuColors);

	Tool tool = null;
	JPanel panel;

	boolean mode_pad = true;
	boolean mode_expert = false;

	/*
	 * END variables initialization
	 */

	/*****************************************************************/

	/*
	 * Tool Class
	 */

	@SuppressWarnings("serial")
	class Tool extends AbstractAction implements MouseInputListener {
		Point o;
		Point expert;
		Shape shape;
		String name;

		public Tool(String name) {
			super(name);
			this.name = name;
		}

		public String getName() {
			return this.name;
		}

		public void actionPerformed(ActionEvent e) {
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
			for (int i = 0; i < shapesCustom.size(); i++) {
				if (shapesCustom.get(i).getSpec() == 1)
					shapesCustom.remove(shapesCustom.get(i));
			}
		}

		public void mouseReleased(MouseEvent e) {
			shape = null;
		}

		public void mouseDragged(MouseEvent e) {
		}

		public void mouseMoved(MouseEvent e) {
		}
	}

	/*****************************************************************/

	/*
	 * Tools list
	 */

	@SuppressWarnings("serial")
	Tool tools[] = { new Tool("pen") {
		public void mouseDragged(MouseEvent e) {
			if (SwingUtilities.isLeftMouseButton(e)) {
				Path2D.Double path = (Path2D.Double) shape;
				if (path == null) {
					path = new Path2D.Double();
					path.moveTo(o.getX(), o.getY());
					ShapeCustom sc = new ShapeCustom((shape = path), actualColor, 0);
					shapesCustom.add(sc);
				}
				path.lineTo(e.getX(), e.getY());
				panel.repaint();
			}
		}
	}, new Tool("rectangle") {
		public void mouseDragged(MouseEvent e) {
			if (SwingUtilities.isLeftMouseButton(e)) {
				Rectangle2D.Double rect = (Rectangle2D.Double) shape;
				if (rect == null) {
					rect = new Rectangle2D.Double(o.getX(), o.getY(), 0, 0);
					ShapeCustom sc = new ShapeCustom((shape = rect), actualColor, 0);
					shapesCustom.add(sc);
				}
				rect.setRect(min(e.getX(), o.getX()), min(e.getY(), o.getY()), abs(e.getX() - o.getX()),
						abs(e.getY() - o.getY()));
				panel.repaint();
			}
		}
	}, new Tool("ellipse") {
		public void mouseDragged(MouseEvent e) {
			if (SwingUtilities.isLeftMouseButton(e)) {
				Ellipse2D.Double ell = (Ellipse2D.Double) shape;
				if (ell == null) {
					ell = new Ellipse2D.Double(o.getX(), o.getY(), 0, 0);
					ShapeCustom sc = new ShapeCustom((shape = ell), actualColor, 0);
					shapesCustom.add(sc);
				}
				ell.setFrame(min(e.getX(), o.getX()), min(e.getY(), o.getY()), abs(e.getX() - o.getX()),
						abs(e.getY() - o.getY()));
				panel.repaint();
			}
		}
	}, new Tool("round rectangle") {
		public void mouseDragged(MouseEvent e) {
			if (SwingUtilities.isLeftMouseButton(e)) {
				RoundRectangle2D.Double roundRect = (RoundRectangle2D.Double) shape;
				if (roundRect == null) {
					roundRect = new RoundRectangle2D.Double(o.getX(), o.getY(), 0, 0, 30, 30);
					ShapeCustom sc = new ShapeCustom((shape = roundRect), actualColor, 0);
					shapesCustom.add(sc);
				}
				roundRect.setFrame(min(e.getX(), o.getX()), min(e.getY(), o.getY()), abs(e.getX() - o.getX()),
						abs(e.getY() - o.getY()));
				panel.repaint();
			}
		}
	}, new Tool("line") {
		public void mouseDragged(MouseEvent e) {
			if (SwingUtilities.isLeftMouseButton(e)) {
				Line2D.Double line = (Line2D.Double) shape;
				if (line == null) {
					line = new Line2D.Double(o.getX(), o.getY(), 0, 0);
					ShapeCustom sc = new ShapeCustom((shape = line), actualColor, 0);
					shapesCustom.add(sc);
				}
				line.setLine(o.getX(), o.getY(), abs(e.getX()), abs(e.getY()));
				panel.repaint();
			}
		}
	},

	};

	/*****************************************************************/

	/*
	 * Window
	 */

	@SuppressWarnings("serial")
	public Paint(String title) {
		super(title);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setMinimumSize(new Dimension(800, 600));
		setTitle("IHM TP2 - VINCENT, THOMAS, VANDAL - 2019 - INFO5");

		/*
		 * view with painted components - shapes - open the menu if a right click
		 * occurred
		 */
		add(panel = new JPanel() {
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				Graphics2D g2 = (Graphics2D) g;
				g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

				g2.setColor(Color.WHITE); // set the background color of the window
				g2.fillRect(0, 0, getWidth(), getHeight());

				/*
				 * draws the shapes in the window using their associated colors
				 */
				for (ShapeCustom shape : shapesCustom) {
					g2.setColor(shape.getColor());
					g2.draw(shape.getShape());
					if (shape.getSpec() == 1)
						g2.fill(shape.getShape());
				}
				/*
				 * right click opens menus where the mouse is located
				 */
				
				if (!mode_expert) {
					if (openMenu == 1) {
						menu.draw(g2);
					} else if (openMenu == 2) {
						menuShapes.draw(g2);
					} else if (openMenu == 3) {
						menuColors.draw(g2);
					}
				}
			}
		});

		JButton pad = new JButton("Pad");
		pad.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				mode_pad = true;
			}
		});

		JButton souris = new JButton("Souris");

		souris.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				mode_pad = false;
			}
		});

		JButton expert = new JButton("Expert");
		expert.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				mode_expert = !mode_expert;
			}
		});

		JPanel hbox = new JPanel();
		hbox.add(pad);
		hbox.add(souris);
		hbox.add(expert);
		panel.add(hbox);

		panel.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent me) {
				/*
				 * opens the menu when right click in the window only if no menu is already
				 * opened
				 */
				if ((mode_pad) && (SwingUtilities.isRightMouseButton(me)) && (openMenu == 0)) {
					openMenu = c.rightClick(openMenu, me.getPoint());
				}
				repaint();
			}

			public void mousePressed(MouseEvent me) {
				/*
				 * opens the menu when right click in the window only if no menu is already
				 * opened
				 */
				if ((!mode_pad) && (SwingUtilities.isRightMouseButton(me)) && (openMenu == 0)) {
					openMenu = c.pressMouse(openMenu, me.getPoint());
				}
				repaint();
			}

			public void mouseReleased(MouseEvent me) {
				if ((SwingUtilities.isRightMouseButton(me)) && (!mode_pad)) {
					openMenu = c.releaseMouse(openMenu, me.getPoint());
					actualColor = c.getColor();
					panel.removeMouseListener(tool);
					panel.removeMouseMotionListener(tool);
					tool = tools[c.getTool()];
					panel.addMouseListener(tool);
					panel.addMouseMotionListener(tool);
					repaint();
				}
			}

		});

		/*
		 * menu behaviour when the mouse moves, changes the behavior of the menu
		 */

		panel.addMouseMotionListener(new MouseAdapter() {

			public void mouseMoved(MouseEvent me) {
				colorIndicator(new Point(me.getX(), me.getY()));
				if (mode_pad) {
					openMenu = c.inMenuMoved(openMenu, me.getPoint());
					if (openMenu == 0) {
						actualColor = c.getColor();
						panel.removeMouseListener(tool);
						panel.removeMouseMotionListener(tool);
						tool = tools[c.getTool()];
						panel.addMouseListener(tool);
						panel.addMouseMotionListener(tool);
						if(mode_expert) {
							shapesCustom.remove(shapesCustom.size()-1);
						}
					} else if (mode_expert) {
						actualColor = Color.GRAY;
						panel.removeMouseListener(tool);
						panel.removeMouseMotionListener(tool);
						tool = tools[0];
						panel.addMouseListener(tool);
						panel.addMouseMotionListener(tool);
					}
				}
				repaint();
			}

			public void mouseDragged(MouseEvent me) {
				colorIndicator(new Point(me.getX(), me.getY()));
				if (!mode_pad) {
					openMenu = c.inMenuMoved(openMenu, me.getPoint());
					if (openMenu == 0) {
						actualColor = c.getColor();
						panel.removeMouseListener(tool);
						panel.removeMouseMotionListener(tool);
						tool = tools[c.getTool()];
						panel.addMouseListener(tool);
						panel.addMouseMotionListener(tool);
					}
				}
				repaint();
			}

		});

		pack();
		setVisible(true);

	}

	/*****************************************************************/

	/*
	 * Function to display a tooltip rectangle which indicates what color is
	 * currently selected
	 */

	private void colorIndicator(Point point) {
		Shape shape = null;
		ShapeCustom sc;

		/*
		 * only one occurrence of a tooltip remove the other ones
		 */
		for (int i = 0; i < shapesCustom.size(); i++) {
			if (shapesCustom.get(i).getSpec() == 1)
				shapesCustom.remove(shapesCustom.get(i));
		}

		if (tool == null) {
			// at the beginning, no tool is selected
			// then do nothing
		} else if (tool.getName() == "round rectangle") {
			RoundRectangle2D.Double tooltip = (RoundRectangle2D.Double) shape;
			tooltip = new RoundRectangle2D.Double(point.x + 10, point.y + 10, 10, 10, 5, 5);
			sc = new ShapeCustom((shape = tooltip), actualColor, 1);
			shapesCustom.add(sc);
		} else if (tool.getName() == "rectangle") {
			Rectangle2D.Double tooltip = (Rectangle2D.Double) shape;
			tooltip = new Rectangle2D.Double(point.x + 10, point.y + 10, 10, 10);
			sc = new ShapeCustom((shape = tooltip), actualColor, 1);
			shapesCustom.add(sc);
		} else if (tool.getName() == "pen") {
			Ellipse2D.Double tooltip = (Ellipse2D.Double) shape;
			tooltip = new Ellipse2D.Double(point.x + 10, point.y + 10, 3, 3);
			sc = new ShapeCustom((shape = tooltip), actualColor, 1);
			shapesCustom.add(sc);
		} else if (tool.getName() == "ellipse") {
			Ellipse2D.Double tooltip = (Ellipse2D.Double) shape;
			tooltip = new Ellipse2D.Double(point.x + 10, point.y + 10, 10, 10);
			sc = new ShapeCustom((shape = tooltip), actualColor, 1);
			shapesCustom.add(sc);
		} else if (tool.getName() == "line") {
			Line2D.Double tooltip = (Line2D.Double) shape;
			tooltip = new Line2D.Double(point.x + 15, point.y + 10, point.x + 25, point.y + 25);
			sc = new ShapeCustom((shape = tooltip), actualColor, 1);
			shapesCustom.add(sc);
		}

	}

	/*****************************************************************/

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

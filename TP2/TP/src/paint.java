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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.*;
import java.util.Vector;
import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.event.MouseInputListener;

/* paint *******************************************************************/

class Paint extends JFrame {

	private static final long serialVersionUID = 1L;

	Vector<ShapeCustom> shapesCustom = new Vector<ShapeCustom>();

	Color actualColor = Color.BLACK;

	boolean openMenu = false;
	boolean openMenuShapes = false;
	boolean openMenuColors = false;

	Point menuPosition;
	Point menuShapesPosition;
	Point menuColorsPosition;

	int menuRadius = 100;
	int textRadius = 75;

	String menuNames[] = { "colors", "shapes", "go back" };
	String menuShapesNames[] = { "line", "round rectangle", "ellipse", "rectangle", "pen", "go back" };
	String menuColorsNames[] = { "magenta", "yellow", "pink", "green", "red", "blue", "black", "go back" };

	Menu menu = new Menu(menuRadius, menuNames);
	Menu menuShapes = new Menu(menuRadius, menuShapesNames);
	Menu menuColors = new Menu(menuRadius, menuColorsNames);
	
	double textMenusAngle = Math.toRadians(360 / menu.getSize()) / 2;
	double textColorsAngle = Math.toRadians(360 / menuColors.getSize()) / 2;
	double textShapesAngle = Math.toRadians(360 / menuShapes.getSize()) / 2;
	
	Color colorBackground = new Color(238, 238, 238);
	Color colorBorder = new Color(214, 214, 214);
	Color colorText = new Color(86, 86, 86);
	Color colorHover = new Color(45, 125, 189);

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
			if (SwingUtilities.isLeftMouseButton(e)) {
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
		}
	}, new Tool("rectangle") {
		public void mouseDragged(MouseEvent e) {
			if (SwingUtilities.isLeftMouseButton(e)) {
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
		}
	}, new Tool("ellipse") {
		public void mouseDragged(MouseEvent e) {
			if (SwingUtilities.isLeftMouseButton(e)) {
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
		}
	}, new Tool("round rectangle") {
		public void mouseDragged(MouseEvent e) {
			if (SwingUtilities.isLeftMouseButton(e)) {
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
		}
	}, new Tool("line") {
		public void mouseDragged(MouseEvent e) {
			if (SwingUtilities.isLeftMouseButton(e)) {
				Line2D.Double line = (Line2D.Double) shape;
				if (line == null) {
					line = new Line2D.Double(o.getX(), o.getY(), 0, 0);
					ShapeCustom sc = new ShapeCustom((shape = line), actualColor);
					shapesCustom.add(sc);
				}
				line.setLine(o.getX(), o.getY(), abs(e.getX()), abs(e.getY()));
				panel.repaint();
			}
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

		/*
		 * view with painted components 
		 *     - shapes
		 *     - open the menu if a right click occurred
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
				}
				/*
				 * right click opens menus
				 */
				if (openMenu) {
					open(g2, menuPosition, menu, textMenusAngle);
				}
				if (openMenuShapes) {	
					open(g2, menuShapesPosition, menuShapes, textShapesAngle);
				}
				if (openMenuColors) {
					open(g2, menuColorsPosition, menuColors, textColorsAngle);
				}
			}
		});

		panel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent me) {
				/*
				 * opens the menu when right click in the window
				 * only if no menu is already opened
				 */
				if ((SwingUtilities.isRightMouseButton(me)) && (!openMenu) && (!openMenuShapes) && (!openMenuColors)) {
					menuPosition = new Point(me.getX(), me.getY());
					openMenu = true;
					repaint();
				}
			}

			/*
			 * public void mouseReleased(MouseEvent me) { if
			 * ((SwingUtilities.isRightMouseButton(me))) { openMenu = false; openMenuShapes
			 * = false; openMenuColors = false; repaint(); } }
			 */

		});

		/*
		 * menu behaviour
		 * when the mouse moves, changes the behavior of the menu
		 */
		panel.addMouseMotionListener(new MouseAdapter() {
			int lastAreaMenu = 0;
			int lastAreaShapes = 0;
			int lastAreaColors = 0;

			public void mouseMoved(MouseEvent me) {
				if ((openMenu) && (!openMenuShapes) && (!openMenuColors)) {
					int area = menu.getArea(menuPosition.x, menuPosition.y, me.getX(), me.getY());
					if (area != 0) {
						lastAreaMenu = area;
					} else {
						if (lastAreaMenu == 1) {
							openMenuShapes = true;
							menuShapesPosition = new Point(me.getX(), me.getY());
							openMenu = false;
						} else if (lastAreaMenu == 2) {
							openMenuColors = true;
							menuColorsPosition = new Point(me.getX(), me.getY());
							openMenu = false;
						} else {
							openMenu = false;
						}
					}
				} else if (openMenuShapes) {
					int area = menuShapes.getArea(menuShapesPosition.x, menuShapesPosition.y, me.getX(), me.getY());
					if (area != 0) {
						lastAreaShapes = area;
					} else {
						if (lastAreaShapes != 6) {
							panel.removeMouseListener(tool);
							panel.removeMouseMotionListener(tool);
							tool = tools[lastAreaShapes - 1];
							panel.addMouseListener(tool);
							panel.addMouseMotionListener(tool);
						} else {
							menuPosition = new Point(me.getX(), me.getY());
							openMenu = true;
						}
						openMenuShapes = false;
					}
				} else if (openMenuColors) {
					int area = menuColors.getArea(menuColorsPosition.x, menuColorsPosition.y, me.getX(), me.getY());
					if (area != 0) {
						lastAreaColors = area;
					} else {
						if (lastAreaColors == 1) {
							actualColor = Color.BLACK;
						} else if (lastAreaColors == 2) {
							actualColor = Color.BLUE;
						} else if (lastAreaColors == 3) {
							actualColor = Color.RED;
						} else if (lastAreaColors == 4) {
							actualColor = Color.GREEN;
						} else if (lastAreaColors == 5) {
							actualColor = Color.PINK;
						} else if (lastAreaColors == 6) {
							actualColor = Color.YELLOW;
						} else if (lastAreaColors == 7) {
							actualColor = Color.MAGENTA;
						} else {
							menuPosition = new Point(me.getX(), me.getY());
							openMenu = true;
						}
						openMenuColors = false;
					}
				}
				repaint();
			}
		});

		pack();
		setVisible(true);

	}

	/*
	 * function that handles the appearance of the menus
	 */
	public void open(Graphics g2, Point menuPosition, Menu menu, double text) {
		g2.setColor(colorBackground);
		g2.fillOval(menuPosition.x - menuRadius, menuPosition.y - menuRadius, menuRadius * 2,
				menuRadius * 2);
		g2.setColor(colorBorder);
		g2.drawOval(menuPosition.x - menuRadius, menuPosition.y - menuRadius, menuRadius * 2,
				menuRadius * 2);
		for (int i = 1; i <= menu.getSize(); i++) {
			double angle = Math.toRadians((360 / menu.getSize()) * i);
			double angleText = angle + text;
			int xi = (int) (menuRadius * Math.cos(angle) + menuPosition.x);
			int yi = (int) (menuRadius * Math.sin(angle) + menuPosition.y);
			int xt = (int) (textRadius * Math.cos(angleText) + menuPosition.x);
			int yt = (int) (textRadius * Math.sin(angleText) + menuPosition.y);
			g2.setColor(colorBorder);
			g2.drawLine(menuPosition.x, menuPosition.y, xi, yi);
			g2.setColor(colorText);
			g2.drawString(menu.objects[i - 1],
					xt - (menu.objects[i - 1].length() * g2.getFont().getSize()) / 4,
					yt + g2.getFont().getSize() / 4);
		}
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

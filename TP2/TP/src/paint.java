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

	boolean openMenu = false;
	boolean openMenuShapes = false;
	boolean openMenuColors = false;

	Point menuPosition;
	Point menuShapesPosition;
	Point menuColorsPosition;

	int menuRayon = 100;
	int textRayon = 75;
	// int lastArea = 0;

	int nbMenus = 3;
	int nbColors = 8;
	int nbShapes = 6;

	double textMenusAngle = Math.toRadians(360 / nbMenus) / 2;
	double textColorsAngle = Math.toRadians(360 / nbColors) / 2;
	double textShapesAngle = Math.toRadians(360 / nbShapes) / 2;

	String menuNames[] = { "colors", "shapes", "go back" };
	String menuShapesNames[] = { "line", "round rectangle", "ellipse", "rectangle", "pen", "go back" };
	String menuColorsNames[] = { "magenta", "yellow", "pink", "green", "red", "blue", "black", "go back" };

	Menu menu = new Menu(menuRayon, nbMenus, menuNames);
	Menu menuShapes = new Menu(menuRayon, nbShapes, menuShapesNames);
	Menu menuColors = new Menu(menuRayon, nbColors, menuColorsNames);

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
				if (openMenu) {
					g2.setColor(Color.GRAY);
					g2.fillOval(menuPosition.x - menuRayon, menuPosition.y - menuRayon, menuRayon * 2, menuRayon * 2);
					g2.setColor(Color.BLACK);
					g2.drawOval(menuPosition.x - menuRayon, menuPosition.y - menuRayon, menuRayon * 2, menuRayon * 2);
					for (int i = 1; i <= nbMenus; i++) {
						double angle = Math.toRadians((360 / nbMenus) * i);
						double angleText = angle + textMenusAngle;
						int xi = (int) (menuRayon * Math.cos(angle) + menuPosition.x);
						int yi = (int) (menuRayon * Math.sin(angle) + menuPosition.y);
						int xt = (int) (textRayon * Math.cos(angleText) + menuPosition.x);
						int yt = (int) (textRayon * Math.sin(angleText) + menuPosition.y);
						g2.drawLine(menuPosition.x, menuPosition.y, xi, yi);
						g2.drawString(menu.elemNames[i - 1],
								xt - (menu.elemNames[i - 1].length() * g2.getFont().getSize()) / 4,
								yt + g2.getFont().getSize() / 4);
					}
				}
				if (openMenuShapes) {
					g2.setColor(Color.GRAY);
					g2.fillOval(menuShapesPosition.x - menuRayon, menuShapesPosition.y - menuRayon, menuRayon * 2,
							menuRayon * 2);
					g2.setColor(Color.BLACK);
					g2.drawOval(menuShapesPosition.x - menuRayon, menuShapesPosition.y - menuRayon, menuRayon * 2,
							menuRayon * 2);
					for (int i = 1; i <= nbShapes; i++) {
						double angle = Math.toRadians((360 / nbShapes) * i);
						double angleText = angle + textShapesAngle;
						int xi = (int) (menuRayon * Math.cos(angle) + menuShapesPosition.x);
						int yi = (int) (menuRayon * Math.sin(angle) + menuShapesPosition.y);
						int xt = (int) (textRayon * Math.cos(angleText) + menuShapesPosition.x);
						int yt = (int) (textRayon * Math.sin(angleText) + menuShapesPosition.y);
						g2.drawLine(menuShapesPosition.x, menuShapesPosition.y, xi, yi);
						g2.drawString(menuShapes.elemNames[i - 1],
								xt - (menuShapes.elemNames[i - 1].length() * g2.getFont().getSize()) / 4,
								yt + g2.getFont().getSize() / 4);
					}
				}
				if (openMenuColors) {
					g2.setColor(Color.GRAY);
					g2.fillOval(menuColorsPosition.x - menuRayon, menuColorsPosition.y - menuRayon, menuRayon * 2,
							menuRayon * 2);
					g2.setColor(Color.BLACK);
					g2.drawOval(menuColorsPosition.x - menuRayon, menuColorsPosition.y - menuRayon, menuRayon * 2,
							menuRayon * 2);
					for (int i = 1; i <= nbColors; i++) {
						double angle = Math.toRadians((360 / nbColors) * i);
						double angleText = angle + textColorsAngle;
						int xi = (int) (menuRayon * Math.cos(angle) + menuColorsPosition.x);
						int yi = (int) (menuRayon * Math.sin(angle) + menuColorsPosition.y);
						int xt = (int) (textRayon * Math.cos(angleText) + menuColorsPosition.x);
						int yt = (int) (textRayon * Math.sin(angleText) + menuColorsPosition.y);
						g2.drawLine(menuColorsPosition.x, menuColorsPosition.y, xi, yi);
						g2.drawString(menuColors.elemNames[i - 1],
								xt - (menuColors.elemNames[i - 1].length() * g2.getFont().getSize()) / 4,
								yt + g2.getFont().getSize() / 4);
					}
				}
			}
		});

		panel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent me) {
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

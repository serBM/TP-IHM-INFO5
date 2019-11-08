import java.awt.Color;
import java.awt.Point;

public class Controller {
	
	Menu menu;
	Menu menuShapes;
	Menu menuColors;
	
	int lastAreaMenu = 0;
	int lastAreaShapes = 0;
	int lastAreaColors = 0;
	
	Color color;
	int tool;

	public Controller(Menu menu, Menu menuShapes, Menu menuColors) {
		this.menu = menu;
		this.menuShapes = menuShapes;
		this.menuColors = menuColors;

	}

	public int rightClick(int openMenu, Point p) {
		if (openMenu == 0) {
			menu.setPoint(p);
			openMenu = 1;
		}
		return openMenu;
	}

	public int inMenuMoved(int openMenu, Point p) {
		if (openMenu == 1) {
			int area = menu.getArea(menu.getPoint().x, menu.getPoint().y, p.x, p.y);
			if (area != 0) {
				lastAreaMenu = area;
			} else {
				if (lastAreaMenu == 1) {
					menuShapes.setPoint(p);
					openMenu = 2;
				} else if (lastAreaMenu == 2) {
					menuColors.setPoint(p);
					openMenu = 3;
				} else {
					openMenu = 0;
				}
			}
		} else if (openMenu == 2) {
			int area = menuShapes.getArea(menuShapes.getPoint().x, menuShapes.getPoint().y, p.x, p.y);
			if (area != 0) {
				lastAreaShapes = area;
			} else {
				if (lastAreaShapes != 6) {
					tool = lastAreaShapes-1;
					openMenu = 0;
				} else {
					menu.setPoint(p);
					openMenu = 1;
				}
			}
		} else if (openMenu == 3) {
			int area = menuColors.getArea(menuColors.getPoint().x, menuColors.getPoint().y, p.x, p.y);
			if (area != 0) {
				lastAreaColors = area;
			} else {
				if (lastAreaColors == 1) {
					color = Color.BLACK;
					openMenu = 0;
				} else if (lastAreaColors == 2) {
					color =  Color.BLUE;
					openMenu = 0;
				} else if (lastAreaColors == 3) {
					color = Color.RED;
					openMenu = 0;
				} else if (lastAreaColors == 4) {
					color = Color.GREEN;
					openMenu = 0;
				} else if (lastAreaColors == 5) {
					color = Color.PINK;
					openMenu = 0;
				} else if (lastAreaColors == 6) {
					color =  Color.YELLOW;
					openMenu = 0;
				} else if (lastAreaColors == 7) {
					color = Color.MAGENTA;
					openMenu = 0;
				} else {
					menu.setPoint(p);
					openMenu = 1;
				}
			}
		}
		return openMenu;
	}

	public Color getColor() {
		return color;
	}
	
	public int getTool() {
		return tool;
	}
}

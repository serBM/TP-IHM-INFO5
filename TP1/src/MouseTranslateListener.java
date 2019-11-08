
import java.awt.Component;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public final class MouseTranslateListener implements MouseListener, MouseMotionListener {

	private final Point pos;

	// private: use addToComponent(Component) instead
	private MouseTranslateListener() {
		pos = new Point();
	}

	public static void addToComponent(Component comp) {
		MouseTranslateListener listener = new MouseTranslateListener();
		comp.addMouseListener(listener);
		comp.addMouseMotionListener(listener);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		pos.setLocation(e.getX(), e.getY());
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		Component comp = e.getComponent();
		comp.setLocation(comp.getX() + e.getX() - pos.x, comp.getY() + e.getY() - pos.y);
	}

	public void mouseClicked(MouseEvent e) {
	}

	public void mouseReleased(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	public void mouseMoved(MouseEvent e) {
	}
}

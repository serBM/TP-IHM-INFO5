import java.awt.Color;
import java.awt.Shape;


public class ShapeCustom {

	Shape shape;
	Color color;
	
	public ShapeCustom(Shape shape, Color color) {
		this.shape = shape;
		this.color = color;
	}
	
	public ShapeCustom() {
		this.shape = null;
		this.color = null;
	}
	
	public Shape getShape() {
		return this.shape;
	}
	
	public Color getColor() {
		return this.color;
	}
	
	public void setShape(Shape shape) {
		this.shape = shape;
	}
	
	public void setColor(Color color) {
		this.color = color;
	}
}

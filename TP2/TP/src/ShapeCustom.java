import java.awt.Color;
import java.awt.Shape;

/*
 * This class is creating an object 
 * which associates a shape and a color together
 */
public class ShapeCustom {

	Shape shape;
	Color color;
	
	/*
	 * this attributes allows to specified some specific attributes
	 * takes 0 for default value: no special attribute
	 * takes 1 for special value: color rectangle indicator
	 */
	int specifity; 
	
	public ShapeCustom(Shape shape, Color color, int specifity) {
		this.shape = shape;
		this.color = color;
		this.specifity = specifity;
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

	public int getSpec() {
		return this.specifity;
	}
}

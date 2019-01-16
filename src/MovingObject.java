import java.awt.Graphics2D;

/**
 * Modified Interface that serves as the base for the Composite Pattern and blueprint for any
 * moving object, be it a single unicycle, a group of unicycles, or even a group of groups of unicycles, etc.
 * 
 * @author Luiz do Valle
 *
 */
public interface MovingObject {

	/**
	 * Method that draws the MovingObject on the GUI. Based on the example on Courseworks
	 * @param graphics2d the Graphics2D reference used to draw the objects on the GUI
	 */
	public abstract void draw(Graphics2D graphics2d);
	
	/**
	 * Method that changes the X coordinates of the MovingObject by the specified amount so it can
	 * move left and right. No Y coordinate change needed, as the objects move only horizontally
	 * @param deltaX the amount by which the X coordinates should be changed by
	 */
	public abstract void translate(int deltaX);
	
	/**
	 * Method that returns the X coordinate of the right end of the MovingObject so that the system knows
	 * when the MovingObject has hit the "wall" of the GUI
	 * @return the X coordinate of the rightmost end of the MovingObject
	 */
	public abstract int getRightEndXPosition();
	
	/**
	 * Method that returns the X coordinate of the left end of the MovingObject so that the system knows
	 * when the MovingObject has hit the "wall" of the GUI
	 * @return the X coordinate of the leftmost end of the MovingObject
	 */
	public abstract int getLeftEndXPosition();
	
}

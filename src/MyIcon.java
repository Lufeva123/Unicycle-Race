import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.Icon;

/**
 * Class that implements the Icon Interface.
 * Serves as a container to display the MovingObjects on the screen
 * Based on the Courseworks example
 * 
 * @author Luiz do Valle
 *
 */
public class MyIcon implements Icon{

	/**
	 * The width of the Icon
	 */
	private int width;
	
	/**
	 * The height of the Icon
	 */
	private int height;
	
	/**
	 * The MovingObject to be displayed
	 */
	private MovingObject movingObject;
	
	/**
	 * The constructor for the class that simply initializes the class's fields
	 * @param width the width of the Icon
	 * @param height the height of the Icon
	 * @param movingObject the MovingObjects to be displayed
	 */
	public MyIcon(int width, int height, MovingObject movingObject) {
		
		this.width = width;
		this.height = height;
		this.movingObject = movingObject;
	}
	
	@Override
	public int getIconHeight() {
		
		return height;
	}

	@Override
	public int getIconWidth() {
		
		return width;
	}

	@Override
	public void paintIcon(Component component, Graphics graphics, int x, int y) {
		
		Graphics2D graphics2d = (Graphics2D) graphics;
	
		movingObject.draw(graphics2d);
	}

	
}

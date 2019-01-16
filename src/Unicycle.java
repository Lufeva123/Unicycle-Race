import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.Random;

/**
 * Class that implements the MovingObject interface and represents a single Unicycle
 * 
 * @author Luiz do Valle
 *
 */
public class Unicycle implements MovingObject {

	/**
	 * X coordinate of the center of the wheel
	 */
	private int x;
	
	/**
	 * Y coordinate of the bottom of the wheel (6 o'clock position)
	 * This choice of y makes it easier to place the unicycle on the "ground" (bottom of the frame)
	 */
	private int y;
	
	/**
	 * The unit in terms of which all dimensions of the unicycle component will be defined
	 * In this case, it is used as the seat length
	 */
	private int unit;
	
	/**
	 * The rectangle representing the unicycle's seat
	 */
	private Rectangle2D seat;
	
	/**
	 * The line representing the unicycle's frame
	 */
	private Line2D frame;
	
	/**
	 * The ellipse (circle) representing the unicycle's wheel
	 */
	private Ellipse2D wheel;
	
	/**
	 * The ellipse (circle) representing the unicycle's axle
	 */
	private Ellipse2D axle;
	
	/**
	 * The color of the unicycle
	 * Makes it easy to distinguish between different unicycles on top
	 * of each other and makes them look aesthetically pleasing
	 */
	private Color color;
	
	/**
	 * The X coordinate of the rightmost point in the unicycle.
	 * In this case, the X coordinate of the 3 o'clock position of the wheel
	 */
	private int rightEndXPosition;
	
	/**
	 * The X coordinate of the leftmost point in the unicycle.
	 * In this case, the X coordinate of the 9 o'clock postion of the wheel 
	 */
	private int leftEndXPosition;
	
	/**
	 * Constructor for the class that initializes class fields
	 * Generates a random color for the unicycle to add variety
	 * @param x the X coordinate of the center of the wheel
	 * @param y the Y coordinate of the bottom (6 o'clock) position of the wheel 
	 * @param seatLength the length of the seat. Used to determine the proportions of the unicycle
	 */
	public Unicycle(int x, int y, int seatLength) {
		
		this.x = x;
		this.y = y;
		this.unit = seatLength;
		this.color = generateRandomColor();
		
		createUnicycle();
		
	}
	
	/**
	 * Constructor for the class that initializes class fields
	 * @param x the X coordinate of the center of the wheel
	 * @param y the Y coordinate of the bottom (6 o'clock) position of the wheel 
	 * @param seatLength the length of the seat. Used to determine the proportions of the unicycle
	 * @param color Color reference representing the color of the unicycle
	 */
	public Unicycle(int x, int y, int seatLength, Color color) {
		
		this.x = x;
		this.y = y;
		this.unit = seatLength;
		this.color = color;
		
		createUnicycle();
		
	}
	
	@Override
	public void draw(Graphics2D graphics2d) {
		
		GeneralPath unicycle = new GeneralPath();
		
		createUnicycle();
		
		unicycle.append(seat, false);
		unicycle.append(frame, false);
		unicycle.append(wheel, false);
		unicycle.append(axle, false);
		
		graphics2d.fill(seat);
		graphics2d.setColor(color);
		graphics2d.draw(unicycle);
		
	}

	@Override
	public void translate(int deltaX) {
		
		x += deltaX;
	}

	@Override
	public int getRightEndXPosition() {
		 
		return rightEndXPosition;
	}

	@Override
	public int getLeftEndXPosition() {
		
		return leftEndXPosition;
	}

	/**
	 * Helper method that creates the shapes that compose the unicycle
	 * All lengths and positions defined in terms of units so that unicycles of different sizes can be 
	 * created easily
	 */
	private void createUnicycle() {
		
		//Wheel
		int diameterWheel = unit * 11/10;
		int framingRectangleTopX = x - diameterWheel/2;
		int framingRectangleTopY = y - diameterWheel;
		wheel = new Ellipse2D.Double(framingRectangleTopX, framingRectangleTopY, diameterWheel, diameterWheel);
		
		//Ends
		rightEndXPosition = framingRectangleTopX + diameterWheel;
		leftEndXPosition = framingRectangleTopX;
				
		//Axle
		int diameterAxle = diameterWheel/4;
		framingRectangleTopX = x - diameterAxle/2;
		framingRectangleTopY = y - diameterAxle/2 - diameterWheel/2;
		axle = new Ellipse2D.Double(framingRectangleTopX, framingRectangleTopY, diameterAxle, diameterAxle);
				
		//Frame
		int frameTopX = x;
		int frameTopY = y - diameterWheel/2 - 3/2 * (unit) ;
		int frameBottomX = x;
		int frameBottomY = y - diameterWheel/2;
		frame = new Line2D.Double(frameTopX, frameTopY, frameBottomX, frameBottomY);
				
		//Seat
		int seatTopLeftX = x - unit/2;
		int seatTopLeftY = frameTopY - unit/3;
		int seatLength = unit;
		int seatWidth = unit/3;
		seat = new Rectangle2D.Double(seatTopLeftX, seatTopLeftY, seatLength, seatWidth);
		
	}
	
	/**
	 * Helper method that generates random color
	 * @return Color reference representing the unicycle's
	 */
	private Color generateRandomColor() {
		
		Random random = new Random();
		
		float red = random.nextFloat();
		float green = random.nextFloat();
		float blue = random.nextFloat();
		
		return new Color(red, green, blue);
	}
	
}

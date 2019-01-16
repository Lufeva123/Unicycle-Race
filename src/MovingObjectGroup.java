import java.awt.Graphics2D;
import java.util.LinkedList;

/**
 * Class that represents an aggregate of MovingObjects and is a MovingObject itself (like a train is a moving
 * object but is composed of smaller moving objects, namely carts. Or how a peloton of racers can be considered 
 * a moving object itself)
 * 
 * @author Luiz do Valle
 *
 */
public class MovingObjectGroup implements MovingObject {
	
	/**
	 * LinkedList of MovingObjects compatible with the Composite Pattern
	 * LinkedList chosen because size of the group is unknown, not much adding needs to be done, and 
	 * so that elements can be accessed efficiently
	 */
	private LinkedList<MovingObject> group = new LinkedList<>();
	
	/**
	 * Variable that controls how much the speed of this group is faster than the 
	 * one passed to the translate(int deltaX) method
	 */
	private double speedMultiplier;
	
	/**
	 * Default constructor that initializes the speedMultiplier to 1
	 */
	public MovingObjectGroup() {

		this(1);
	}
	
	/**
	 * Constructor that allows the user to specify speedMultiplier
	 * @param speedMultiplier
	 */
	public MovingObjectGroup(double speedMultiplier) {
		
		this.speedMultiplier = speedMultiplier;
	}
	
	@Override
	public void draw(Graphics2D graphics2d) {
		
		for(MovingObject groupElement : group) {
			
			groupElement.draw(graphics2d);
		}
		
	}

	@Override
	public void translate(int deltaX) {
		
		for(MovingObject groupElement : group) {
			
			int scaledDelatX = (int) (deltaX * speedMultiplier);
			groupElement.translate(scaledDelatX);
		}
	}

	@Override
	public int getRightEndXPosition() {
		
		int rightEndXPosition = Integer.MIN_VALUE;
		
		for(MovingObject groupElement : group) {
			
			if(groupElement.getRightEndXPosition() > rightEndXPosition) {
				
				rightEndXPosition = groupElement.getRightEndXPosition();
			}
		}
		
		return rightEndXPosition;
		
	}

	@Override
	public int getLeftEndXPosition() {
		
		int leftEndXPosition = Integer.MAX_VALUE;
		
		for(MovingObject groupElement : group) {
			
			if(groupElement.getLeftEndXPosition() < leftEndXPosition) {
				
				leftEndXPosition = groupElement.getLeftEndXPosition();
				
			}
		}
		return leftEndXPosition;
	}
	
	/**
	 * Method that adds a MovingObject to the LinkedList of MovingObjects
	 * @param groupElement element to be added to the group
	 */
	public void addMovingObject(MovingObject groupElement) {
		
		group.add(groupElement);
	}
	
	/**
	 * Method that returns the MovingObject at the specified position in the group
	 * @param index position of the MovingObject in the group
	 * @return the MovingObject at that position
	 */
	public MovingObject getGroupElement(int index) {
		
		return group.get(index);
	}
}

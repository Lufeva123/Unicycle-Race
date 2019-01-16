/**
 * Class that implements the Driver Interface.
 * This implementation causes the MovingObject to stop once it reaches the GUI's boundaries,
 * symbolizing the beginning and end of the race
 * 
 * @author Luiz do Valle
 *
 */
public class StopDriver implements Driver{

	/**
	 * The length of the GUI
	 */
	private int guiLength;
	
	/**
	 * The MovingObject to be driven
	 */
	private MovingObject movingObject;
	
	/**
	 * The current speed of the MovingObject
	 */
	private int currentSpeed;
	
	/**
	 * Constructor that initializes the class's fields
	 * @param movingObject the MovingObject to be driven
	 * @param initialSpeed the initial speed of the Unicycle
	 * @param guiLength the length of the GUI
	 */
	public StopDriver(MovingObject movingObject, int initialSpeed, int guiLength) {
		
		this.movingObject = movingObject;
		this.guiLength = guiLength;
		this.currentSpeed = initialSpeed;
		
	}

	
	/**
	 * Constructor that initializes the class's fields, setting the initial speed to default (0)
	 * @param movingObject the MovingObject to be driven
	 * @param guiLength the length of the GUI
	 */
	public StopDriver(MovingObject movingObject, int guiLength) {

		this(movingObject, 0, guiLength);
		
	}

	@Override
	public void drive() {
		
		//If the unicycle group is moving to the right (speed > 0) and the group reaches the rightmost boundary, stop moving
		//Same for the case the group is moving to the left (speed < 0) and the leftmost boundary is reached
		if(movingObject.getRightEndXPosition() >= guiLength && currentSpeed > 0) {
					
			currentSpeed = 0;
				
		} else if(movingObject.getLeftEndXPosition() <= 0 && currentSpeed < 0) {
					
			currentSpeed = 0;
		}
				
		movingObject.translate(currentSpeed);
		
	}
	
	@Override
	public void drive(int speed) {
		
		currentSpeed = speed;
		drive();
	}
}

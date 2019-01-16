/**
 * Class that implements the Driver Interface.
 * This implementation causes the MovingObject to reverse directions once it reaches the GUI's boundaries
 * (Moves left and right)
 * @author Luiz do Valle
 *
 */
public class LeftAndRightDriver implements Driver {

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
	 * Constructor that initializes the class's fields, setting the initial speed to default (0)
	 * @param movingObject the MovingObject to be driven
	 * @param guiLength the length of the GUI
	 */
	public LeftAndRightDriver(MovingObject movingObject, int guiLength) {
		
		this(movingObject, 0, guiLength);
	}
	
	/**
	 * Constructor that initializes the class's fields
	 * @param movingObject the MovingObject to be driven
	 * @param initialSpeed the initial speed of the Unicycle
	 * @param guiLength the length of the GUI
	 */
	public LeftAndRightDriver(MovingObject movingObject, int initialSpeed, int guiLength) {
		
		this.movingObject = movingObject;
		this.guiLength = guiLength;
		this.currentSpeed = initialSpeed;
		
	}
	
	@Override
	public void drive() {
		
		/*If rightmost or leftmost position of unicycle is at the GUI's rightmost or leftmost boundary respectively, 
		flip directions*/
		if(movingObject.getRightEndXPosition() >= guiLength && currentSpeed > 0) {
			
			currentSpeed = -currentSpeed;
		
		} else if (movingObject.getLeftEndXPosition() <= 0 && currentSpeed < 0) {
			
			currentSpeed = -currentSpeed;
			
		}
		
		movingObject.translate(currentSpeed);
	}
	
	@Override
	public void drive(int speed) {
		
		currentSpeed = speed;
		
		drive();
		
	}

	
}

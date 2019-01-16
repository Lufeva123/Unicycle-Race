/**
 * Interface that serves as the blueprint for the classes that determine how a MovingObject moves in the GUI
 * @author Luiz do Valle
 *
 */
public interface Driver {

	/**
	 * Method that moves the MovingObject at a constant speed
	 */
	public abstract void drive();
	
	/**
	 * Method that moves the MovingObject at the specified speed (allows the speed to be updated)
	 * @param speed the new speed of the MovingObject
	 */
	public abstract void drive(int speed);
	
}

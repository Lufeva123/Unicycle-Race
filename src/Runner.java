/**
 * Class that executes the program
 * 
 * 
 * NOTE: The unicycles of a group stop moving in a given direction whenever the unicycle in the front
 * or back hits a boundary, symbolizing the "beginning" or "end" of the race. See recordings for 
 * illustration of how this works
 * 
 * TESTING
 * 
 * 
 * Part 2.1
 * 
 * Test Case ID: Single Unicycle with constant speed
 * Test Designed By: Luiz do Valle
 * Test Summary: This tests whether a single unicycle is drawn correctly and moves correctly
 * from left to right and vice versa in the frame
 * Pre-conditions: None
 * Test Data: A single unicycle
 * Expected Result: The unicycle will move from right to left and vice versa at a constant speed
 * and change directions once it hits the frame's boundaries
 * Actual Result: The results matched expectations
 * Post-condition: None
 * 
 * Part 2.2
 * 
 * Test Case ID: Group of Unicycles with slider speed
 * Test Designed By: Luiz do Valle
 * Test Summary: This tests whether a group of unicycles is drawn correctly and moves correctly
 * from left to right and vice versa in the frame according to the speed specified by the 
 * slider
 * Pre-conditions: A single unicycle is drawn and moves correctly
 * Test Data: A group of unicycles (peloton)
 * Expected Result: The unicycle will move from left to right and vice versa at a constant speed.
 * The group will stop moving once the unicycle in the far right or left touches the right or left barrier,
 * respectively.
 * Actual Result: The results matched expectations
 * Post-condition: None
 * 
 * Part 2.3
 * 
 * Test Case ID: Groups of Unicycles with different speeds
 * Test Designed By: Luiz do Valle
 * Test Summary: This tests whether a group of groups of unicycles is drawn correctly and moves correctly
 * from left to right and vice versa in the frame according to the speed specified by the slider
 * Pre-conditions: A single group of unicycles is drawn and moves correctly
 * Test Data: A group of unicycles composed of other groups of unicycles works properly
 * Expected Result: The different subgroups of unicycles will move with different speeds, but stop once the
 * unicycle in the far right or far left reaches the right or left boundary
 * Actual Result: The results matched expectations
 * Post-condition: None
 * 
 * @author Luiz do Valle
 *
 */
public class Runner {

	/**
	 * Main method for the system.
	 * Beginning of program execution
	 * @param args
	 */
	public static void main(String[] args) {
		
		Tester tester = new Tester();
		
		tester.testCreativity();
	}
}

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Class that contains methods to test the different parts of the system
 * 
 * @author Luiz do Valle
 *
 */
public class Tester {

	/**
	 * The unit used to draw the Unicycles
	 */
	private static final int UNIT = 50;
	
	/**
	 * The width of the Icon used to display the images
	 */
	private static final int ICON_W = 1500;
	
	/**
	 * The height of the Icon used to display the images
	 */
	private static final int ICON_H = 250;
	
	/**
	 * Number of unicycles created for the second and third tests
	 */
	private static final int NUM_UNICYCLES = 7;
	
	/**
	 * The speed of the MovingObjects.
	 * Although this value is constant for the first test, speed is changed
	 * in later tests based on the JSlider. For this reason, speed is a variable
	 * and not a constant
	 */
	private static int speed = 2;
	
	/**
	 * Method that tests whether the unicycle is drawn and moves correctly on the GUI 
	 */
	public void testConstantSpeed() {
		
		//Set GUI's title
		JFrame frame = new JFrame("The Unicycle Race");
		
		//Unicycle and icon
		final MovingObject unicycle = new Unicycle(100, 250, UNIT);
		final MyIcon icon = new MyIcon(ICON_W, ICON_H, unicycle);
		
		//Driver
		final Driver leftAndRightDriver = new LeftAndRightDriver(unicycle, speed, ICON_W);
		
		final JLabel label = new JLabel(icon);
		
		//Add label containing the unicycle's icon to the frame
		frame.add(label);
		
		//Layout is the default FlowLayout
		frame.setLayout(new FlowLayout());
		
		
		/*Create Timer that updates GUI and changes the unicycle's X position 60 times per second (60 Hz) 
		 * to create the impression of fluid movement*/
		final int DELAY = 50/3;
		Timer timer = new Timer(DELAY, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				leftAndRightDriver.drive();
				
				label.repaint();
			}
		});
		
		timer.start();
		
		//Make the GUI visible, closeable, and have a not resizeable
		frame.setDefaultCloseOperation(JFrame .EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
		frame.setResizable(false);
	}
	
	/**
	 * Method that tests whether the group of unicycles is drawn correctly and moves correctly
	 * at the speed defined by the JSlider on the GUI
	 * Once the unicycle in the group that is most to the right reaches the right boundary of the GUI, the
	 * whole group stops moving to symbolize the end of the race. Same happens for the leftmost unicycle
	 */
	public void testSliderGroup() {
		
		//Set GUI's title
		JFrame frame = new JFrame("The Unicycle Race");
		
		//Unicycle group
		MovingObjectGroup unicycleGroup = new MovingObjectGroup();
		
		/*Generate NUM_UNICYCLES random unicycles and add them to the group
		 *X and Y coordinates, and the scaling of each unicycle are randomized
		 *with respect to UNIT*/
		for(int i = 0; i < NUM_UNICYCLES; i++) {
			
			int x = (int) (Math.random() * UNIT * 5 + 20);
			int y =  250 - (int)(Math.random() * UNIT);
			double sizeScalar = Math.random() * 1.5 + 0.5;
			int newSize = (int) (sizeScalar * UNIT);
			
			MovingObject unicycle = new Unicycle(x, y, newSize);
			
			unicycleGroup.addMovingObject(unicycle);
		}
		
		//Driver
		Driver stopDriver = new StopDriver(unicycleGroup, ICON_W);
		
		//Create icon for the group and add it to the label
		MyIcon icon = new MyIcon(ICON_W, ICON_H, unicycleGroup);
		JLabel label = new JLabel(icon);
		
		//Create JSlider that goes from -10 to 10 and controls the group's speed
		JSlider speedSlider = new JSlider(-10, 10, 0);
		
		//Create markings and labels for each integer value of the slider to make it easy to set each speed
		speedSlider.setMajorTickSpacing(1);
		speedSlider.setPaintTicks(true);
		speedSlider.setPaintLabels(true);
		
		//Add slider and label with unicycle to the Frame with a spacing in between
		frame.add(label);
		frame.add(Box.createVerticalStrut(20));
		frame.add(speedSlider);
		
		//Layout is the BoxLayout organized vertically so that the slider is below the unicycles
		frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
		
		//Get the initial speed of the slider
		speed = speedSlider.getValue();
		
		//Add action listener to slider to update speed whenever the position of the slider is changed
		speedSlider.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				
				speed = speedSlider.getValue();
				
			}
		});
		
		/*Create Timer that updates GUI and changes the unicycle's X position 60 times per second (60 Hz) 
		 * to create the impression of fluid movement*/
		final int DELAY = 50/3;
		Timer timer = new Timer(DELAY, (e) -> { 
		
			stopDriver.drive(speed);
		
			label.repaint();
		
		});
		
		timer.start();
		
		//Make the GUI visible, closeable, and have a not resizeable
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
		frame.setResizable(false);
	}
	
	/**
	 * Method that tests whether the Composite Pattern works correctly and allows different groups
	 * to have different speeds in the race
	 * Once the unicycle in the group that is most to the right reaches the right boundary of the GUI, the
	 * whole group stops moving to symbolize the end of the race. Same happens for the leftmost unicycle
	 */
	public void testCreativity() {
		
		//Set GUI's title
		JFrame frame = new JFrame("The Unicycle Race");
		
		//Create broader group that will encompass other groups
		MovingObjectGroup unicycleGroup = new MovingObjectGroup();
		
		//Driver
		Driver stopDriver = new  StopDriver(unicycleGroup, ICON_W);
		
		//Create first subgroup (peloton) of unicycles
		MovingObjectGroup unicycleGroup1 = new MovingObjectGroup(1.2);
		
		//Fill first peloton with NUM_UNICYCLES random unicycles
		for(int i = 0; i < NUM_UNICYCLES; i++) {
			
			int x = (int) (Math.random() * UNIT * 5 + 20);
			int y =  250 - (int)(Math.random() * UNIT);
			double sizeScalar = Math.random() * 1.5 + 0.5;
			int newSize = (int) (sizeScalar * UNIT);
			
			MovingObject unicycle = new Unicycle(x, y, newSize);
			
			unicycleGroup1.addMovingObject(unicycle);
		}
		
		
		MovingObjectGroup unicycleGroup2 = new MovingObjectGroup(2);
		
		//Fill second peloton with NUM_UNICYCLES random unicycles
		for(int i = 0; i < NUM_UNICYCLES; i++) {
			
			int x = (int) (Math.random() * UNIT * 5 + 20);
			int y =  250 - (int)(Math.random() * UNIT);
			double sizeScalar = Math.random() * 1.5 + 0.5;
			int newSize = (int) (sizeScalar * UNIT);
			
			MovingObject unicycle = new Unicycle(x, y, newSize);
			
			unicycleGroup2.addMovingObject(unicycle);
		}
		
		//Create third peloton of unicycles
		MovingObjectGroup unicycleGroup3 = new MovingObjectGroup(1.5);
		
		//Fill second peloton with NUM_UNICYCLES random unicycles
		for(int i = 0; i < NUM_UNICYCLES; i++) {
			
			int x = (int) (Math.random() * UNIT * 5 + 20);
			int y =  250 - (int)(Math.random() * UNIT);
			double sizeScalar = Math.random() * 1.5 + 0.5;
			int newSize = (int) (sizeScalar * UNIT);
			
			MovingObject unicycle = new Unicycle(x, y, newSize);
			
			unicycleGroup3.addMovingObject(unicycle);
		}
		
		//Add the third peloton to the second
		unicycleGroup2.addMovingObject(unicycleGroup3);
		
		//Create single unicycle
		MovingObject unicycle = new Unicycle(300, 250, UNIT);
		
		//Add the two pelotons and single unicycle to larger group
		unicycleGroup.addMovingObject(unicycleGroup1);
		unicycleGroup.addMovingObject(unicycleGroup2);
		unicycleGroup.addMovingObject(unicycle);
		
		//Create Icon and label
		MyIcon icon = new MyIcon(ICON_W, ICON_H, unicycleGroup);
		JLabel label = new JLabel(icon);
		
		//Create JSlider with values from -10 to 10 and add markings to it
		JSlider speedSlider = new JSlider(-10, 10, 0);
		speedSlider.setMajorTickSpacing(1);
		speedSlider.setPaintTicks(true);
		speedSlider.setPaintLabels(true);
		
		//Add label and JSlider to frame with separation in between 
		frame.add(label);
		frame.add(Box.createVerticalStrut(20));
		frame.add(speedSlider);
		
		//Layout is the BoxLayout organized vertically so that the slider is below the unicycles
		frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
		
		//Get the initial speed of the slider
		speed = speedSlider.getValue();
		
		//Add action listener to slider to update speed whenever the position of the slider is changed
		speedSlider.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				
				speed = speedSlider.getValue();
				
			}
		});
		
		/*Create Timer that updates GUI and changes the unicycle's X position 60 times per second (60 Hz) 
		 * to create the impression of fluid movement*/
		final int DELAY = 50/3;
		Timer timer = new Timer(DELAY, (e) -> { 
		
			stopDriver.drive(speed);
		
			label.repaint();
		
		});
		
		timer.start();
		
		//Make the GUI visible, closeable, and have a not resizeable
		frame.setDefaultCloseOperation(JFrame .EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
		frame.setResizable(false);
	}
}

import java.awt.Color;

/**
* CS312 Assignment 11.
*
* On MY honor, <NAME>, this programming assignment is MY own work
* and I have not provided this code to any other student.
*
* Student name: 
* UTEID:
* email address:
* Grader name:
* Number of slip days used on this assignment:
* 
*/

public class Vulture extends Bird{
	private boolean hungry = true;
	private boolean fight = false;
	public Vulture() {
		super();
		}

	/*
	 * This method is used to signify if the critter should eat or not
	 * 
	 * This version is determined by if hungry is true and if it recently got into a
	 * fight
	 * 
	 * @returns True only if hunger is true, False any other time
	 */
	public boolean eat() {
		if(hungry) {
			hungry = false;
			return true;
		} 
		if(!hungry && fight) {
			fight = false;
			hungry = true;
		}
		return false;
	}
	/*
	 * This returns the color that is color of the critter
	 * 
	 * Vulture is always black
	 * 
	 * @return Color.BLACK
	 */
	public Color getColor() {
		return Color.BLACK;
	}

	

}

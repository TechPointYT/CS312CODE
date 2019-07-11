// CSE 142 Critters - Modified for CS312
// Authors: Marty Stepp and Stuart Reges
// Minor changes by Mike Scott
//
// Stone objects are displayed as S and always stay put.
// They always pick ROAR in a fight.
//
import java.awt.Color;

/**
* CS312 Assignment 11.
*
* On MY honor, David Alvarez, this programming assignment is MY own work
* and I have not provided this code to any other student.
*
* Student name: David Alvarez
* UTEID: da28443
* email address: david.alvarez@utexas.edu
* Grader name: divya Manohar
* Number of slip days used on this assignment: 1
* 
*/

public class Stone extends Critter {
	// method comment goes here
	/*
	 * This method is used to determine What attack move is used when put into a
	 * fight
	 * 
	 * @returns Attack.ROAR
	 */
	public Attack fight(String opponent) {
	    // Good old rock, I mean roar. Nothing beats that.
		return Attack.ROAR;
	}
	/*
	 * This returns the color that is color of the critter
	 * 
	 * 
	 * @return Color.GRAY 
	 */
	public Color getColor() {
		return Color.GRAY;
	}
	
	/*
	 * this method is used to get the string representation of the critter
	 * 
	 * @return "S"
	 */
	public String toString() {
		return "S";
	}
}

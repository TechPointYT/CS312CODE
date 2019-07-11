import java.awt.Color;
import java.util.Random;

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


public class Longhorn extends Critter {
	final static Color[] VALIDCOLORS = {Color.black,Color.cyan,Color.green,Color.magenta,Color.orange,Color.RED,Color.blue, Color.yellow}; 
	final static Direction[] VALIDDIRECTS = {Direction.EAST,Direction.CENTER,Direction.NORTH,Direction.SOUTH,Direction.WEST}; 
	int colorIndex;
	int directIndex;
	Random random = new Random();
	public Longhorn() {
		super();
		
	}
	/*
	 * This method is used to signify if the critter should eat or not
	 * 
	 * This version is always true LONGHORNS ALWAYS HUNGRY FOR KNOWLEDGE
	 * 
	 * @returns true
	 * 
	 */
	public boolean eat() {
		return true;
	}
	/*
	 * This method is used to determine What attack move is used when put into a
	 * fight
	 * 
	 * If ANT ROAR, if STONE POUNCE,else SCRATCH
	 * 
	 * @returns Attack.ROAR if ANT, Attack.POUNCE if STONE, else Attack.SCRATCH
	 * 
	 */
	public Attack fight(String opponent) {
		if(opponent == "%") {
			return Attack.ROAR;
		}else if(opponent.equals("S") ){
			return Attack.POUNCE;
		}
			return Attack.SCRATCH;
	}
	/*
	 * This returns the color that is color of the critter
	 * 
	 * Randomized color based on array of valid color 
	 * 
	 * @return VALIDCOLORS[randomINDEX]
	 */
	public Color getColor() {
		colorIndex = random.nextInt(VALIDCOLORS.length);
		return VALIDCOLORS[colorIndex];
	}
	public Direction getMove() {
		directIndex = random.nextInt(VALIDDIRECTS.length);
		return VALIDDIRECTS[directIndex];
	}
	/*
	 * this method is used to get the string representation of the critter
	 * 
	 * @return "L"
	 */
	public String toString() {
		return "L";
	}
}

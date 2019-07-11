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

public class Ant extends Critter {
	boolean walkSouth ;
	Direction lastMove = Direction.CENTER;
	int index = 0;
	public Ant(boolean walkSouth) {
		super();
		this.walkSouth = walkSouth;
	}
	
	/*
	 * This method is used to signify if the critter should eat or not
	 * 
	 * @returns true in this version
	 */
	public boolean eat() {
		return true;
	}

	/*
	 * This method is used to determine What attack move is used when put into a
	 * fight
	 * 
	 * ANTS ALWAYS SCRATCH
	 * 
	 * @returns Attack.Scratch
	 */
	public Attack fight(String opponent) {
		return Attack.SCRATCH;
	}
	/*
	 * This returns the color that is color of the critter
	 * 
	 * ANTS ARE RED
	 * 
	 * @return Color.RED 
	 */
	public Color getColor() {
		return Color.RED;
	}
	/*
	 * This method is used to determine the direction the critter heads
	 * 
	 * Direction is based of walkSouth True or false 
	 *
	 * @return validDirectionTrue[index] if walkSouth true, else validDirectionFalse[index]
	 */
	public Direction getMove() {
		Random direction = new Random();
		Direction[] validDirectionTrue = {Direction.SOUTH, Direction.EAST}; 
		Direction[] validDirectionFalse = {Direction.NORTH, Direction.EAST}; 
		
		if(this.walkSouth) {	
			lastMove = validDirectionTrue[index];
			if(index == 0) {
				index++;
			}else if(index == 1){
				index--;
			}
		}else {
			lastMove = validDirectionFalse[index];
			if(index == 0) {
				index++;
			}else if(index == 1){
				index--;
			}
			
		}
		
		
		return lastMove;
	}
	/*
	 * this method is used to get the string representation of the critter
	 * 
	 * @return "%"
	 */
	public String toString() {
		return "%";
	}

}

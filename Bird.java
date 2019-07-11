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

public class Bird extends Critter{
	int[] counterOfDir = new int[4];
	private Direction lastMove = Direction.CENTER;
	final static int MAXMOVES = 4;
	final static int NOMOVES = 0;
	public Bird() {
		super();
		
	}
	/*
	 * This method is used to signify if the critter should eat or not
	 * 
	 * @returns false
	 * 
	 */
	public boolean eat() {
		return false;
	}
	/*
	 * This method is used to determine What attack move is used when put into a
	 * fight
	 * 
	 * If opponent is ant ROAR else POUNCE
	 * 
	 * @returns Attack.ROAR if opponent is ANT else Attack.POUNCE
	 */
	public Attack fight(String opponent) {
		if(opponent.equals("%")) {
			return Attack.ROAR;
		}
			return Attack.POUNCE;
	}
	/*
	 * This returns the color that is color of the critter
	 * 
	 * ALWAYS BLUE
	 * 
	 * @return Color.BLUE
	 */
	public Color getColor() {
		return Color.BLUE;
	}

	public Direction getMove() {	
	//[0] NORTH [1] EAST [2] SOUTH [3] WEST 
		if(this.counterOfDir[0] <= MAXMOVES && this.counterOfDir[1] == NOMOVES && this.counterOfDir[2] == NOMOVES && this.counterOfDir[3] ==  NOMOVES ) {
			this.counterOfDir[0]++;
			this.lastMove = Direction.NORTH;
		}else if(this.counterOfDir[0] >= MAXMOVES && this.counterOfDir[1] <= MAXMOVES && this.counterOfDir[2] == NOMOVES && this.counterOfDir[3] ==  NOMOVES ) {
			this.counterOfDir[1]++;
			this.lastMove = Direction.EAST;
		}else if(this.counterOfDir[0] >= MAXMOVES && this.counterOfDir[1] >= MAXMOVES && this.counterOfDir[2] <= MAXMOVES && this.counterOfDir[3] ==  NOMOVES) {
			this.counterOfDir[2]++;
			this.lastMove = Direction.SOUTH;
		}else if(this.counterOfDir[0] >= MAXMOVES && this.counterOfDir[1] >= MAXMOVES && this.counterOfDir[2] >= MAXMOVES && this.counterOfDir[3] <=  MAXMOVES){
			counterOfDir[3]++;
			if(this.counterOfDir[3] >= MAXMOVES)
				this.counterOfDir = new int[4];
			this.lastMove = Direction.WEST;
		}
		return this.lastMove;
		
	}
	/*
	 * this method is used to get the string representation of the critter
	 * 
	 * based on lastMove direction
	 * 
	 * @return dependent on lastMove
	 */
	public String toString() {
		
		if(lastMove  == Direction.NORTH || lastMove  == Direction.CENTER) {
			return "^";
		}else if(lastMove == Direction.EAST) {
			return ">";
		}else if(lastMove == Direction.SOUTH) {
			return "V";
		}else {
			return "<";
		}
	}

	
}

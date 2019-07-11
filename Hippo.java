import java.awt.Color;
import java.util.Random;

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

public class Hippo extends Critter{
	private int hunger;
	private int steps = 0;
	private boolean hungry = true;
	Random ranIndex = new Random();
	private int currentDirIndex;
	private final static int NEXTINT = 4;
	private static final int MAXSTEPS = 6;
	private Direction lastMove;
	int wantHungry;
	private static final Direction[] VALIDDIRECTS = {Direction.NORTH, Direction.EAST, Direction.SOUTH, Direction.WEST}; 
	public Hippo(int hunger) {
		super();
		this.hunger = hunger;
		this.wantHungry = hunger;
		this.currentDirIndex = ranIndex.nextInt(NEXTINT); 
	}
	/*
	 * This method is used to signify if the critter should eat or not
	 * 
	 * This version is determined by hunger counter not == 0
	 * 
	 * @returns true if the hunger counter != 0 false when  hunger == 0
	 */
	public boolean eat() {
		if(this.wantHungry > 0) {
			this.wantHungry --; 
			return true;
		}
			hungry = false;
			return false;
	}
	/*
	 * This method is used to determine What attack move is used when put into a
	 * fight
	 * 
	 * If hungry scratch else pounce
	 * 
	 * @returns Attack.Scratch if hungry else Attack.POUNCE
	 */
	public Attack fight(String opponent) {
		if(hungry) {
			return Attack.SCRATCH;
		}
			return Attack.POUNCE;
	}
	
	/*
	 * This returns the color that is color of the critter
	 * 
	 * WHITE is hunger counter > 0 , GRAY if hunger counter = 0
	 * 
	 * @return Attack.WHITE is hunger counter > 0 , Attack.GRAY if hunger counter = 0
	 */
	
	public Color getColor() {
		if(this.wantHungry <= 0) {
			return Color.white;
		}
		return Color.gray;
	}
	
	public Direction getMove() {
		if(steps < MAXSTEPS) {
			steps ++;
			if(steps >= MAXSTEPS) {
				steps = 0;
				this.currentDirIndex = ranIndex.nextInt(NEXTINT);
				while(VALIDDIRECTS[currentDirIndex] == this.lastMove) {
					this.currentDirIndex = ranIndex.nextInt(NEXTINT);
				}
			}
		}	
		this.lastMove = VALIDDIRECTS[currentDirIndex]; 
		return this.lastMove;
	}

	public String toString() {
		
		if(wantHungry >= 1) {
			return "" + wantHungry;
		}
			return "" + 0;
	}

}

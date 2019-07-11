
/*
 *  CS312 Assignment 2.
 *  On my honor, David Alvarez, this programming assignment is my own work.
 *
 *  A program to print out the UT Tower in ASCII art form.
 *
 *  Name:David Alvarez
 *  email address: david.alvarez@utexas.edu
 *  UTEID:DA28443
 *  Section 5 digit ID:50724
 *  Grader name:
 *  Number of slip days used on this assignment:0
 */

public class Tower {
    
    // CS312 students, DO NOT ALTER THE FOLLOWING LINE except for the 
    // value of the literal int.
    // You may change the literal int assigned to SIZE to any value from 2 to 100.
    // In the final version of the program you submit set the SIZE to 3.
    public static final int SIZE = 3;
//This function call the function that prints the entire tower
    public static void main(String[] args) {
    	wholeTower();
    } 
//This function prints Calls all the methods that together build the tower
    public static void wholeTower() {
    	topOfTower();
    	middleOfTower();
    	topOfTowerBase();
    	bottomOfTowerBase();
    }
// This function builds the very top part of the Tower
    public static void topOfTower() {
    	int heightWOBorder = SIZE *2 -2;
    	int width = SIZE * 2 - 1;
    	whiteSpaceForTopOfTower();
    	borderOfTopOfTower();
    	for(int currentAmountOfRows = 0; currentAmountOfRows < heightWOBorder; currentAmountOfRows++){
    		whiteSpaceForTopOfTower();
    		for(int currentPositionInWidth = 0; currentPositionInWidth < width; currentPositionInWidth ++) {
        		System.out.print("|");
        	}
    		System.out.println("");
    	}
    	whiteSpaceForTopOfTower();
    	borderOfTopOfTower();
    }
 //This function is dedicated to providing the necessary white space for the top of the tower
    public static void whiteSpaceForTopOfTower() {
    	int maxAmountOfWhiteSpace = SIZE * 4 +2;
    	for(int currentAmountOfWhiteSpace = 0; currentAmountOfWhiteSpace < maxAmountOfWhiteSpace; currentAmountOfWhiteSpace ++ ) {
    		System.out.print(" ");
    	}
    }
//This function provides the necessary border that appears twice at the top 
public static void borderOfTopOfTower() {
    	int width = SIZE*2-1;
    	for(int currentPositionInWidth = 0; currentPositionInWidth < width; currentPositionInWidth ++) {
    		System.out.print("#");
    	}
    		System.out.println("");
    }
  //This function puts together all of the neccessary components to build the middle of the tower  
   public static void middleOfTower() { 
	   int height= SIZE * SIZE;
	   for(int row = 0; row < height; row++) {
		   			whiteSpaceForMiddleOfTower();
		   			dividers();
		   			whiteSpaceForMiddleOfTower();
			   		windowRow();   
	   		}	   
	   				whiteSpaceForMiddleOfTower();
	   				dividers();
   } 
   //This function provides the necessary left margin of the middle of the tower
   public static void whiteSpaceForMiddleOfTower() {
	   int whiteSpaceMax = SIZE * 4;
	   for(int whiteSpace = 0; whiteSpace < whiteSpaceMax; whiteSpace ++) {
		   System.out.print(" ");
	   	}
   }
//This function prints the repetitive dividers between the window rows   
   public static void dividers() {
	   int numberOfSymbolsInDivider = SIZE*2 +3;
	   for(int currentNumberOfSymbols = 0; currentNumberOfSymbols < numberOfSymbolsInDivider; currentNumberOfSymbols++) {
		   System.out.print("~");
	   }
	   		System.out.println("");
   }
//This function prints the necessary repetitive window pattern
   public static void windowRow() {
	   int numberOfWindows= SIZE;
	   		System.out.print("|");
	   for(int pairsOfSymbols = 0 ; pairsOfSymbols < numberOfWindows; pairsOfSymbols++) {
		   System.out.print("-O");
	   }
	   		System.out.println("-|");
   }
//This function prints the Top of the Tower Base
   public static void topOfTowerBase() {
	   int heightOfTopOfTower= (SIZE/2) +1;
	   int currentAmountOfMaxWhiteSpace = (heightOfTopOfTower*3) -3;
	   int currentWidth = (SIZE*5)- currentAmountOfMaxWhiteSpace;
	   for(int row = 0; row < heightOfTopOfTower; row++  ) {
		   for(int whiteSpace = 0; whiteSpace < currentAmountOfMaxWhiteSpace; whiteSpace++) {
			   System.out.print(" ");
		   }
		   		System.out.print("/");
		   for(int amountOfPairOfSymbols = 0; amountOfPairOfSymbols < currentWidth; amountOfPairOfSymbols++ ) {
			   	System.out.print("\"'");
		   }
		   		System.out.println("\"\\");
		   		currentAmountOfMaxWhiteSpace-= 3;
		   		currentWidth += 3;
	   }
   }
 //This function prints the repetitive base tower pattern  
   public static void rowOfBottomTower() {
	   int width = SIZE*5;
	   System.out.print("/");
		for(int amountPairOfSymbols= 0; amountPairOfSymbols < width ; amountPairOfSymbols ++) {
			System.out.print("\"O");
		}
		System.out.println("\"\\");
   }
//This function puts all of the components of the bottom of the tower base together
    public static void bottomOfTowerBase() {
    	int heightOfBottomOfTower = SIZE ;
    	for(int row = 0; row < heightOfBottomOfTower; row++) {
    			rowOfBottomTower();
    	}
    	
    }

    
}

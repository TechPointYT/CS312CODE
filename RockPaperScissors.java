import java.util.Scanner;

/**
 * CS312 Assignment 4.
 *
 * On my honor, <NAME>, this programming assignment is my own work and I have
 * not shared my solution with any other student in the class.
 *
 * A program to play Rock Paper Scissors
 *
 *  email address:
 *  UTEID:
 *  Number of slip days used on this assignment:
 */

public class RockPaperScissors {
	
    /* A program to allow a human player to play rock - paper - scissors
     * against the computer. If args.length != 0 then we assume
     * the first element of args can be converted to an int
     */
	
	public static final int ROCK = 1;
	public static final int PAPER = 2;
	public static final int SCISSORS = 3;
	public static final int DRAW = 0;
	public static final int PLAYERWIN = 1;
	public static final int COMPUTERWIN = 2;
	/*
     * This method combines all the methods created to play the game
     */
    public static void main(String[] args) {
		// CS312 Students. Do not change the following line.
        RandomPlayer computerPlayer = buildRandomPlayer(args);

        // CS312 students do no change the following line. Do not create any other Scanners.
        Scanner keyboard = new Scanner(System.in);
        
        String nameOfPlayer = getName(keyboard);
    	int numberOfRounds = getNumberOfRounds(keyboard,nameOfPlayer);
    	int computerChoice = computerPlayer.getComputerChoice();
    	int drawsInGame = 0;
    	int numberOfComputerWins = 0;
    	int numberOfPlayerWins = 0;
    	int playerChoice;
    	int roundResults;
    	System.out.println();
    	for(int currentRound = 0; currentRound < numberOfRounds; currentRound++){
    		System.out.printf("Round %d.\n", currentRound+1);
    		playerChoice = getPlayerChoice(keyboard, nameOfPlayer);
    		roundResults = getResultOfRound(nameOfPlayer, playerChoice, computerChoice);
    			if(roundResults == DRAW) {
    				drawsInGame += 1;
    			}else if (roundResults == PLAYERWIN) {
    				numberOfPlayerWins += 1;
    			}else if(roundResults == COMPUTERWIN) {
    				numberOfComputerWins += 1;
    			}
    	}
    	printResults(nameOfPlayer,numberOfRounds,drawsInGame,numberOfPlayerWins,numberOfComputerWins);

        keyboard.close();
    }
    /*
     * This method gets the name of the player
     * @para1 this scanner is used to get the input 
     * @returns the name of the player
     */
    public static String getName(Scanner input) {
    	System.out.println("Welcome to ROCK PAPER SCISSORS. I, Computer, will be your opponent.");
    	System.out.print("Please type in your name and press return: ");
    	String name = input.next();
    	System.out.println();
    	System.out.printf("Welcome %s.", name);
    	System.out.println("\n");
    	return name;
    }
    /*
     * This is used to get the number of rounds for the game
     * @para1 this scanner is what is used to get the input from the keyboard
     * @para2 This is used in formatting for the text by using the name
     * @return the  number round of paper rock scissors that will be used
     */
    public static int getNumberOfRounds(Scanner input, String name) {
    	System.out.println("All right "+ name +". How many rounds would you like to play?");
    	System.out.print("Enter the number of rounds you want to play and press return: ");
    	int numberOfRounds = input.nextInt(); 
    	return numberOfRounds;
    }
    /*
     * This is used the player's choice for the ground
     * @para1 this scanner is what is used to get the input from the keyboard
     * @para2 This is used in formatting for the text by using the name
     * @return the players choice for that round of paper rock scissors that will be used
     */
    public static int getPlayerChoice(Scanner input, String name) {
    	System.out.printf("%s, please enter your choice for this round.\n", name);
    	System.out.print("1 for ROCK, 2 for PAPER, and 3 for SCISSORS: ");
    	int playerChoice = input.nextInt();
    	return playerChoice;
    }
    
    
    /*
     * This method combines the usage of the resultsIn methods to calculate Who wins or if its a draw that round
     * @para1 This is used in the when print the results
     * @para2 This is used in the calculation of who wins
     * @para3 this is used the calculation of who wins
     */
    public static int getResultOfRound(String name, int playerChoice, int computerChoice) {
    	if(computerChoice == ROCK){
    		System.out.print("Computer picked ROCK, ");
    		return resultsInRocks(playerChoice, name);	
    	}else if(computerChoice == PAPER){
    		System.out.print("Computer picked PAPER,");
    		return resultsInPaper(name, playerChoice);
    	}
    		System.out.print("Computer picked SCISSOR,");
    		return resultInScissor(name , playerChoice);	
    	
    }
    /*This method is used to calculate results if the CPU chooses Paper
     * @para1 This is used in formatting the printed results of the round
     * @para2 This is used to calculate if the cpu or human wins
     * @returns the number that signifies player win computer win or draw
     */
    public static int resultsInRocks(int playerChoice, String playerName) {
    	if(playerChoice == PAPER) {
    		System.out.printf("%s picked PAPER\n", playerName);
			System.out.println("\nPAPER covers ROCK. You win.\n");
			return PLAYERWIN;
		}else if(playerChoice == SCISSORS){
			System.out.printf("%s picked SCISSORS\n", playerName);
			System.out.println("\nROCK breaks SCISSORS. I win.\n");
			return COMPUTERWIN;
		}
			System.out.printf("%s picked ROCK\n", playerName);
			System.out.println("\nWe picked the same thing! This round is a draw.\n");
			return DRAW;
    }
    /*
     * This method is used to calculate results if the CPU chooses Paper
     * @para1 This is used in formatting the printed results of the round
     * @para2 This is used to calculate if the cpu or human wins
     * @returns the number that signifies player win computer win or draw
     */
    public static int resultsInPaper(String playerName, int playerChoice) {
    	if(playerChoice == ROCK) {
			System.out.printf("%s picked ROCK\n", playerName);
			System.out.println("\nPAPER covers ROCK. I win.\n");
			return COMPUTERWIN;
		}else if(playerChoice == SCISSORS){
			System.out.printf("%s picked SCISSORS\n", playerName);
			System.out.println("\nSCISSORS cut PAPER. You win.\n");
			return PLAYERWIN;
		}
			System.out.printf("%s picked PAPER\n", playerName);
			System.out.println("\nWe picked the same thing! This round is a draw.\n");
			return DRAW;
    }
    /*
     * This number is used to calculate the results of the round if the cpu picks Scissors
     * @para1 This is used in formatting the printed results of the round
     * @para2 This is used to calculate if the cpu or human wins
     * @returns the number that signifies player win computer win or draw
     */
    public static int resultInScissor(String playerName, int playerChoice){
    	if(playerChoice == ROCK) {
			System.out.printf("%s picked ROCK\n", playerName);
			System.out.println("\nROCK breaks SCISSORS. You win.\n");
			return PLAYERWIN;
		}else if(playerChoice == PAPER) {
			System.out.printf("%s picked PAPER\n", playerName);
			System.out.println("\nSCISSORS cut PAPER. I win.\n");
			return COMPUTERWIN;
		}
			System.out.printf("%s picked SCISSORS\n", playerName);
			System.out.println("\nWe picked the same thing! This round is a draw.\n");
			return DRAW;
	
	}
    /*
     * This method prints the results of the n number of rounds that were played 
     * @para1 the name of the player is used to include it when printing the results of rounds they won
     * @para2 the number of rounds is printed in this method
     * @para3 The number of draws between the cpu and player is printed in this method
     * @para4 The number of player wins is combined with player name and printed
     * @para5 This number is printed in this method 
    */
    public static void printResults(String nameOfPlayer, int numberOfRounds,int drawsInGame, int playerWinsInGame, int computerWinsInGame) {
    	System.out.println("Number of games of ROCK PAPER SCISSORS: " + numberOfRounds);
    	System.out.println("Number of times Computer won: " + computerWinsInGame);
    	System.out.printf("Number of times %s won: %d\n", nameOfPlayer, playerWinsInGame);
    	System.out.println("Number of draws: " + drawsInGame);
    	
    }
    
    /*
     * Build the random player. If args is length 0 then the
     * default RandomPlayer is built that follows a predictable
     * sequence. If args.length > 0 then we assume we can
     * convert the first element to an int and build the
     * RandomPlayer with that initial value.
     */
    public static RandomPlayer buildRandomPlayer(String[] args) {
        if(args.length == 0) {
            return new RandomPlayer();
        } else {
            int seed = Integer.parseInt(args[0]);
            return new RandomPlayer(seed);
        }
    }
}
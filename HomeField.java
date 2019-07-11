import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * CS312 Assignment 7.
 * 
 * On my honor, David alvarez ing assignment is my own work and I have
 * not shared my solution with any other student in the class.
 *
 * A program to play determine the extend of home field advantage in sports.
 *
 *  email address:david.alvarez@utexas.edu
 *  UTEID:da28443
 *  Unique 5 digit course ID:50724
 *  Grader name:divya
 *  Number of slip days used on this assignment:2
 */
 
 /**
 * Analysis of results. Include your write-up of results and analysis here:
 *
 */
 
public class HomeField {
    final static String ALPHABET = "abcdefghigjklmnopqrstuvwxyz&";
    // Ask the user for the name of a data file and process
    // it until they want to quit.
    public static void main(String[] args) throws IOException {
        System.out.println("A program to analyze home field advantage in sports.");
        System.out.println();
        // CS312 students. Do not create any other Scanners connected to System.in.
        // Pass keyboard as a parameter to all the methods that need it. 
        Scanner keyboard = new Scanner(System.in);     
        // CS312 students - Add your code here
        File file;
		Scanner parser ;
		boolean contAnalyze = true; 
		while(contAnalyze) {
				file = getValidatedFile(keyboard);
				parser = new Scanner(file);
				contAnalyze = analyzeDocument(parser ,file, keyboard );	
			}	
       
        keyboard.close();
    }
    // CS312 Students - Add your methods here.
    /*
     * this function analyzes the document that was currently entered
     * @param parser this is used to parse through each line in the document
     * @param fileName this is used to make the parser go through each line in file
     * @param keyboard this is used to get input from the person
     * @returns true or false depending on if the user want to analyze another document 
     */
    public static boolean analyzeDocument(Scanner parser , File fileName, Scanner keyboard ) {
    	String line = parser.nextLine();
		String headerPrt1 = "";
		String headerPrt2 = "";
		int homeTeamPoints = 0;
		int currentHomeTeamPoints;
		int awayTeamPoints = 0;
		int currentAwayTeamPoints;
		int homeTeamWins = 0;
		int homeGames = 0;
		int games = 1;
		boolean contAnalyze = true; 
		
			while(parser.hasNextLine()) {
				if(games<= 2) {
					if(games == 1) {
						headerPrt1 = line;
					}else {
						headerPrt2 = line;
					}
				}else {
					currentHomeTeamPoints = getHomeTeamPoints(line);
					if(line.contains("@"))
						homeGames++;	
					homeTeamPoints += currentHomeTeamPoints;
					currentAwayTeamPoints = getAwayTeamPoints(keyboard,line,currentHomeTeamPoints);
					awayTeamPoints += currentAwayTeamPoints;
					homeTeamWins += getHomeTeamWins(currentHomeTeamPoints, currentAwayTeamPoints);
				}
				line= parser.nextLine();	
				games++;
			}
			printInformation(getFormattedHeader(headerPrt1, headerPrt2), homeTeamPoints, awayTeamPoints, games-2 , homeTeamWins, homeGames);
			
			return getIfContinue(keyboard);
    }
    /*
     * this function gets the name of the file from the user
     * @param input this is used to get input from user
     * @return the name of the file
     */
    public static String getFileName(Scanner input) {
    	System.out.print("Enter the file name: ");
    	return input.nextLine();
    	
    }
    /*
     * this function returns boolean based on if the user wants to analyze another document 
     * @param input this is used to get input from user
     * @return boolean based on if the user wants to analyze another document
     */
    public static boolean getIfContinue(Scanner input) {
    	input = new Scanner(System.in);
    	System.out.println("Do you want to check another data set?");
    	System.out.print("Enter Y or y to analyze another file, anything else to quit: ");
    	String answer = input.nextLine();
    	answer = answer.toLowerCase();
    	System.out.println();
    	if(!answer.equals("y")) {
    		return false;
    	}
    	return true;
    }
    /*
     * this function validates the file name that was entered from user to see if it is a valid file
     * @param input this used to get user input for file name
     * @returns validated file name 
     */
    public static File getValidatedFile(Scanner input) {
    	String fileName = getFileName(input);
    	File tester = new File(fileName);	
    	while(!tester.isFile() && !tester.isDirectory()) {
    		System.out.println("Sorry, that file does not exist");
    		fileName = getFileName(input);
    		tester = new File(fileName);
    	}
    	System.out.println();
    	return tester;
    }
    
    /*
     * this functions formats the header into the correct  format 
     * @param prt1 this is part 1 that holds sport name
     * @param prt2 this is part 2 of header that holds sport year 
     * @returns formatted header
     */
    public static String getFormattedHeader(String prt1, String prt2) {
    	return "**********   "+prt1+" --- "+prt2+"   **********";
    }
    /*
     * this function gets home team points from line 
     * @param line being analyzed this is the line that the home team points is retrieved
     * @returns home team points
     */
    public static int getHomeTeamPoints(String lineBeingAnalyzed) {
    	int posOfHomeTeam = lineBeingAnalyzed.indexOf('@');
    	Scanner lineAnalyzer = new Scanner(lineBeingAnalyzed);
    	if(posOfHomeTeam == -1) {
    		return 0;
    	} 		
    		lineBeingAnalyzed = lineBeingAnalyzed.substring(posOfHomeTeam+1);
    		return retrieveHomePoints(lineAnalyzer,lineBeingAnalyzed);
    	
    	
    }
    /*
     * this function gets home team points from line 
     * @param line being analyzed this is the line that the home team points is retrieved
     * @analyzer this is used to parse through line 
     * @returns home team points
     */
    public static int retrieveHomePoints(Scanner analyzer, String leftOverLine ) {
    	leftOverLine = leftOverLine.toLowerCase();
    	analyzer =  new Scanner(leftOverLine);
    	String nextBeingTested ;
    	String testedPos;
    	while(analyzer.hasNext() ) {	
    		nextBeingTested = analyzer.next();
    		testedPos ="" + nextBeingTested.charAt(0);
    		if(!ALPHABET.contains(testedPos)) {
    			return Integer.valueOf(nextBeingTested);
    		}
    	}	
    	analyzer.close();
    	return 0;
    	
    }

    
    /*
     * this function gets home team points from line 
     * @param line being analyzed this is the line that the home team points is retrieved
     * @ param homeTeam points this is used to retrive away team points
     * @param lineAnalyzer this scanner is used to parse through line  
     * @returns home team points
     */
    public static int getAwayTeamPoints(Scanner lineAnalyzer, String lineBeingAnalyzed, int homeTeamPoint) {
    	int awayTeamPoints = 0;
    	lineAnalyzer = new Scanner(lineBeingAnalyzed);
    	int startOfDataLine = lineAnalyzer.next().length();
    	lineBeingAnalyzed = lineBeingAnalyzed.substring(startOfDataLine);
    	if(!lineBeingAnalyzed.contains("@")) {
    		return 0 ;
    	}
    	return retrieveAwayPoint(lineAnalyzer, lineBeingAnalyzed, homeTeamPoint);
    }
    /*
     * this function gets home team points from line 
     * @param line being analyzed this is the line that the home team points is retrieved
     * @ param homeTeam points this is used to retrive away team points
     * @param lineAnalyzer this scanner is used to parse through line  
     * @returns home team points
     */
    public static int retrieveAwayPoint(Scanner lineAnalyzer, String reducedLine, int homeTeamPoint) {
    	reducedLine = reducedLine.toLowerCase();
    	lineAnalyzer =  new Scanner(reducedLine);
    	String posTested = ""+ lineAnalyzer.next();
    	String nextBeingTested ;
    	String testedPos;
    	String lineDelimeter = ""+String.valueOf(homeTeamPoint);
    	int pos = reducedLine.indexOf(lineDelimeter.charAt(0));	
    	String testingPos = ""+posTested.charAt(0);
    	if(testingPos.equals("@")  ) {
    		reducedLine = reducedLine.substring(pos);
    		posTested = ""+ lineAnalyzer.next().charAt(0);
    		while(ALPHABET.contains(posTested)) {
    			posTested = ""+ lineAnalyzer.next().charAt(0);
    		}
    		pos = reducedLine.indexOf(posTested);
    		reducedLine = reducedLine.substring(pos);	
    	}else {
    		reducedLine = reducedLine.substring(0,reducedLine.indexOf("@")-1);		
    	}
    	
    	while(lineAnalyzer.hasNext() ) {	
    		nextBeingTested = lineAnalyzer.next();
    		testedPos ="" + nextBeingTested.charAt(0);
    		if(!ALPHABET.contains(testedPos)) {
    			return Integer.valueOf(nextBeingTested);
    		}
    	}
    	return 0;
    }
    /*
     * this function returns if home team wins and  if not 
     * @param homeTeamPoints
     */
    public static int getHomeTeamWins(int homeTeamPoints, int awayTeamPoints) {
    	if(homeTeamPoints > awayTeamPoints) {
    		return 1;
    	}
    	return 0;
    }
    
    public static void printInformation(String header, int homePoints, int awayPoints, int totalNumberOfGames, int numberOfHomeWins, int numberOfGamesWHome) {
    	String percentageSign = "%";
    	printIntroToResults(header);
    	System.out.printf("Total number of games: %,d %n", totalNumberOfGames);
    	System.out.printf("Number of games with a home team: %,d %n", numberOfGamesWHome);
    	System.out.printf("Percentage of games with a home team: %.1f%%\n",calculatePecentWHTeam(totalNumberOfGames, numberOfGamesWHome));
    	System.out.printf("Number of games the home team won: %,d %n", numberOfHomeWins);
    	System.out.printf("Home team win percentage: %.1f%%\n", calculateHTeamWinPercentage(numberOfGamesWHome, numberOfHomeWins));
    	System.out.printf("Home team average margin: %.2f\n", calculateHTeamAvMargin(homePoints, awayPoints, numberOfGamesWHome));
    	System.out.println();
    	
    }
    
    public static void printIntroToResults(String Header) {
    	System.out.println(Header);
    	System.out.println();
    	System.out.println("HOME FIELD ADVANTAGE RESULTS");
    	System.out.println();
    }
    
    public static double calculatePecentWHTeam(int totalGames, int gamesWithHome) {
    	return ((double) gamesWithHome / totalGames) * 100.0;
    }
    public static double calculateHTeamWinPercentage(int gamesWithHome, int numberOfHomeWins) {
    	return (double) numberOfHomeWins/ gamesWithHome * 100.0;
    }
    public static double calculateHTeamAvMargin(int homePoints, int awayPoints, int numberOfGamesWHome){
    	return (double) (homePoints-awayPoints) / numberOfGamesWHome;
    }
    
    
}

/**
 * CS312 Assignment 8.
 * 
 * On my honor, David Alvarez, this programming assignment is my own work and I have
 * not shared my solution with any other student in the class.
 *
 * A program to read a file with raw data from a Keirsey personality test
 * and print out the results.
 *
 *  email address: david.alvarez@utexas.edu
 *  UTEID:	da28443
 *  Unique 5 digit course ID: 50724
 *  Grader name: Divya Manohar
 *  Number of slip days used on this assignment: 0
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Personality {

	// CS312 Student: Add your constants here.

	final static int NUMOFQUESTIONS = 20;
	final static String[] ANSWERTYPES = { "a", "b", "-" };
	final static int MIDDLE = 50;
	final static int HIGH = 100;
	final static int NAMESPACING = 30;
	final static int PERCENTSPACING = 11;
	final static int IANDE = 0;
	final static int SANDN = 1;
	final static int FANDT = 2;
	final static int PANDJ = 3;
	final static int NONEXIST = -1;

	// The main method to process the data from the personality tests
	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in); // do not make any other Scanners connected to System.in
		Scanner fileScanner = getFileScanner(keyboard);
		int numOfRecs = fileScanner.nextInt();
		// CS312 Student: Your code and methods calls go here.
		String[] records = readLines(fileScanner, numOfRecs);
		String[] responses = getResponses(records, numOfRecs);
		int numPerPerson = 4;
		int sizeOfInfo = numOfRecs * numPerPerson;
		int[] percentagesOfEach = getPercentagesOfEach(numOfRecs, responses, sizeOfInfo);
		records = getModifiedRecords(records, percentagesOfEach);
		printInfo(records, percentagesOfEach);
		fileScanner.close();
		keyboard.close();
	}

	// CS312 Student: Add your methods here.
	/*
	 * this function reads the file line by line and puts every line in a String
	 * array
	 * 
	 * @param fileScanner this is used to read through all the lines of the file
	 * 
	 * @param numOfRecords is used to determine the number of lines in the
	 * 
	 * @returns a String array that has all the lines from the file
	 */
	public static String[] readLines(Scanner fileScanner, int numOfRecords) {
		fileScanner.nextLine();
		int numOfLines = numOfRecords * 2;
		String[] linesInFile = new String[numOfLines];
		for (int pos = 0; pos < numOfLines; pos++) {
			String nxtLine = fileScanner.nextLine();
			linesInFile[pos] = nxtLine;
		}
		return linesInFile;
	}

	/*
	 * This function returns a String array with the responses of every record
	 * 
	 * @param records this is used to retrieve the responses from the records
	 * 
	 * @param numOfRecords this is used to determine the size of the array
	 * 
	 * @return String array with only the responses from the records
	 */
	public static String[] getResponses(String[] records, int numOfRecords) {
		String[] responses = new String[numOfRecords];
		int posInResponses = 0;
		int posInRecords = 1;
		int augmentor = 2;
		while (posInResponses < responses.length) {
			responses[posInResponses] = records[posInRecords];
			posInResponses++;
			posInRecords += augmentor;
		}
		return responses;
	}

	/*
	 * This function is used to specifically retrieve the answers from only the I
	 * and E questions
	 * 
	 * @param responses this is the specific response from the records that are
	 * parsed through
	 * 
	 * @return a String array that contains the answers from the responses for the
	 * specific questions
	 */
	public static String[] getIAndEQuestions(String responses) {
		int posInQuests = 0;
		int sizeOfQs = NUMOFQUESTIONS / 2;
		int augmentor = 7;
		String[] IAndEQuestions = new String[sizeOfQs];
		for (int quest = 0; quest < sizeOfQs; quest++) {
			IAndEQuestions[quest] = "" + responses.charAt(posInQuests);

			posInQuests += augmentor;
		}
		return IAndEQuestions;
	}

	/*
	 * This function is used to specifically retrieve the answers based on the
	 * parameter given questions
	 * 
	 * @param responses this is the specific response from the records that are
	 * parsed through
	 * 
	 * @param type this is used to determine what questions to parse through
	 * 
	 * @return a String array that contains the answers from the responses for the
	 * specific questions
	 */
	public static String[] getQuestions(int type, String response) {
		int augmentor = 2;
		int difBetQs = 6;
		String[] SAndIQuests = new String[NUMOFQUESTIONS];
		int Qs = setStartNum(type);
		for (int pos = 0; pos < NUMOFQUESTIONS; pos += augmentor) {
			SAndIQuests[pos] = "" + response.charAt(Qs);
			Qs++;
			SAndIQuests[pos + 1] = "" + response.charAt(Qs);
			Qs += difBetQs;
		}
		return SAndIQuests;
	}

	/*
	 * this functions sets the starting question number for the getQuestion method
	 * 
	 * @param type this is used to determine the starting question number
	 * 
	 * @returns number that is the starting question
	 */
	public static int setStartNum(int type) {
		if (type == SANDN) {
			return 1;
		} else if (type == FANDT) {
			return 3;
		}
		return 5;
	}

	/*
	 * This function is used to count the number of a,b, and - in each array of
	 * questions answers
	 * 
	 * @param answers this is the array that is analyzed in this method
	 * 
	 * @return array of ints that signify the number of each type of answer
	 */
	public static int[] getNumOfAnswers(String[] answers) {
		int typesOfAnswers = 3;
		int[] counters = new int[typesOfAnswers];

		for (int pos = 0; pos < answers.length; pos++) {
			String testedPos = answers[pos].toLowerCase();
			if (testedPos.equals(ANSWERTYPES[IANDE])) {
				counters[IANDE]++;
			} else if (testedPos.equals(ANSWERTYPES[SANDN])) {
				counters[SANDN]++;
			} else if (testedPos.equals(ANSWERTYPES[FANDT])) {
				counters[FANDT]++;
			}
		}

		return counters;
	}

	/*
	 * This function is used to determine personality based on the questions
	 * 
	 * @param percentage this is used to determine personality
	 * 
	 * @param type this used to see which type of personality is being performed
	 * 
	 * @returns the personality based on percentage
	 */
	public static String typeOfPersonality(int percentage, int type) {
		if (percentage == NONEXIST) {
			return "-";
		} else if (percentage == MIDDLE) {
			return "X";
		}
		return personality(percentage, type);
	}

	/*
	 * This is used to determine personality based on percentage
	 * 
	 * @param percentage this is used to determine personality
	 * 
	 * @param type this used to see which type of personality is being performed
	 * 
	 * @returns the personality based on percentage
	 */
	public static String personality(int percentage, int type) {
		if (type == IANDE) {
			return EOrI(percentage);
		} else if (type == SANDN) {
			return NOrS(percentage);
		} else if (type == FANDT) {
			return FOrT(percentage);
		}
		return POrJ(percentage);

	}

	/*
	 * This is a series of functions that is used to return E or I personalities
	 * 
	 * @param percentage this determines what personality is returned
	 * 
	 * @returns personalities I or E
	 */
	public static String EOrI(int percentage) {
		if (percentage > MIDDLE && percentage <= HIGH) {
			return "I";
		}
		return "E";
	}

	/*
	 * This is a series of functions that is used to return N or S personalities
	 * 
	 * @param percentage this determines what personality is returned
	 * 
	 * @returns personalities N or S
	 */
	public static String NOrS(int percentage) {
		if (percentage > MIDDLE && percentage <= HIGH) {
			return "N";
		}
		return "S";
	}

	/*
	 * This is a series of functions that is used to return F or T personalities
	 * 
	 * @param percentage this determines what personality is returned
	 * 
	 * @returns personalities F or T
	 */
	public static String FOrT(int percentage) {
		if (percentage > MIDDLE && percentage <= HIGH) {
			return "F";
		}
		return "T";
	}

	/*
	 * This is a series of functions that is used to return P or J personalities
	 * 
	 * @param percentage this determines what personality is returned
	 * 
	 * @returns personalities P or J
	 */
	public static String POrJ(int percentage) {
		if (percentage > MIDDLE && percentage <= HIGH) {
			return "P";
		}
		return "J";
	}

	/*
	 * This is function is used to calculation the percentage of As and Bs in the
	 * answers
	 * 
	 * @param answers this holds the number of each answer type from the arrays of
	 * questions
	 * 
	 * @returns calculated/formatted percentage
	 */
	public static int setPercentage(int[] answers) {

		int totalAnswers = answers[0] + answers[1];
		if (answers[0] == 0 && answers[1] == 0)
			return NONEXIST;

		double temp = (double) answers[1] / totalAnswers * 100;
		int percentage = (int) (temp + 0.5);

		return percentage;
	}

	/*
	 * this function is used to create array of ints that holds the percentages of
	 * each record
	 * 
	 * @param numOfRecs this is used to parse through the different types of
	 * questions
	 * 
	 * @param responses this is what is analyzed to retrieve percentages
	 * 
	 * @param sizeOfInfo the is used to determine size of percentages
	 * 
	 * @return an array of percentages
	 */
	public static int[] getPercentagesOfEach(int numOfRecs, String[] responses, int sizeOfInfo) {
		int[] percentagesOfEach = new int[sizeOfInfo];
		String[] IAndEQuestions;
		String[] SAndIQuestions;
		String[] TAndFQuestions;
		String[] JAndPQuestions;
		int size = responses.length;
		int personalityTypes = 4;
		int augmentor = personalityTypes;
		int position = 0;
		for (int resp = 0; resp < size; resp++) {
			String response = responses[resp];
			IAndEQuestions = getIAndEQuestions(response);
			SAndIQuestions = getQuestions(1, response);
			TAndFQuestions = getQuestions(2, response);
			JAndPQuestions = getQuestions(3, response);
			for (int pos = 0; pos < personalityTypes; pos++) {
				if (pos == IANDE) {
					percentagesOfEach[position + pos] = setPercentage(getNumOfAnswers(IAndEQuestions));
				} else if (pos == SANDN) {
					percentagesOfEach[position + pos] = setPercentage(getNumOfAnswers(SAndIQuestions));
				} else if (pos == FANDT) {
					percentagesOfEach[position + pos] = setPercentage(getNumOfAnswers(TAndFQuestions));
				} else {
					percentagesOfEach[position + pos] = setPercentage(getNumOfAnswers(JAndPQuestions));
				}
			}
			position += augmentor;
		}
		return percentagesOfEach;
	}

	/*
	 * this function is used to modify the initial Records array and replacing the
	 * responses with their respective personalities
	 * 
	 * @param records this is being modified to its new version
	 * 
	 * @param percentages this used to to determine personality types and put in new
	 * records array
	 * 
	 * @returns modified records array
	 */
	public static String[] getModifiedRecords(String[] records, int[] percentages) {
		int position = 0;
		int numOfPersGroup = 4;
		int augmentor = 4;
		int modifier = 2;
		for (int record = 1; record < records.length; record += modifier) {
			records[record] = "";
			for (int type = 0; type < numOfPersGroup; type++) {
				int posModifier = type;
				int percentage = percentages[position + posModifier];
				records[record] = records[record] + typeOfPersonality(percentage, type);
			}
			position += augmentor;
		}
		return records;
	}

	/*
	 * this function is used to print out all of the information
	 * 
	 * @param Records this is to print the name and the personality of each record
	 * 
	 * @param percentages this is used to print the percentages of each record
	 */
	public static void printInfo(String[] Records, int[] percentages) {
		int position = 0;
		int recordsAugmentor = 2;
		int numOfpersonalites = 4;
		int diffInRec = 1;
		for (int record = 0; record < Records.length; record += recordsAugmentor) {
			System.out.printf("%"+ NAMESPACING +"s:", Records[record]);

			for (int percentage = 0; percentage < numOfpersonalites; percentage++) {
				int currentPercentage = percentages[position + percentage];
				if (currentPercentage == NONEXIST) {
					System.out.printf("%"+ PERCENTSPACING +"s", "NO ANSWERS");
				} else {
					System.out.printf("%"+ PERCENTSPACING +"d", percentages[position + percentage]);
				}
			}
			System.out.print(" = " + Records[record + diffInRec]);
			System.out.println();
			position += numOfpersonalites;
		}
	}

	// Method to choose a file.
	// Asks user for name of file.
	// If file not found creates a Scanner hooked up to a dummy set of data.
	// Example use:
	public static Scanner getFileScanner(Scanner keyboard) {
		Scanner result = null;
		try {
			System.out.print("Enter the name of the file with the personality data: ");
			String fileName = keyboard.nextLine().trim();
			System.out.println();
			result = new Scanner(new File(fileName));
		} catch (FileNotFoundException e) {
			System.out.println("Problem creating Scanner: " + e);
			System.out.println("Creating Scanner hooked up to default data " + e);
			String defaultData = "1\nDEFAULT DATA\n" + "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"
					+ "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA";
			result = new Scanner(defaultData);
		}
		return result;
	}
}

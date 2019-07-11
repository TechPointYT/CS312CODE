import java.util.Scanner;

/**
 * CS312 Assignment 5.
 * 
 * On my honor, David Alvarez, this programming assignment is my own work and I
 * have not shared my solution with any other student in the class.
 *
 * A program to play calculate the readability of text using the Flesch Reading
 * Ease Test.
 *
 * email address: David.alvarez@utexas.edu 
 * UTEID: da28443 
 * Unique 5 digit course ID: 50724 
 * Number of slip days used on this assignment: 1
 */
public class Flesch {

	/*
	 * A program that calculates the Flesch Reading Ease Test for text entered by
	 * the user.
	 */
	public final static double CONSTANT1 = 206.835;
	public final static double CONSTANT2 = 1.015;
	public final static double CONSTANT3 = 84.6;

	public static void main(String[] args) {
		// CS312 students, do not create any other Scanners besides this one.
		// Pass this is a parameter as necessary.
		Scanner key = new Scanner(System.in);
		// This is a sentence. So is this!
		// TODO CS312 students add your method calls here.
		printIntro();
		int numOfTextsToAnalyze = getNumberOfInputText(key);
		repeatedNTextAnalysis(key, numOfTextsToAnalyze);
		key.close();
	}

	// This functions the introduction text to the program
	public static void printIntro() {
		System.out.println("*** Flesch Reading Ease Test ***");
		System.out.println("This program asks you for text and");
		System.out.println("then calculates and displays the");
		System.out.println("readability of the text based on the");
		System.out.println("Flesch Reading Ease Test.\n");
	}

	// This function retrieves the text that will be analyzed
	// param input this is scanner is what will be used to get the input text
	// param numberOfText this number is being used to signify which text is being
	// inputed
	// return the string that is going to analyzed
	public static String getText(Scanner input, int numberOfText) {
		System.out.print("Please enter text sample number " + numberOfText + ": ");
		String sentence = input.nextLine();
		System.out.println();

		return sentence;
	}

	// this function gets the number of texts that will be analyzed
	// param input this is the scanner that is used to get the number of texts
	// returns the int that signifies the number of texts that will inputed
	public static int getNumberOfInputText(Scanner input) {
		System.out.print("How many pieces of text do you want to analyze? ");
		int numberOfInputText = input.nextInt();
		input.nextLine();
		System.out.println();
		return numberOfInputText;
	}

	// this function is used to do text analysis based on the number of texts that
	// are to be analyzed
	// param input this scanner is sent to the getText function to get the input
	// text
	// param numberOfTextToAnalyzed this number is used as a counter for the for
	// loop
	public static void repeatedNTextAnalysis(Scanner input, int numberOfTextsToAnalyze) {
		String text = "";
		int numberOfText = 0;
		for (int numberOfTextsAnalyzed = 0; numberOfTextsAnalyzed < numberOfTextsToAnalyze; numberOfTextsAnalyzed++) {
			numberOfText = numberOfTextsAnalyzed + 1;
			text = getText(input, numberOfText);
			analyzeText(text);
		}
	}

	// this function is where the text that is inputed is analyzed by the individual
	// analysis functions and then prints the results
	// param text this is the text that is sent to the analysis functions
	public static void analyzeText(String text) {
		int numberOfSentences = getNumberOfSentences(text);
		int numberOfWords = getnumberOfWords(text);
		int numberOfSyllables = getnumberOfSyllables(text);
		double fleschScore = getFleschScore(numberOfWords, numberOfSentences, numberOfSyllables);
		printTextInfo(text, numberOfSentences, numberOfWords, numberOfSyllables, fleschScore);
	}

	// This is one of the analysis functions that analyzes the text and return the
	// number of sentences
	// param text this is the text that is analyzed
	// returns number of sentences in text
	public static int getNumberOfSentences(String text) {
		int numberOfSentences = 0;
		int endOfStringPos = text.length();
		char charBeingTested;
		for (int position = 0; position < endOfStringPos; position++) {
			charBeingTested = text.charAt(position);
			numberOfSentences += isASentenceDelimiter(charBeingTested);
		}
		if (numberOfSentences == 0) {
			numberOfSentences++;
		}
		return numberOfSentences;
	}

	// this function is used the determine if character is a sentence delimiter
	// param item this is the char that is tested
	// return 1 if is sentence delimiter and 0 if it is not a sentences delimiter
	public static int isASentenceDelimiter(char item) {
		char period = '.';
		char colon = ':';
		char semicolon = ';';
		char questionMark = '?';
		char exclamationMark = '!';
		if (item == period || item == colon || item == semicolon) {
			return 1;
		} else if (item == questionMark || item == exclamationMark) {
			return 1;
		}
		return 0;
	}

	// this function is used to determine the number of words in a sentence
	// param text this is text that analyzed is in function
	// returns number of words in sentence
	public static int getnumberOfWords(String text) {
		int numberOfWords = 0;
		int endOfStringPos = text.length() - 1;
		text = " " + text.toLowerCase();

		for (int position = 0; position <= endOfStringPos; position++) {
			numberOfWords += isWordChecker(text, position, position + 1);
		}
		return numberOfWords;
	}

	// this function is used to check if whats being tested is the start of the word
	// param text this is whats being used to test
	// param position this is the position in the text that is being test
	// param aheadPosition this is the position ahead of the orig pos that is used
	// to analyze if start of word
	// returns 1 if is word and 0 if it is not
	public static int isWordChecker(String text, int position, int aheadPosition) {
		char charAtPos = text.charAt(position);
		char charAtAheadPos = text.charAt(aheadPosition);
		if (isWordDelimiter(charAtPos) == 1 || isASentenceDelimiter(charAtPos) == 1) {
			if (isASentenceDelimiter(charAtAheadPos) == 0 && isWordDelimiter(charAtAheadPos) == 0) {
				return 1;
			}
		}
		return 0;
	}

	// this function test is a character is a blank space delimiter
	// param charAtPos this is the character that is being tested
	// returns 1 if word delimiter and 0 if it is not
	public static int isWordDelimiter(char charAtPos) {
		char blank = ' ';
		char tab = '\t';
		char newLine = '\n';
		if (charAtPos == blank || charAtPos == tab || charAtPos == newLine) {
			return 1;
		}
		return 0;

	}

	// this function gets number of syllables from analyzed text
	// param text this is the text that is being analyzed
	// returns number of syllables in text
	public static int getnumberOfSyllables(String text) {
		int numberOfSyllables = 0;
		int endOfStringPos = text.length() - 1;
		text = " " + text.toLowerCase();
		char charAtPos;
		char charAtBehindPos;
		for (int positionInString = 0; positionInString <= endOfStringPos; positionInString++) {
			charAtPos = text.charAt(positionInString);
			if (isVowel(charAtPos) == 1) {
				charAtBehindPos = text.charAt(positionInString - 1);
				if (isVowel(charAtBehindPos) == 0) {
					numberOfSyllables++;
				}
			}
		}
		return numberOfSyllables;
	}

	// this function is a function that is used to see if a character is a vowel
	// param itemTested this is the character that is being tested
	// returns 1 if it is a vowel and 0 if it is not
	public static int isVowel(char itemTested) {
		char a = 'a';
		char e = 'e';
		char i = 'i';
		char o = 'o';
		char u = 'u';
		if (itemTested == a || itemTested == e || itemTested == i) {
			return 1;
		} else if (itemTested == o || itemTested == u) {
			return 1;
		}
		return 0;
	}

	// this function is used the calculate the flesch score for the text that was
	// analyzed
	// param totalWords is used in the calculation equation
	// param totalsentences is used in the calculation equation
	// param totalSyllables is used in the calculation equation
	// returns the calculated flesch Score
	public static double getFleschScore(int totalWords, int totalSentences, int totalSyllables) {
		double partOfEquation1 = (double) totalWords / totalSentences;
		double partOfEquation2 = (double) totalSyllables / totalWords;
		return CONSTANT1 - (CONSTANT2 * partOfEquation1) - (CONSTANT3 * partOfEquation2);
	}

	// This function prints out all of the statistics for the text analyzed
	// param text this is printed here
	// param numberOfSentences this is printed here
	// param numberOfWords this is printed here
	// param numberOfSyllables this is printed here
	// param fleschScore this is rounded to the nearest tenth and then printed
	public static void printTextInfo(String text, int numberOfSentences, int numberOfWords, int numberOfSyllables,
			double fleschScore) {
		double roundedFleschScore = Math.round(fleschScore * 10.0) / 10.0;
		System.out.println("Statistics for this text: " + text);
		System.out.println("Number of sentences: " + numberOfSentences);
		System.out.println("Number of words: " + numberOfWords);
		System.out.println("Number of syllables: " + numberOfSyllables);
		System.out.println("Flesch score: " + roundedFleschScore);
		System.out.println();

	}

}

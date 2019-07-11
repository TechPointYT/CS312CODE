/**
 * CS312 Assignment 6.
 * 
 * On my honor, <NAME>, this programming assignment is my own work and I have
 * not shared my solution with any other student in the class.
 *
 * A program to play Hangman.
 *
 *  email address: david.alvarez@utexas.edu
 *  UTEID: Da28443
 *  Unique 5 digit course ID: 50724
 *  Number of slip days used on this assignment: 0
 */

import java.util.Scanner;

public class Hangman {

	public static void main(String[] args) {
		intro();
		PhraseBank phrases = buildPhraseBank(args);
		// CS312 Students -- Do not create any additional Scanners.
		Scanner keyboard = new Scanner(System.in);

		// CS312 students: add your code here

		playGame(keyboard, phrases);
		/*
		 * String movie = phrases.getNextPhrase(); for(int a = 0; a< 6; a++) {
		 * System.out.println(movie); movie = phrases.getNextPhrase(); }
		 */

		keyboard.close();
	}

	// CS312 students: add your methods here.

	// Build the PhraseBank.
	// If args is empty or null, build the default phrase bank.
	// If args is not null and has a length greater than 0
	// then the first elements is assumed to be the name of the
	// file to build the PhraseBank from.
	public static PhraseBank buildPhraseBank(String[] args) {
		PhraseBank result;
		if (args == null || args.length == 0 || args[0] == null || args[0].length() == 0) {
			result = new PhraseBank();
			// CS312 students, uncomment this line if you would like less predictable
			// behavior
			// from the PhraseBank during testing. NOTE, this line MUST be commented out
			// in the version of the program you turn in or your will fail all tests.
			// THE FOLLOWING LINE MUST BE COMMENTED OUT IN THE VERSION YOU SUBMIT!!!
			// result = new PhraseBank("HangmanMovies.txt", true);
		} else {
			result = new PhraseBank(args[0]);
		}

		return result;
	}

	// show the intro to the program
	public static void intro() {
		System.out.println("This program plays the game of hangman.");
		System.out.println();
		System.out.println("The computer will pick a random phrase.");
		System.out.println("Enter letters for your guess.");
		System.out.println("After 5 wrong guesses you lose.");
		System.out.println();
	}

	public static void printType(String type) {
		System.out.println("I am thinking of a " + type + " ...\n");
	}

	/*
	 * this function sets the initial hidden revealed word
	 * 
	 * @param currentSecretWord this is used to set to to the initial revealed word
	 * 
	 * @returns the set revealed word
	 */
	public static String setInitialRevealedWord(String currentSecretWord) {
		int sizeOfRevWord = currentSecretWord.length();
		String revealedWord = "";
		for (int pos = 0; pos < sizeOfRevWord; pos++) {
			if (currentSecretWord.charAt(pos) != '_') {
				if (pos == 0 || pos == 1) {
					revealedWord = "*" + revealedWord;
				} else {
					revealedWord = revealedWord.substring(0, pos) + "*";
				}
			} else {
				revealedWord = revealedWord.substring(0, pos) + "_";
			}
		}
		return revealedWord;
	}

	/*
	 * this function set the initial valid answer
	 * 
	 * @returns the set valid answers string
	 */
	public static String setInitialValidAnswers() {
		String validAnswers = "";
		char letter = 'A';
		for (int alphabet = 0; alphabet < 25; alphabet++) {
			validAnswers = validAnswers + letter + "--";
			letter++;
		}
		validAnswers += letter;
		return validAnswers;
	}

	/*
	 * this function is called to play the game of hang man
	 * 
	 * @param input this is used to get any input necessary for the game
	 * 
	 * @param secretWordGen this is used to generate the secret phrase
	 */
	public static void playGame(Scanner input, PhraseBank secretWordGen) {
		String secretPhrase = secretWordGen.getNextPhrase();
		String revealedWord = setInitialRevealedWord(secretPhrase);
		String validAnswers = setInitialValidAnswers();
		String type = secretWordGen.getTopic();
		boolean continuePlaying = true;

		while (continuePlaying) {
			printFirstGuessInfo(validAnswers, revealedWord, type);
			continuePlaying = playRound(input, type, secretPhrase, validAnswers, revealedWord);
			if (continuePlaying) {
				validAnswers = setInitialValidAnswers();
				secretPhrase = secretWordGen.getNextPhrase();
				revealedWord = setInitialRevealedWord(secretPhrase);
			}
		}
	}

	/*
	 * this function is used to play a round of hangman
	 * 
	 * @param input this is used to retrieve any user input
	 * 
	 * @param type this holds the topic of the secret phrase
	 * 
	 * @param secretPhrase this holds the secret phrase for the current round of
	 * hangman
	 * 
	 * @param validAnswers this holds the current valid answers allowed to enter
	 * 
	 * @param revealedWord this hold the current revealed word format based on the
	 * current secret phrase
	 * 
	 * @returns true or false based on if it wants to play another round
	 */
	public static boolean playRound(Scanner input, String type, String secretPhrase, String validAnswers,
			String revealedWord) {
		String guess;

		int wrongGuesses = 0;
		while (wrongGuesses < 5 && !secretPhrase.equals(revealedWord)) {
			guess = getValidatedGuess(input, validAnswers);
			System.out.println("You guessed " + guess + ".\n");
			if (secretPhrase.contains(guess)) {
				System.out.println("That is present in the secret phrase.\n");
				revealedWord = setCurrentRevealedWord(secretPhrase, revealedWord, guess);
			} else {
				System.out.println("That is not present in the secret phrase.\n");
				wrongGuesses++;
			}
			validAnswers = removeGuessedLetter(validAnswers, guess);
			if (wrongGuesses < 5 && !secretPhrase.equals(revealedWord))
				printAfterEveryGuess(wrongGuesses, revealedWord, validAnswers);
		}
		return getIfContinue(input, revealedWord, secretPhrase, wrongGuesses);
	}

	/*
	 * this function gets if the user wants to play another round of hangman
	 * 
	 * @param input used to get user input
	 * 
	 * @param revealedWord this is used to check if win or lose
	 * 
	 * @param secretPhrase this is used to check if win or lose
	 * 
	 * @param wrongGuesses this is used to check if win or lose
	 * 
	 * @returns true or false based on if wants to play another round
	 */
	public static boolean getIfContinue(Scanner input, String revealedWord, String secretPhrase, int wrongGuesses) {
		printCheckWinOrLose(revealedWord, secretPhrase, wrongGuesses);
		System.out.println("Do you want to play again?");
		System.out.print("Enter 'Y' or 'y' to play again: ");
		String YorN = "" + input.next().charAt(0);
		YorN = YorN.toUpperCase();
		System.out.println();
		if (!YorN.equals("Y")) {
			return false;
		}
		return true;

	}

	/*
	 * this function print the necessary info after every guess made
	 * 
	 * @param wrongGuesses this prints the number wrong guess after the current
	 * guess
	 * 
	 * @param revealedWord this is used to print the current revealed word
	 * 
	 * @param validAnswers this is used to print the current valid answers
	 */
	public static void printAfterEveryGuess(int wrongGuesses, String revealedWord, String validAnswers) {
		System.out.println("Number of wrong guesses so far: " + wrongGuesses);
		System.out.println("The current phrase is " + revealedWord);
		System.out.println();
		printValidAnswers(validAnswers);
	}

	/*
	 * this function prints the info before the first guess
	 * 
	 * @param validAnswers this is used to print the unaltered valid answers
	 * 
	 * @param revealedWord this is used to print the unaltered revealed word
	 * 
	 * @param type this is used to print the type of secret phrase
	 */
	public static void printFirstGuessInfo(String validAnswers, String revealedWord, String type) {
		printType(type);
		System.out.println("The current phrase is " + revealedWord);
		System.out.println();
		printValidAnswers(validAnswers);

	}

	/*
	 * this is called when player wins and prints the winning output
	 * 
	 * @param secretPhrase this is used to print the secret phrase
	 * 
	 * @param wrongGuesses this is used to print the wrong guesses
	 */
	public static void printIfWin(String secretPhrase, int wrongGuesses) {
		System.out.println("Number of wrong guesses so far: " + wrongGuesses);
		System.out.println("The phrase is " + secretPhrase);
		System.out.println("You win!!");
	}

	/*
	 * this is called when player loses and prints the losing output
	 * 
	 * @param secretPhrase this is used to print the secret phrase
	 * 
	 * @param wrongGuesses this is used to print the wrong guesses
	 */
	public static void printIfLose(String secretPhrase, int wrongGuesses) {
		System.out.println("Number of wrong guesses so far: " + wrongGuesses);
		System.out.println("You lose. The secret phrase was " + secretPhrase);
	}

	/*
	 * this function is used to check if player wins or lose and prints out based on
	 * that
	 * 
	 * @param revealedWord this is used to check if win or lose
	 * 
	 * @param secretPhrase this is used to check if win or lose
	 * 
	 * @param wrongGuesses this is used to check if win or lose
	 */
	public static void printCheckWinOrLose(String revealedWord, String secretPhrase, int wrongGuesses) {
		if (secretPhrase.equals(revealedWord) && wrongGuesses < 5) {
			printIfWin(secretPhrase, wrongGuesses);
		} else {
			printIfLose(secretPhrase, wrongGuesses);
		}
	}

	/*
	 * this function returns the guess after it has been validated to be valid
	 * 
	 * @param input this is used to get user guess
	 * 
	 * @param validAnswers this us used to check if the guess is a valid type of
	 * guess
	 */
	public static String getValidatedGuess(Scanner input, String validAnswers) {
		String guess = getGuess(input);
		while (!(validAnswers.contains(guess)) && guess != "-") {
			System.out.println(guess + " is not a valid guess.");
			printValidAnswers(validAnswers);
			guess = getGuess(input);
		}
		return guess;
	}

	/*
	 * this function print the current valid answers
	 * 
	 * @param validAnswers this is printed here
	 */
	public static void printValidAnswers(String validAnswers) {
		System.out.println("The letters you have not guessed yet are: ");
		System.out.println(validAnswers);
		System.out.println();
	}

	/*
	 * this function is used to get the guess of the user
	 * 
	 * @param input this used to retrieve the input of the user
	 * 
	 * @returns the guess of the user
	 */
	public static String getGuess(Scanner input) {
		System.out.print("Enter your next guess: ");
		String guess = "" + input.next().charAt(0);
		guess = guess.toUpperCase();
		System.out.println();
		return guess;

	}

	/*
	 * this function is used used to remove the guessed answer from the valid
	 * guesses
	 * 
	 * @param validGuesses this is where the guessed answer will be removed from
	 * 
	 * @param guess this is what will be removed from the valid guesses
	 * 
	 * @return valid guesses without guess
	 */
	public static String removeGuessedLetter(String validGuesses, String guess) {
		if (validGuesses.contains(guess)) {
			if (validGuesses.indexOf(guess) == 0) {
				return validGuesses.substring(3);
			} else if (validGuesses.indexOf(guess) == validGuesses.length() - 1) {
				return validGuesses.substring(0, validGuesses.indexOf(guess) - 2);
			} else
				return validGuesses.substring(0, validGuesses.indexOf(guess) - 1)
						+ validGuesses.substring(validGuesses.indexOf(guess) + 2);
		}
		return validGuesses;
	}

	/*
	 * this is used to reveal the guess if it is inside the secret phrase
	 * 
	 * @param currentSecretWord this is used to check if the guess is inside this
	 * phrase
	 * 
	 * @param currentRevealedWord this where the guess will be revealed if inside
	 * secret phrase
	 * 
	 * @param guess this is what is revealed in revealed word if in secret phrase
	 * 
	 * @returns revealed word with revealed guess
	 */
	public static String setCurrentRevealedWord(String currentSecretWord, String currentRevealedWord, String guess) {
		String testedChar = "";
		String revealedWord = currentRevealedWord;
		for (int pos = 0; pos < currentSecretWord.length(); pos++) {
			testedChar = "" + currentSecretWord.charAt(pos);
			if (testedChar.equals(guess)) {
				if (pos == 0) {
					revealedWord = guess + revealedWord.substring(1);
				} else if (pos == currentSecretWord.length() - 1) {
					revealedWord = revealedWord.substring(0, pos) + guess;
				} else {
					revealedWord = revealedWord.substring(0, pos) + guess + revealedWord.substring(pos + 1);
				}
			}
		}
		return revealedWord;

	}

}
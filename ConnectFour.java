import java.util.Scanner;

/**
 * CS312 Assignment 10.
 *
 * On my honor, David Alvarez, this programming assignment is my own work and I have
 * not shared my solution with any other student in the class.
 *
 *  email address: David.alvarez@utexas.edu
 *  UTEID: Da28443
 *  Section 5 digit ID: 50724
 *  Grader name: Divya Manohar
 *  Number of slip days used on this assignment: 1
 *
 * Program that allows two people to play Connect Four.
 */
public class ConnectFour {

	// CS312 Students, add your constants here.
	final static int ROWS = 6;
	final static int COLS = 7;
	final static char[] VALIDTYPES = { 'r', 'b' };
	final static int MAXPIECESPERCOLS = ROWS;
	final static int[] VALIDRANGE = { 1, 7 };
	final static int MIN = VALIDRANGE[0];
	final static int MAX = VALIDRANGE[1];
	final static int FINALBOARD = 0;
	final static int CURRENTBOARD = 1;
	final static int[] PLAYERNUMS = { 1, 2 };
	final static int PIECESMAX = 42;
	final static int NUMPIECESWIN = 4;

	public static void main(String[] args) {
		intro();
		// Complete this method.
		// Make and use one Scanner connected to System.in.
		Scanner key = new Scanner(System.in);
		game(key);
	}

	// CS312 Students, add your methods here.
	/*
	 * This function is used to get the name of the players
	 * 
	 * @param input this is the scanner that is used for user input
	 * 
	 * @returns array of size 2 with both names
	 */
	public static String[] getNames(Scanner input) {
		String[] names = new String[2];
		for (int name = 0; name < names.length; name++) {
			System.out.print("Player " + (name + 1) + " enter your name: ");
			names[name] = input.nextLine();
			System.out.println();
		}
		return names;
	}

	/*
	 * This create the board at its default state
	 * 
	 * @returns a char 2d array in its default state
	 */
	public static char[][] createBoard() {
		char[][] board = new char[ROWS][COLS];
		for (int currentRow = 0; currentRow < ROWS; currentRow++) {
			for (int currentCol = 0; currentCol < COLS; currentCol++) {
				board[currentRow][currentCol] = '.';
			}
		}
		return board;
	}

	/*
	 * this function is used to add a piece in board at a specific collumn
	 * 
	 * @param col this is the collumn on which the piece is added
	 * 
	 * @param turnType this defines what type of piece is added
	 * 
	 * @param board this is the board in which the piece is added
	 * 
	 * @param numOfPiecesInCols This is used to see how many pieces is in current
	 * col and determine current row
	 * 
	 * @return board with added piece
	 */
	public static void addPiece(int col, int turnType, char[][] board, int[] numOfPiecesInCols) {
		int adjustedCol = col - 1;
		int currentRow = MAXPIECESPERCOLS - 1;
		int posRow = currentRow - numOfPiecesInCols[adjustedCol];
		int typeOfAnswer = turnType - 1;
		board[posRow][adjustedCol] = VALIDTYPES[typeOfAnswer];
		numOfPiecesInCols[adjustedCol]++;
	}

	/*
	 * this function prints the board
	 * 
	 * @param board this is the board that printed
	 * 
	 * @param version this is used to determine with board header is printed
	 */
	public static void printBoard(char[][] board, int version) {
		if (version == FINALBOARD) {
			System.out.println("Final Board");
		} else if (version == CURRENTBOARD) {
			System.out.println("Current Board");
		}

		for (int numOfCol = 1; numOfCol <= COLS; numOfCol++) {
			System.out.print(numOfCol + " ");
		}
		System.out.println(" column numbers");
		for (int currentRow = 0; currentRow < ROWS; currentRow++) {
			for (int currentCol = 0; currentCol < COLS; currentCol++) {
				System.out.print(board[currentRow][currentCol] + " ");
			}
			System.out.println();
		}
		System.out.println();

	}

	/*
	 * This function is used to determine if the collumn trying to be added is full
	 * 
	 * @param col this is column that is checked
	 * 
	 * @param numOfPiecesInCol this hold number of pieces in each column
	 * 
	 * @return true or false dependent on if column is full or not
	 */
	public static boolean canAddPiece(int col, int[] numOfPiecesInCols) {
		int adjustedCol = col - 1;
		if (numOfPiecesInCols[adjustedCol] == MAXPIECESPERCOLS) {
			return false;
		}
		return true;
	}

	/*
	 * This function is used to validate if column is within valid range
	 * 
	 * @param col this is column that is checked
	 * 
	 * @returns true or false dependent on if within range
	 */
	public static boolean withinValidRange(int col) {

		if (col >= MIN && col <= MAX) {
			return true;
		}
		return false;
	}

	/*
	 * This function is used to get valid col input that is within range and not
	 * full
	 * 
	 * @param key This is used to get user input
	 * 
	 * @param player this is the name of the current player turn
	 * 
	 * @param playerNum this is the current number current player turn
	 * 
	 * @param numOfpiecesInCols this is used to check if can be added to columns
	 * 
	 * @returns column int that is validated
	 */
	public static int getValidCol(Scanner key, String player, int playerNum, int[] numOfPiecesInCols) {
		int type = playerNum - 1;
		String prompt = player + ", enter the column to drop your checker: ";
		System.out.println(player + " it is your turn.");
		System.out.println("Your pieces are the " + VALIDTYPES[type] + "'s.");
		System.out.print(prompt);
		int col = getInt(key, prompt);
		System.out.println();
		if (withinValidRange(col) == false) {
			col = validateInRange(key, prompt, col);
		} else if (canAddPiece(col, numOfPiecesInCols) == false) {
			col = validateCanAddCol(key, prompt, numOfPiecesInCols, col);
		}
		return col;
	}

	/*
	 * This function is used to get int that is within range
	 * 
	 * @param key This is used to get user input
	 * 
	 * @param prompt this is used to prompt users when error in input
	 * 
	 * @param col this is what is checked
	 * 
	 * @returns int that is within range
	 */
	public static int validateInRange(Scanner key, String prompt, int col) {
		System.out.println(col + " is not a valid column.");
		System.out.print(prompt);
		col = getInt(key, prompt);
		System.out.println();
		while (withinValidRange(col) == false) {
			System.out.println(col + " is not a valid column.");
			System.out.print(prompt);
			col = getInt(key, prompt);
			System.out.println("");
		}
		return col;
	}

	/*
	 * This function is the game function that holds the game loop and and run whole
	 * game
	 * 
	 * @param input this is used to get user input
	 */
	public static void game(Scanner input) {
		char[][] board = createBoard();
		int[] numOfPiecesInCols = new int[COLS];
		String[] playerNames = getNames(input);
		String playerName = "";
		boolean winning = false;
		int playerNum;
		char typePiece;
		int currentNumPieces = 0;
		printBoard(board, CURRENTBOARD);
		while ((winning == false && currentNumPieces != PIECESMAX)) {
			for (int player = 0; winning != true && player < PLAYERNUMS.length; player++) {
				playerNum = PLAYERNUMS[player];
				playerName = playerNames[playerNum - 1];
				typePiece = VALIDTYPES[playerNum - 1];
				playerTurn(input, playerName, playerNum, board, numOfPiecesInCols);
				winning = win(playerNum, typePiece, numOfPiecesInCols, board);
				currentNumPieces = numOfPiecesInBoard(numOfPiecesInCols);
				if (winning == false && currentNumPieces != PIECESMAX)
					printBoard(board, CURRENTBOARD);
			}
		}
		printWinner(board, playerName, winning, currentNumPieces);
	}

	/*
	 * This function does essential steps need per player turn
	 * 
	 * @param key This used to get user input
	 * 
	 * @param player this is the player name of current player
	 * 
	 * @param playerNum this is the player num of current player
	 * 
	 * @param board this is the board that is tested
	 * 
	 * @param numOfPieceInCols this is used to determine of column is full
	 */
	public static void playerTurn(Scanner key, String player, int playerNum, char[][] board, int[] numOfPiecesInCols) {
		int col = getValidCol(key, player, playerNum, numOfPiecesInCols);
		addPiece(col, playerNum, board, numOfPiecesInCols);

	}

	/*
	 * This funtion is used to get num that is not full in col
	 * 
	 * @param key this is used to get user input
	 * 
	 * @param prompt this the prompt that is used in case of error
	 * 
	 * @param numOfPiecesInCols this is used to determine row
	 * 
	 * @param col This is col that is replaced
	 */
	public static int validateCanAddCol(Scanner key, String prompt, int[] numOfPiecesInCols, int col) {
		System.out.println(col + " is not a legal column. That column is full");
		System.out.print(prompt);
		col = getInt(key, prompt);
		System.out.println("");
		if (withinValidRange(col) == false) {
			col = validateInRange(key, prompt, col);
		}
		while (canAddPiece(col, numOfPiecesInCols) == false) {
			System.out.println(col + " is not a legal column. That column is full");
			System.out.print(prompt);
			col = getInt(key, prompt);
			System.out.println("");

			if (withinValidRange(col) == false) {
				col = validateInRange(key, prompt, col);
			}
		}
		return col;
	}

	/*
	 * This is function is used to determine how many pieces are on the board
	 * 
	 * @param numOfPiecesInCols This is array that is used to determine current
	 * pieces in number
	 * 
	 * @returns sum of all pieces on board
	 */
	public static int numOfPiecesInBoard(int[] numOfPiecesInCols) {
		int sum = 0;
		for (int col = 0; col < COLS; col++) {
			sum += numOfPiecesInCols[col];
		}
		return sum;
	}

	/*
	 * This function is used to check if win down right
	 * 
	 * @param col this is col that is checked
	 * 
	 * @param row this is row that is check
	 * 
	 * @param playerTurn this is the current player turn
	 * 
	 * @param typePIece this is current valid piece
	 * 
	 * @param numOfPiecesInCols this is used to check is board empty
	 * 
	 * @param Board this is the board that is checked
	 * 
	 * @ return true or false dependent on if counter == 4
	 * 
	 */
	public static boolean winDownRight(int col, int row, int playerTurn, char typePiece, int[] numOfPiecesInCols,
			char[][] board) {
		int counter = 0;
		if (col > NUMPIECESWIN || numOfPiecesInCols[col] < NUMPIECESWIN) {
			return false;
		}
		for (int rowPos = row; rowPos < ROWS && col + 1 < COLS && counter < NUMPIECESWIN;) {
			if (board[rowPos][col] == typePiece) {
				counter++;
			} else {
				counter = 0;
			}
			if (col >= 0) {
				rowPos++;
				col++;
			}
		}
		return counter >= NUMPIECESWIN;
	}

	/*
	 * This function is used to check if win down left
	 * 
	 * @param col this is col that is checked
	 * 
	 * @param row this is row that is check
	 * 
	 * @param playerTurn this is the current player turn
	 * 
	 * @param typePIece this is current valid piece
	 * 
	 * @param numOfPiecesInCols this is used to check is board empty
	 * 
	 * @param Board this is the board that is checked
	 * 
	 * @ return true or false dependent on if counter == 4
	 * 
	 */
	public static boolean winDownLeft(int col, int row, int playerTurn, char typePiece, int[] numOfPiecesInCols,
			char[][] board) {

		if (col < 4 || row > 4) {
			return false;
		}
		int counter = 0;
		for (int rowPos = row; rowPos < ROWS && col >= 0 && counter < NUMPIECESWIN;) {
			if (board[rowPos][col] == typePiece) {
				counter++;
			} else {
				counter = 0;
			}
			if (col >= 0) {
				rowPos++;
				col--;
			}
		}
		return counter >= NUMPIECESWIN;
	}

	/*
	 * This function is used to check if win down
	 * 
	 * @param col this is col that is checked
	 * 
	 * @param row this is row that is check
	 * 
	 * @param playerTurn this is the current player turn
	 * 
	 * @param typePIece this is current valid piece
	 * 
	 * @param numOfPiecesInCols this is used to check is board empty
	 * 
	 * @param Board this is the board that is checked
	 * 
	 * @ return true or false dependent on if counter == 4
	 * 
	 */
	public static boolean winDown(int col, int row, int playerTurn, char typePiece, int[] numOfPiecesInCols,
			char[][] board) {
		int counter = 0;
		if (numOfPiecesInCols[col] < NUMPIECESWIN) {
			return false;
		}
		for (int currentRow = row; currentRow < ROWS && counter < NUMPIECESWIN; currentRow++) {
			if (board[currentRow][col] == typePiece) {
				counter++;
			} else {
				counter = 0;
			}
		}
		return counter >= NUMPIECESWIN;
	}

	/*
	 * This function is used to check if win right
	 * 
	 * @param col this is col that is checked
	 * 
	 * @param row this is row that is check
	 * 
	 * @param playerTurn this is the current player turn
	 * 
	 * @param typePIece this is current valid piece
	 * 
	 * @param numOfPiecesInCols this is used to check is board empty
	 * 
	 * @param Board this is the board that is checked
	 * 
	 * @ return true or false dependent on if counter == 4
	 * 
	 */
	public static boolean winRight(int col, int row, int playerTurn, char typePiece, int[] numOfPiecesInCols,
			char[][] board) {
		int counter = 0;

		for (int currentCol = col; currentCol < COLS && counter < NUMPIECESWIN; currentCol++) {
			if (board[row][currentCol] == typePiece) {
				counter++;
			} else {
				counter = 0;
			}
		}

		return counter >= NUMPIECESWIN;
	}

	/*
	 * This is used to check whole board for if win
	 * 
	 * @param playerTurn this is the current player turn
	 * 
	 * @param typePIece this is current valid piece
	 * 
	 * @param numOfPiecesInCols this is used to check is board empty
	 * 
	 * @param Board this is the board that is checked
	 * 
	 * @return true or false dependent on if anyone winning on board
	 */
	public static boolean win(int playerTurn, char typePiece, int[] numOfPiecesInCols, char[][] board) {
		boolean winning = false;
		for (int rowPos = 0; rowPos < ROWS; rowPos++) {
			for (int colPos = 0; colPos < COLS; colPos++) {
				winning = winChecker(colPos, rowPos, playerTurn, typePiece, numOfPiecesInCols, board);
				if (winning == true) {
					return true;
				}
			}
		}
		return false;
	}

	/*
	 * This function is used to check if win current col and row
	 * 
	 * @param col this is col that is checked
	 * 
	 * @param row this is row that is check
	 * 
	 * @param playerTurn this is the current player turn
	 * 
	 * @param typePIece this is current valid piece
	 * 
	 * @param numOfPiecesInCols this is used to check is board empty
	 * 
	 * @param Board this is the board that is checked
	 * 
	 * @ return true or false dependent on if counter == 4
	 * 
	 */
	public static boolean winChecker(int col, int row, int playerTurn, char typePiece, int[] numOfPiecesInCols,
			char[][] board) {

		return (winDownRight(col, row, playerTurn, typePiece, numOfPiecesInCols, board)
				|| winDownLeft(col, row, playerTurn, typePiece, numOfPiecesInCols, board)
				|| winRight(col, row, playerTurn, typePiece, numOfPiecesInCols, board)
				|| winDown(col, row, playerTurn, typePiece, numOfPiecesInCols, board));
	}

	/*
	 * this is used to print final board
	 * 
	 * @param board board that is printed
	 * 
	 * @param playerName name of winner that printed
	 * 
	 * @param win used to determine is win or draw
	 * 
	 * @param currentNumPiece current num of pieces in board
	 */
	public static void printWinner(char[][] board, String playerName, boolean win, int currentNumPieces) {
		if (win == true) {
			System.out.println(playerName + " wins!!\n");
		} else {
			System.out.println("The game is a draw.\n");
		}
		printBoard(board, FINALBOARD);

	}

	// Show the introduction.
	public static void intro() {
		System.out.println("This program allows two people to play the");
		System.out.println("game of Connect four. Each player takes turns");
		System.out.println("dropping a checker in one of the open columns");
		System.out.println("on the board. The columns are numbered 1 to 7.");
		System.out.println("The first player to get four checkers in a row");
		System.out.println("horizontally, vertically, or diagonally wins");
		System.out.println("the game. If no player gets fours in a row and");
		System.out.println("and all spots are taken the game is a draw.");
		System.out.println("Player one's checkers will appear as r's and");
		System.out.println("player two's checkers will appear as b's.");
		System.out.println("Open spaces on the board will appear as .'s.\n");
	}

	// Prompt the user for an int. The String prompt will
	// be printed out. key must be connected to System.in.
	public static int getInt(Scanner key, String prompt) {

		while (!key.hasNextInt()) {
			System.out.println();
			String notAnInt = key.nextLine();
			System.out.println(notAnInt + " is not an integer.");
			System.out.print(prompt);

		}
		int result = key.nextInt();
		key.nextLine();
		return result;
	}

}

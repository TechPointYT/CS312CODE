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
* 
*/
public class GuitarHero {
	final static String KEYBOARD = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
	final static int SIZE = 37;
	final static double CONCERTA = 440.0;
	final static double NOTEFACTOR = 1.05956;

	public static void main(String args[]) {

		GuitarString[] notes = new GuitarString[SIZE];
		for (int note = 0; note < SIZE; note++) {
			double frequency = CONCERTA * Math.pow(2, ((note - 24) / 12.0));
			notes[note] = new GuitarString(frequency);
		}

		final double TEXT_POS_X = .5;
		final double TEXT_POS_Y = .5;
		final double TEXT_POS_Y2 = .4;

		StdDraw.text(TEXT_POS_X, TEXT_POS_Y, "Type one of these" + KEYBOARD + " to play a note!");
		StdDraw.text(TEXT_POS_X, TEXT_POS_Y2, "The range of the notes are from 110Hz - 880Hz\n ");

		play(notes);

	}

	/*
	 * this function is used to to run the system loop that detects if key that is
	 * equal to valid note
	 * 
	 * @param notes this is all the notes that are used to be play
	 */
	private static void play(GuitarString[] notes) {
		int index = 0;
		while (true) {

			if (StdDraw.hasNextKeyTyped()) {

				char key = StdDraw.nextKeyTyped();
				index = KEYBOARD.indexOf(key);
				if (index != -1)
					notes[index].pluck();

			}

			double sample = calcSupPos(notes);

			StdAudio.play(sample);

			updateTics(notes);
		}

	}

	/*
	 * this function is used to calculate super position by adding sum of each note
	 * sample in notes array
	 * 
	 * @param notes this is used to determine super position
	 */
	private static double calcSupPos(GuitarString[] notes) {
		double sum = 0;
		for (int note = 0; note < SIZE; note++) {
			sum += notes[note].sample();

		}
		return sum;
	}

	/*
	 * This function is used to update all tics in all notes
	 * 
	 * @param notes all tic in this array are updated
	 */
	private static void updateTics(GuitarString[] notes) {
		for (int note = 0; note < SIZE; note++) {
			notes[note].tic();
		}
	}

}

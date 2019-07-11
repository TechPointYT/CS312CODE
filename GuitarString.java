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
public class GuitarString {
	private RingBuffer rb;
	private double N;
	private int SAMPLINGRATE = 44100;
	private double frequency;
	private static final double MIN = -.5;
	private static final double MAX = .5;
	private int timesTic = 0;
	private static final double ENERGYDECAYFACTOR = .994;

	/*
	 * this constructor is for a singular frequency and sets initial state for queue
	 * 
	 * @param frequency this is used to calculate capacity of ringBuffer
	 */
	public GuitarString(double frequency) {
		this.frequency = frequency;
		N = Math.ceil(SAMPLINGRATE / this.frequency);
		int convertedN = (int) N;
		rb = new RingBuffer(convertedN);
		for (int index = 0; index < convertedN; index++) {
			rb.enqueue(0.0);
		}
	}

	/*
	 * This constructor is with a double array
	 * 
	 * @param init this is used to calculate capacity and set the initial state of
	 * ringbuffer
	 */
	public GuitarString(double[] init) {
		int convertedN = init.length;
		rb = new RingBuffer(convertedN);

		for (int index = 0; index < convertedN; index++) {
			rb.enqueue(init[index]);
		}
	}

	/*
	 * This function is used to enqueue and dequeue Random value between -.5 and .5
	 * into the ringbuffer
	 * 
	 */
	public void pluck() {
		double randNum = 0;
		for (int element = 0; element < (int) N; element++) {
			randNum = Math.random() * (MAX - MIN) + MIN;
			rb.dequeue();
			rb.enqueue(randNum);
		}

	}

	/*
	 * this function is used to enqueue the average of firstelement and first sample
	 * * EnergyDECAYFACTOR into the array
	 */
	public void tic() {
		timesTic++;
		double firstSample = rb.dequeue();
		double nextSample = rb.peek();
		double average = .5 * (firstSample + nextSample);
		double newElement = ENERGYDECAYFACTOR * average;
		rb.enqueue(newElement);
	}

	/*
	 * this function returns the peek of the ringBuffer
	 */
	public double sample() {
		return rb.peek();
	}

	/*
	 * this returns the number of time the tic function has been called
	 */
	public int time() {
		return timesTic;
	}
}

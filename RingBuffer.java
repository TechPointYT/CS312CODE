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
public class RingBuffer {
	private double[] ringBuffer;
	private int size;
	private int first = 0;
	private int last = 0;
	private double elementOut ;
	private int capacity;
	
	/*this constructor is used to create a queue from a given size of queue
	 * @param capacity this is the size of the queue
	 */
	public RingBuffer(int capacity) {
		this.capacity = capacity;
		this.size = 0;
		this.ringBuffer = new double[capacity];	
		
	}
	/*this function returns the dynamic size of the queue
	 * @return size this returns dynamic size of queue
	 */
	public int size() {
		return size;
	}
	/*This function return if the queue is empty
	 * @returns true or false based on if size == 0
	 */
	public boolean isEmpty() {
		return this.size == 0;
	}
	/*This function checks if the queue is full
	 * @returns true or false based on if the size == capacity
	 */
	public boolean isFull() {
		return this.size == this.capacity;
	}
	/*This function is used to add a new element to the Queue
	 * @param x this is the double value that is added into the queue
	 */
	public void enqueue(double x) throws IllegalStateException {
		if(isFull()) {
			throw new IllegalStateException();
		}else {
			ringBuffer[this.last] = x;
			this.size ++;
			last = (last + 1) % capacity;
			
		}
	}
	/* This function is used to return the first value in the queue and delete the first element in the queue
	 * 
	 */
	public double dequeue() throws IllegalStateException {
		if(isEmpty()) {
			throw new IllegalStateException();
		}else {
			this.elementOut = ringBuffer[this.first];
			ringBuffer[this.first] = 0;
			this.size --;
			first = (first + 1) % capacity;
		}
		return elementOut;
	}
	/* This function is used to return the first value in the queue
	 * 
	 */
	public double peek() {
		if(isEmpty()) {
			throw new IllegalStateException();
		}else {
			return ringBuffer[this.first];
		}
	}
	/*This function is used to format the String output of this object
	 * @returns outputed string
	 */
	public String toString() {
		 if(isEmpty()) {
			return "[]";
		}
		 String output = "[";
		 int pos = first;
		 for(int index = 0; index < size  ; index++) {
			 
			 output += ringBuffer[pos];
			 if(index < size - 1)
				 output += ", ";
			 pos = (pos + 1) % capacity;
		 }
			 
			 output += "]";
		return output;
	}
}

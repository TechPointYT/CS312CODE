public class Stack {
	private int capacity;
	private int top = -1;
	private int[] stack;
	private int size = 0;
	
	public Stack(int capacity) {
		this.capacity = capacity;
		this.stack = new int[capacity];
	}
	
	public boolean isEmpty() {
		return top == -1;
	}
	public int size() {
		return size;
	}
	
	public void push(int element) {
		
		if( size >= capacity  ) {
			System.out.println("Stack overflow");
		}else {
			top++;
			stack[top] = element;
			size++;
		}
	}
	
	public int pop() {
		if(top == -1 ) {
			System.out.println("Stack underflow");
			return 0;
		}
		int element = stack[top];
		top --;
		size --;
		return element;
		
	}
	
	public String toString() {
		String currentStack = "[";
		if(top == -1) {
			return "[]";
		}else if(top == 0) {
			return "[" + stack[top] + "]";
		}
		for(int pos = 0; pos <= top; pos ++) {
			currentStack += stack[pos];
			if(pos < top) {
				currentStack += ",";
			}
		}
		currentStack += "]";
		return currentStack;
	}
}

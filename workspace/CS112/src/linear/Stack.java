package linear;

import java.util.NoSuchElementException;

public class Stack <T> {

	Node<T> head;
	
	public boolean isEmpty(){
		
		if (head == null)
			return true;
		else
			return false;
	}
	
	public void push (T data){
		
		Node<T> n = new Node<T>(data);
		n.next = head;
		head = n;
	}
	public T pop(){
		
		if (head == null)
			throw new NoSuchElementException();
		T tmp = head.data;
		
		head=head.next;
		
		return tmp;
		
	}
	
	public T peek(){
		
		if (head == null) return null;
		
		return head.data;
	}
	public int postfix(){
		Stack <int> numbers = new stack() <int>;
		
	}
}

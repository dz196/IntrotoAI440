package linear;
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class ALStack {

	private ArrayList<T> list;
	
	public ALStack(){
		
		list = new ArrayList<T>(10);
	}
	
	public boolean isEmpty(){
		
		return list.isEmpty();
		
	}
	
	public void Push(T data){
		
		list.add(data);
	}
	
	public T Pop(){
		if (list.size() == 0) throw new NoSuchElementException();
		return list.remove(list.size()-1);
		
	}
	
	public Peek()
}

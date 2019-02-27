package linear;

public class CLL {

	private StringNode tail;
	
	public void addToFront(String data){
		
		StringNode n = new StringNode(data);
		
		if (tail == null){
			n.next = n;
			tail = n;
		}
		else{

		n.next = tail.next;
		tail.next = n;
		
		}
	}
	public void addToRear(String data){
		
		StringNode n = new StringNode(data);
		
		if (tail == null){
			n.next = n;
			tail = n;
		}
		else{

		n.next = tail.next;
		tail.next = n;
		tail = n;
		
		}
	}
	public boolean search(String target){
		
		for (StringNode tmp = tail.next ; tmp != tail; tmp=tmp.next)
		{
			if (tmp.data.equals(target))
				return true;
		}
		
		if (tail.data.equals(target)) return true;
		
		return false;
	}
}

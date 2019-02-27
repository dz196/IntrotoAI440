package linear;

import java.util.NoSuchElementException;

public class LinkedList <G>{

	private Node<G> head;
	private int length;
	
	public LinkedList(){
		
		head = null; 
		length = 0;
		
	}
	public G get(int x){
		
	}
	
	public void deleteFromFront() throws DavidsCoolException{
		if (head != null){
			head = head.next;
			
		}
		else{
			
			throw new DavidsCoolException(); // unhandled exception
		}
	}
	
	//addToFront
	public void addToFront(String data){
		
		StringNode n=new StringNode(data);
		n.next = head;
		
		head = n;
		length++;
		
	}
	//addAfter
	public void addAfter( String s, String target){
		
		for (StringNode tmp = head; tmp != null; tmp=tmp.next){
			if (tmp.data.equals(target))
			{
				StringNode n=new StringNode(s);
				n.next = tmp.next;
				tmp.next = n;
				length++;
			}
		}
			
	}
	//addToRear
	
	//get
	
	public String get (int x){
		
		return null;
		
	}
	//search
	public boolean search(String target) {
		
		//stub for actual recursive call
		
		return search(head,target);
		
	}
	private boolean search( StringNode n, String target){
		if ( n == null)
		{
			return false;
		}
		if (n.data.equals(target)){
			return true;
		}
		return search( n.next, target);
		
		/*Node tmp = head;
			while (tmp != null){
			if (tmp.data == target){
				return true;
			}
			tmp = tmp.next;
		}
		
		return false;*/
	}
	
	//delete
	
	//toString
	
	//equals
	
	//set
	
	
	public static void main (String[] args){
		
		LinkedList<Integer`> l=new LinkedList();
		
		try{
			Integer.parseInt("fx");
			l.deleteFromFront();
		
			except = false;
		}
		catch( DavidsCoolException e){ // e points to the exception in DeleteFromFront
			//e.printStackTrace();
		}
		catch( NumberFormatException n){
			
		}
		catch (Exception f){
			
		}
	}
	http://marketplace.eclipse.org/marketplace-client-intro?mpc_install=1403812
	
}

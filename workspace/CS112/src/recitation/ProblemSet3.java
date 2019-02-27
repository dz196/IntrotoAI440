package recitation;

public class ProblemSet3 {

	public static DLLNode reverse(DLLNode front){
		
		if (front == null)
			return null;
		if (front.next == null)
			return front;
		for (DLLNode tmp = front.next; tmp.next != null; tmp = tmp.next){

			if (tmp.next == null){
				
				DLLNode previous = tmp.prev;
				for (DLLNode tmp2= tmp; tmp.next != null; tmp2 = tmp2.next ){
					tmp2.prev = tmp2.next;
					tmp2.next = previous;
					if (previous == null){
						return tmp;
					}
					previous = previous.prev;
				}
			}
		}
		return null;	
		
	}
	
	public static void main(String[] args)
	{
		
	}
}

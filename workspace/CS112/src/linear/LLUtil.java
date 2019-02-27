package linear;

public class LLUtil {
	
	public static Node addToFront( Node head, int data){
		Node n = new Node(data);
		
		n.next = head;
		return n;
	}
	
	public static boolean search(Node head, int target){
		Node tmp = head;
		
		while (tmp != null){
			if (tmp.data == target){
				return true;
			}
			tmp = tmp.next;
		}
		
		return false;
	}
	
	public static Node deleteFromFront(Node head){
		if (head != null)
			return head.next;
		return null;
	}

	public static Node deleteFirstTarget2(Node head, int target){
		if (head == null)
			return null;
		
		if (head.data == target)
			return head.next;
		
		for ( Node tmp = head; tmp.next != null; tmp=tmp.next){
			if (tmp.next.data == target)
			{
				tmp.next=tmp.next.next;
				return head;
			}
		}
		
		return head;
	}
	public static Node deleteFirstTarget(Node head, int target){
		
		if (head == null)
			return null;
		if (head.data == target)
			return head.next;
		Node prev = head;
		for ( Node tmp = head.next; tmp != null; tmp=tmp.next){
			if (tmp.data == target)
			{
				prev.next = tmp.next;
				return head;
			}
			prev = prev.next; // prev = tmp; works as well
		}
		return head;
		
	}
	// Instead of deleting the first target, delete every occurrence
	// Special Case: Two nodes that are the same next to each other
	
	//delete Every Other item
	
	public static Node deleteEveryOther(Node head){
		
		for (Node tmp = head.next; )
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Node head = new Node(1);
		
		head = addToFront(head, 3);
		head = addToFront(head, 0);
		head = addToFront(head, 8);
		
		// 8->0->3->1
		
		head = deleteFromFront(head);
		
		// 0->3->1
	}

}

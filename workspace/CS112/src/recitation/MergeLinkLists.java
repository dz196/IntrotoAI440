package recitation;

public class MergeLinkLists {

	public static Node merge(Node L1, Node L2){
	/*	if (L1.data > L2.data){
			Node tmp = L2;
			return merge(L1, L2.next);
		}
		else if (L2.data < L1.data){
			Node tmp = L1;
			return merge(L1.next, L2);
		}
		else {
			tmp.next = L1;
			tmp.next.next = L2;
			return merge(L1.next, L2.next);
		}
	}*/
		if (L1 == null && L2 == null)
			return null;
		if (L1 == null && L2 != null)
			return L2;
		if (L1 != null && L2 == null)
			return L1;
		if (L1.data > L2.data){
			L2.next = merge(L1, L2.next);
			return L2;
			
		}
		else if (L1.data < L2.data){
			
			L1.next = merge(L1.next, L2);
			return L1;

		}
		else {
			
			return L1;
		}

		}
	
}

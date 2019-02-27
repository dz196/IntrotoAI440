package recitation;

public class ProblemSet8 {

	class BTNode<T>{
		T data;
		BTNode<T> left, right;
		BTNode(T data, BTNode<T> left, BTNode<T> right) {
			this.data = data;
			this.left = left;
			this.right = right;
		}
	}
	
	public static <T> boolean isomorphic (BTNode<T> T1, BTNode<T> T2)
	{
		if (T1 == null && T2 == null)
			return true;
		else if (T1 == null && T2 != null)
			return false;
		else if (T1 != null && T2 == null)
			return false;
		if (isomorphic(T1.left, T2.left) == false)
			return false;
		return isomorphic(T1.right, T2.right);
	}
	
}

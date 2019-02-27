package recitation;

public class BSTNode {

	public static <T extends Comparable <T>> void reverseKeys(BSTNode<T> root)
	{
		if (root == null)
		{
			return null;
		}
		// top to bottom; if you move this to below the return statements, it becomes flipped bottom to top
		BSTNode tmp = root.left;
		root.left = root.right;
		root.right = tmp; 
		return reverseKeys(root.left);
		return reverseKeys(root.right);
	}
	public static <T extends Comparable<T>> T kthLargest(BSTNode<T> root, int k )
	{
		if (k == rightsize+1 )
		{
			return root.data;
		}
		else if (k > rightsize +1)
		{
			return kthLargest(root.left, k - rightsize - 1);
		}
		else if (k < rightsize +1)
		{
			return kthLargest(root.right, k);
		}
		
	}
}

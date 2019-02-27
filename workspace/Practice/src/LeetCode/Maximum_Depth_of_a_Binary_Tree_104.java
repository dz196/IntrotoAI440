package LeetCode;

public class Maximum_Depth_of_a_Binary_Tree_104 {
	public class TreeNode {
		 int val;
		 TreeNode left;
		 TreeNode right;
		 TreeNode(int x) { val = x; }
	}
    public int maxDepth(TreeNode root) {
        if (root == null){
        	return 0;
        }
        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }
}

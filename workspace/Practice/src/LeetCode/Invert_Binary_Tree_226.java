package LeetCode;

import java.util.LinkedList;
import java.util.Queue;

public class Invert_Binary_Tree_226 {
	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x) { val = x;}
	}
    public TreeNode invertTree(TreeNode root) {
    	if (root == null){
    		return null;
    	}
        Queue <TreeNode> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()){
        	TreeNode n = q.remove();
        	TreeNode tmp = n.left;
        	n.left = n.right;
        	n.right = tmp;
        	if (n.left != null){
        		q.add(n.left);
        	}
        	if (n.right != null){
        		q.add(n.right);
        	}
        }
        return root;
    }
}

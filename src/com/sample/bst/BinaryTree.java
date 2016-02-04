/**
 * 
 */
package com.sample.bst;

/**
 * @author suresh
 *
 */
public class BinaryTree  {
	
	public static <T> boolean isBalanced(Node<T> node) {
		// empty tree
		if(node == null) return true;
		
		// check the length of subtrees
		int ls = height(node.left);
		int lr = height(node.right);
		
		if(Math.abs(ls - lr) <= 1 &&
				isBalanced(node.left) &&
				isBalanced(node.right))
		{
			// balanced tree
			return true;
		}

		// it's not a balanced tree		
		return false;		
	}
	
	public static <T> int height(Node<T> node) {
		if(node == null) return 0;
		else return 1 + Math.max(height(node.left), height(node.right));
	}
	
	
}


class Node<T> {
	
	T data;
	Node<T> left;
	Node<T> right;
	
	public Node(T data) {
		this.data = data;
		this.left = this.right = null;
	}
}
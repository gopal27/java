/**
 * 
 */
package com.sample.bst;

/**
 * @author suresh
 *
 */
public class MainBinaryTree {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		// construct a test tree
		Node<Integer> tree = new Node<>(10);
		tree.left = new Node<>(9);
		tree.right = new Node<>(11);
		
		tree.left.left = new Node<>(1232);
		tree.left.right = new Node<>(2132);
		
		tree.left.right.left = new Node<>(5000);
		
		System.out.println("Is Balanced:" + BinaryTree.isBalanced(tree));
		
		tree.right.left = new Node(5700);
		tree.right.right = new Node(34334);
		tree.right.right.left = new Node(34334);
		
		System.out.println("Is Balanced:" + BinaryTree.isBalanced(tree));
		

	}

}

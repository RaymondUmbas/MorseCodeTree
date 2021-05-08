package edu.miracosta.cs113;

import java.util.Scanner;

public class BinaryTree<T> {
	protected static class Node<T>
	{
		protected T data;
		protected Node<T> left;
		protected Node<T> right;

		public Node(T data)
		{
			this.data = data;
			left = null;
			right = null;
		}
		public String toString ()
		{
			return data.toString();
		}
	}
	
	protected Node<T> root;
	/* Constructors */
	//No Parameter Constructor
	public BinaryTree() 
	{
		 root = null;
		 
	}
	//Node parameter Constructor
	protected BinaryTree(Node<T> root) 
	{
		this.root = root;
	}
	//Data parameter
	protected BinaryTree(T data) 
	{
		this.root = new Node<T>(data);
	}
	//Full Constructor 
	public BinaryTree(T data, BinaryTree<T> leftTree, BinaryTree<T> rightTree) 
	{
		root = new Node<>(data);
		if (leftTree != null) 
		{
			root.left = leftTree.root;
		} 
		else 
		{
			root.left = null;
		}
		
		if (rightTree != null) 
		{
			root.right = rightTree.root;
		} 
		else
		{
			root.right = null;
		}
	}
	
	/* Get Subtrees*/
	public BinaryTree<T> getLeftSubtree() 
	{
		if (root != null && root.left != null) 
		{
			return new BinaryTree<>(root.left);
		} 
		else
		{
			return null;
		}
	}
	
	public BinaryTree<T> getRightSubtree() 
	{
		if (root != null && root.right != null) 
		{
			return new BinaryTree<>(root.right);
		} 
		else
		{
			return null;
		}
	}
	
	public boolean isLeaf() 
	{
		 return (root.left == null && root.right == null);
	}
	
	public String toString() 
	{
		 StringBuilder sb = new StringBuilder();
		 toString(root, 1, sb);
		 return sb.toString();
	}
	
	private void toString(Node<T> node, int depth, StringBuilder sb) 
	{
		for (int i = 1; i < depth; i++) 
		{
			sb.append(" ");
		}
		
		if (node == null) 
		{
			sb.append("null\n");
		} 
		else 
		{
			sb.append(node.toString());
			sb.append("\n");
			toString(node.left, depth + 1, sb);
			toString(node.right, depth + 1, sb);
		}
	}
	public T getData() {
		return root.data;
		
	}
	
	
	public static BinaryTree<String> readBinaryTree(Scanner scan) 
	{
		// Read a line and trim leading and trailing spaces.
		String data = scan.nextLine().trim();
		if (data.equals("null"))
		{
			return null;
		} 
		else 
		{
			BinaryTree<String> leftTree = readBinaryTree(scan);
			BinaryTree<String> rightTree = readBinaryTree(scan);
			return new BinaryTree<>(data, leftTree, rightTree);
		}
	}
	
	//Adding Nodes to Tree
	public void addLeft(T data) {
		
		root.left = new Node<T>(data);
	}
	
	public void addRight(T data) {
		root.right = new Node<T>(data);
	}
	
}

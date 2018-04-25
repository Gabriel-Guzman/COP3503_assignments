package main;

public class Node {
	
	public Node(int data, char character) {
		this.data = data;
		this._char = character;
		this.left = this.right = null;
	}
	
	public Node(int data) {
		this.data = data;
		this.left = this.right = null;
	}

	public int data() {
		return this.data;
	}
	
	public char _char() {
		return this._char;
	}
	
	private int data;
 	private char _char;

 	Node left;
 	Node right;
}
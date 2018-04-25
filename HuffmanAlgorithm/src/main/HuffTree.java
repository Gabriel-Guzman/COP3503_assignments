package main;

public class HuffTree {
	public Node head;
	
	public Character getCharAt(String path) throws Exception {
		if (head == null) return null;
		
		Node temp = head;
		
		for(char c : path.toCharArray()) {
			if (c != '1' && c != '0') {
				throw new Exception("Character code can only contain 0's and 1's");
			}
			
			if (temp.right == null && temp.left == null) return temp._char();
			
			switch (c) {
			case '1': temp = temp.right;
				break;
			case '0': temp = temp.left;
				break;
			}
			
			// TODO actually return your Character and should we handle paths to null children
		}
		
		return temp._char();
	}
}

package main;

public class HuffTree {
	public Node head;
	
	// Returns the Character at a certain path.
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
		}
		
		return temp._char();
	}
	
	// This function encodes char c using the HuffTree "this"
	public String encodeChar(char c) {
		Node temp = head;
		
		if(temp == null) return null;
		
		String result = this._findChar(c, head, "");
		if(result == "x") return null;
		
		return result;
	}
	
	// Recursively asks the left and right children if they have 'c'.
	// Returns either the path of 'c' or 'x' if 
	private String _findChar(char c, Node node, String currentPath) {
		if (node.left == null && node.right == null) {
			if (node._char() == c) return currentPath;
			return "x"; // "x" is the sentinel value for _findChar to stop
		}
		
		String leftResult = _findChar(c, node.left, currentPath + "0");
		String rightResult = _findChar(c, node.right, currentPath + "1");
		
		if (leftResult != "x") {
			return leftResult;
		}
		
		if (rightResult != "x") {
			return rightResult;
		}
		
		return "x";
	}
	
	// Traverse the string 'code' from left to right, restarting at root every time a leaf is hit.
	public String decode(String code) throws Exception {
		if (head == null) return null;
		String result = "";
		
		Node temp = head;
		
		for(char c : code.toCharArray()) {
			if (c != '1' && c != '0') {
				throw new Exception("Character code can only contain 0's and 1's");
			}
			
			switch (c) {
			case '1': temp = temp.right;
				break;
			case '0': temp = temp.left;
				break;
			}
			
			if (temp.right == null && temp.left == null) {
				result += temp._char();
				temp = head;
			}
		}
		
		return result;
	}
}

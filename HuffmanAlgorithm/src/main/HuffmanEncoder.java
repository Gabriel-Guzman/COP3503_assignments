package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;
import java.util.PriorityQueue;


class _Comparator implements Comparator<Node> {
 public int compare(Node x, Node y)
 	{
	 	return x.data() - y.data();
 	}
}

public class HuffmanEncoder implements HuffmanCoding {
	
	@Override
	public String getFrequencies(File inputFile) throws FileNotFoundException {
		String result = "";
		int i;
		try {
			// Creating an array the size of the largest extended ascii code.
			// also the language spec guarantees that it will be initialized to 0
			int chars[] = new int[255];
			
			FileReader fr = new FileReader(inputFile);
			
			// Building the array so that the index = ascii value, and chars[index] is the count of that char
			while ((i=fr.read()) != -1)
				chars[i]++;
			
			fr.close();
			
			for (int j = 0; j < 255; j++) {
				if(chars[j] > 0) result += (char) j + " " + chars[j] + "\n";
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(0);
		}
		
		return result;
	}
	
	private int[] _getFrequencies(File inputFile) throws FileNotFoundException, Exception {
		int i;
		int chars[] = new int[255];
		
		
		FileReader fr = new FileReader(inputFile);
		
		
		// Creating an array the size of the largest extended ascii code.
		// also the language spec guarantees that it will be initialized to 0
		
		
		// Building the array so that the index = ascii value, and chars[index] is the count of that char
		while ((i=fr.read()) != -1) {
			chars[i] = chars[i] + 1;
		}
		
		fr.close();
		
		return chars;
	}
	
	private PriorityQueue<Node> _pqFromArray(int[] arr) {
		PriorityQueue<Node> pq = new PriorityQueue<Node>(255,  new _Comparator());
		
		// Note: For the input arr, index = ascii value, arr[index] = count
		// Loop through array, for every char with count > 0, add it to the priority queue as a Node
		for (int i = 0; i < 255; i++) {
			if(arr[i] > 0) {
				pq.add(new Node(arr[i], (char) i));
			}
		}
		
		return pq;
	}

	@Override
	public HuffTree buildTree(File inputFile) throws FileNotFoundException, Exception {
		int[] frequencies = this._getFrequencies(inputFile);
		
		PriorityQueue<Node> pq = _pqFromArray(frequencies);
		// TODO: Begin build process https://www.geeksforgeeks.org/greedy-algorithms-set-3-huffman-coding/
		
		Node root = null;
		
		while (pq.size() > 1) {
			Node left 	= pq.poll();
			Node right 	= pq.poll();
			
			Node newParent 	= new Node(left.data() + right.data());
			newParent.left 	= left;
			newParent.right = right;
			
			pq.add(newParent);
			
			root = newParent;
		};
		
		HuffTree huff = new HuffTree();
		huff.head = root;
		
		return huff;
	}

	@Override
	public String encodeFile(File inputFile, HuffTree huffTree) throws FileNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String decodeFile(String code, HuffTree huffTree) throws Exception {

		
		return null;
	}
	
	private String pq_traverseHuffSubTree(Node node, String currentCode, PriorityQueue<String> pq) {
		String result = "";
		
		if(node.left == null && node.right == null) {
			result = node._char() + " " + currentCode + "\n";
			pq.add(result);
		} else {
			result = pq_traverseHuffSubTree(node.left, currentCode + "0", pq) + pq_traverseHuffSubTree(node.right, currentCode + "1", pq);
		}
		
		return result;
	}

	
//	private String traverseHuffSubTree(Node node, String currentCode) {
//		String result = "";
//		
//		if(node.left == null && node.right == null) {
//			result = node._char() + " " + currentCode + "\n";
//		} else {
//			result = _traverseHuffSubTree(node.left, currentCode + "0") + _traverseHuffSubTree(node.right, currentCode + "1");
//		}
//		
//		return result;
//	}

	@Override
	public String traverseHuffmanTree(HuffTree huffTree) throws Exception {
		Node root = huffTree.head;
		PriorityQueue<String> orderedStrings = new PriorityQueue<String>(new _HuffStringComparator());
		
		pq_traverseHuffSubTree(root, "", orderedStrings);
		
//		System.out.println("ORDERED STRINGS " + orderedStrings.toString());
		
		String result = "";
		while(orderedStrings.size() > 0) {
			result += orderedStrings.poll();
		}
		
		return result;
	}
}

class _HuffStringComparator implements Comparator<String> {
	public int compare(String x, String y) {
	 	return ((int) x.charAt(0) - (int) y.charAt(0));
 	}
}

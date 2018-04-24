package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;

class Node {

	int data;
 	char _char;

 	Node left;
 	Node right;
}

class _Comparator implements Comparator<Node> {
 public int compare(Node x, Node y)
 	{
	 	return x.data - y.data;
 	}
}

public class HuffTree implements HuffmanCoding {
	
	public HuffTree() {
		
	}
	
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
	
	public int[] _getFrequencies(File inputFile) throws FileNotFoundException, Exception {
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
		LinkedList<Node> tempList = new LinkedList<Node>();
		PriorityQueue<Node> pq = new PriorityQueue<Node>(255,  new _Comparator());
		int count = 0;
		
		for (int i = 0; i < 255; i++) {
			if(arr[i] > 0) {
				count++;
				Node temp = new Node();
				temp._char = (char) i;
				pq.add(temp);
			}
		}
		
		return pq;
	}

	@Override
	public HuffTree buildTree(File inputFile) throws FileNotFoundException, Exception {
		int[] frequencies = this._getFrequencies(inputFile);
		
		PriorityQueue<Node> pq = _pqFromArray(frequencies);
		// TODO: Begin build process https://www.geeksforgeeks.org/greedy-algorithms-set-3-huffman-coding/
		
	    System.out.println(pq.toString());
		return null;
	}

	@Override
	public String encodeFile(File inputFile, HuffTree huffTree) throws FileNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String decodeFile(String code, HuffTree huffTree) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String traverseHuffmanTree(HuffTree huffTree) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}

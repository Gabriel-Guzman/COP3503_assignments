package main;

import java.io.File;
import java.io.FileNotFoundException;

public class Main {
	public static void main(String[] args) throws Exception {
		
		File file = new File("C:\\Users\\gabe1\\Documents\\School\\COP3503\\COP3503_assignments\\HuffmanAlgorithm\\src\\input.txt");
		
		HuffmanEncoder huff = new HuffmanEncoder();
		System.out.println(huff.traverseHuffmanTree(huff.buildTree(file)));
		
	}
}

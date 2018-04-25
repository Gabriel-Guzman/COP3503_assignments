package main;

import java.io.File;
import java.io.FileNotFoundException;

public class Main {
	public static void main(String[] args) throws Exception {
		
		File file = new File("/Users/gabe/Documents/School/COP3530-1/eclipse_workspace/HuffmanAlgorithm/src/main/input.txt");
		
		HuffmanEncoder huff = new HuffmanEncoder();
		System.out.println(huff.traverseHuffmanTree(huff.buildTree(file)));
		System.out.println(huff.buildTree(file).getCharAt("0000001"));
		
		
	}
}

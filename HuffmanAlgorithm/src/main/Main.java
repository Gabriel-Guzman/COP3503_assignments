package main;

import java.io.File;
import java.io.FileNotFoundException;

public class Main {
	public static void main(String[] args) throws Exception {
		
		File file = new File("C:\\Users\\gabe1\\Documents\\School\\COP3503\\COP3503_assignments\\HuffmanAlgorithm\\src\\main\\input.txt");
		
		HuffmanEncoder huff = new HuffmanEncoder();
		HuffTree tree = huff.buildTree(file);
		
//		System.out.println(huff.traverseHuffmanTree(tree));
//		System.out.println(tree.decode(
//			"011011000010110111111110011101010000010011000000011110011111111101101100110010111101011001011010011000000110010001011011100010000110000110100010010000110000101101001011010011011110110011011101000010000011101111100001110100110001000100111110111010111101001101100111111011010101010001000101100010011010001100100101011001010001111110000100100010000011110101101101010001110110000010010010100010011011010011001101110111111001011000111100101101111111101000000110111011100100011001010101010010001010111001111")
//			.equals("=a=az=a=aaaaaaaQaa==wQa_aaaaQaaaaaaaa=a=aQaQ=aaa==aQ=a=QaGaQ1=a=aa1zaza=1Qzaz=a=Q=a=Qa=aaa=aQa=aa=zwaa=aaaazaa=Qa11Qaaaa=aa==aaa=Qa=aQaaaaa=a====11=a1Qa=1aQQ==aQ=1aaaaazQ1waaa==a=a==1aa=awQQ=1Qa=a=QaQa=aa=aaaaaQ=a1aaaQ=a=aaaaaaa=Ga=aa=aaQ1aQ====Q1==aaQaaa")
//		);
//		
//		char[] toTest = { 'a', 'b', 'z', 'c', 'y' };
//		
//		System.out.println("Decoding testing: ");
//		
//		for (int i = 0; i < 5; i++) {
//			System.out.println("For char: " + toTest[i]);
//			String encodedChar = tree.encodeChar(toTest[i]);
//			String decodedChar = tree.decode(encodedChar);
//			
//			System.out.println(encodedChar + "\n" + decodedChar);
//			
////			System.out.println(toTest[i] + ": " +
////					huff.buildTree(file).encodeChar(toTest[i]).equals(huff.buildTree(file).decode(huff.buildTree(file).encodeChar(toTest[i])))
////					);
//		}
		
		String encodedFile = huff.encodeFile(file, tree);
		String decodedFile = huff.decodeFile(encodedFile, tree);
		
		System.out.println("encodedFile: " + encodedFile + "\n" + "decodedFile: " + decodedFile);
		
		System.out.println("Traversing tree: \n\n" + huff.traverseHuffmanTree(tree));
		
		System.out.println("Getting frequencies: \n\n" + huff.getFrequencies(file));
	}
}

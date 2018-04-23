package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

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
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Error reading file.");
			System.exit(0);
		}
		
		for (int j = 0; j < 255; j++) {
			
		}
		
		return null;
	}

	@Override
	public HuffTree buildTree(File inputFile) throws FileNotFoundException, Exception {
		// TODO Auto-generated method stub
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

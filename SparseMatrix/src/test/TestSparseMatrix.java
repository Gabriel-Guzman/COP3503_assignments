package test;
import main.SparseMatrix;

public class TestSparseMatrix {
	public static void main(String args[]) {
		run();
	}

	public static void run() {
		// TODO: Start with toString to check for zeroes, then add.

		SparseMatrix sm = new SparseMatrix();
		p("BEGIN SPARSEMATRIX TESTING");
		p("Testing toString() on empty matrix");

		p(sm.toString().equals("") ? "OK" : "FAILED");

		p("Testing add element once.");
		sm.addElement(0, 0, 1);
		p(sm.toString().equals("0 0 1\n") ? "OK" : "FAILED");

		p("Testing adding multiple elements");
		sm.addElement(0, 0, 2);
		sm.addElement(0, 1, 2);
		sm.addElement(0, 2, 2);
		sm.addElement(1, 0, 2);
		sm.addElement(1, 1, 2);
		sm.addElement(1, 2, 2);
		sm.addElement(2, 0, 2);
		sm.addElement(2, 1, 2);
		sm.addElement(2, 2, 2);
		String correctAdd = "0 0 2\n" + 
				"0 1 2\n" + 
				"0 2 2\n" + 
				"1 0 2\n" + 
				"1 1 2\n" + 
				"1 2 2\n" + 
				"2 0 2\n" + 
				"2 1 2\n" + 
				"2 2 2\n";
		
		p(sm.toString().equals(correctAdd) ? "OK" : "FAILED");
		
		p("Testing adding multiple elements with overwrite");
		sm.addElement(0, 0, 0);
		sm.addElement(0, 1, 22);
		sm.addElement(0, 2, 2123);
		sm.addElement(1, 0, 213);
		sm.addElement(1, 1, 2333);
		sm.addElement(1, 2, 4123);
		sm.addElement(2, 0, 1231);
		sm.addElement(2, 1, 2);
		sm.addElement(2, 2, 0);
		
		sm.addElement(0, 0, 2);
		sm.addElement(0, 1, 2);
		sm.addElement(0, 2, 222);
		sm.addElement(1, 0, 4123);
		sm.addElement(1, 1, 123);
		sm.addElement(1, 2, 213);
		sm.addElement(2, 0, 4123);
		sm.addElement(2, 1, 123);
		sm.addElement(2, 2, 23);
		
		sm.addElement(0, 0, 2);
		sm.addElement(0, 1, 2);
		sm.addElement(0, 2, 2);
		sm.addElement(1, 0, 2);
		sm.addElement(1, 1, 2);
		sm.addElement(1, 2, 2);
		sm.addElement(2, 0, 2);
		sm.addElement(2, 1, 2);
		sm.addElement(2, 2, 2);
		
		correctAdd = "0 0 2\n" + 
				"0 1 2\n" + 
				"0 2 2\n" + 
				"1 0 2\n" + 
				"1 1 2\n" + 
				"1 2 2\n" + 
				"2 0 2\n" + 
				"2 1 2\n" + 
				"2 2 2\n";
		
		p(sm.toString().equals(correctAdd) ? "OK" : "FAILED");
		
		p("Number of Rows. Should be 3: " + sm.getNumRowNodes());
		
		p("Testing out of bounds addElement");
		
		try {
			sm.addElement(12, 0, 1);
			p("Invalid row: FAILED");
		} catch (Error e) {
			p("Invalid row: OK");
		}
		
		try {
			sm.addElement(1, 12, 1);
			p("Invalid row: FAILED");
		} catch (Error e) {
			p("Invalid row: OK");
		}
		
		try {
			sm.addElement(12, 12, 1);
			p("Invalid row and col: FAILED");
		} catch (Error e) {
			p("Invalid row and col: OK");
		}
		
		p("Testing add.");
		SparseMatrix addTest1 = new SparseMatrix();
		addTest1.setSize(3, 3);
		addTest1.addElement(0, 0, 1);
		addTest1.addElement(1, 1, 2);
		addTest1.addElement(2, 2, 3);

		SparseMatrix addTest2 = new SparseMatrix();
		addTest2.setSize(3, 3);
		addTest2.addElement(0, 0, 3);
		addTest2.addElement(0, 0, 2);
		addTest2.addElement(0, 0, 1);
		
		p(( (SparseMatrix) addTest1.addMatrices(addTest2)).altToString());
		
		p("Testing multiply");
		SparseMatrix aMult = new SparseMatrix(2, 3);
		SparseMatrix bMult = new SparseMatrix(3, 2);
		
		aMult.addElement(0, 0, 1);
		aMult.addElement(0, 1, 2);
		aMult.addElement(0, 2, 3);
		
		aMult.addElement(1, 0, 4);
		aMult.addElement(1, 1, 5);
		aMult.addElement(1, 2, 6);
		
		bMult.addElement(0, 0, 7);
		bMult.addElement(0, 1, 8);
		
		bMult.addElement(1, 0, 9);
		bMult.addElement(1, 1, 10);
		
		bMult.addElement(2, 0, 11);
		bMult.addElement(2, 1, 12);
		
		p("a: \n" + ( (SparseMatrix) aMult).altToString());
		p("b: \n" + ( (SparseMatrix) bMult).altToString());
		
		p(( (SparseMatrix) aMult.multiplyMatrices(bMult)).altToString().equals("58 64 \n139 154 \n") ? "OK" : "FAILED");
		
		aMult.setSize(1, 3);
		bMult.setSize(3, 4);
		
		aMult.addElement(0, 0, 3);
		aMult.addElement(0, 1, 4);
		aMult.addElement(0, 2, 2);
		
		bMult.addElement(0, 0, 13);
		bMult.addElement(0, 1, 9);
		bMult.addElement(0, 2, 7);
		bMult.addElement(0, 3, 15);
		
		bMult.addElement(1, 0, 8);
		bMult.addElement(1, 1, 7);
		bMult.addElement(1, 2, 4);
		bMult.addElement(1, 3, 6);
		
		bMult.addElement(2, 0, 6);
		bMult.addElement(2, 1, 4);
		bMult.addElement(2, 2, 0);
		bMult.addElement(2, 3, 3);
		
		p(( (SparseMatrix) aMult.multiplyMatrices(bMult)).altToString().equals("83 63 37 75 \n") ? "OK" : "FAILED");
	}

	private static void p(String string) {
		System.out.println(string);
	}
}

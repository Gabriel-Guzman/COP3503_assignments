package main;

public class SparseMatrix implements SparseInterface {

	private Row head = null;
	private int numRows = 3, numCols = 3; // The default dimensions are 3x3 since that seemed popular in the Test class you guys made
	
	public SparseMatrix(int rows, int cols) {
		this.numRows = rows;
		this.numCols = cols;
	}
	
	public SparseMatrix() {}

	@Override
	public void clear() {
		this.head = null; // And we pray that Java garbage collects
	}

	// Empties the matrix and changes the dimensions
	public void setSize(int numRows, int numCols) {
		this.clear();
		this.numRows = numRows;
		this.numCols = numCols;
	}

	// Returns the number of conceptual rows
	public int getNumRows() {
		return this.numRows;
	}
	
	// This is a helper function that returns the amount of allocated 'rows'
	public int getNumRowNodes() {
		int count = 0;
		Row temp = head;
		
		while (temp != null) {
			temp = temp.next;
			count++;
		}
		
		return count;
	}

	@Override
	public int getNumCols() {
		return this.numCols;
	}
	
	// Returns a reference to the Row at row index 'row'
	private Row referenceAt(int row) {
		if (row >= this.numRows) throw new Error("Error: Attempted to reference out of bounds Row");

		Row temp = this.head;
		
		// Temp either stops at the found row or 'null'
		while (temp != null) {
			if (temp.row() == row) {
				return temp;
			}

			temp = temp.next;
		}

		return temp;
	}

	// Sets or creates the node 
	public void addElement(int row, int col, int data) {
		if (row >= this.numRows || col >= this.numCols) throw new Error("Error: attempted to add element out of SparseMatrix bounds.");

		if (this.head == null && data != 0) {
			this.head = new Row(this.numCols, row);
			this.head.add(data, col);

			return;
		}

		Row existingRow = this.referenceAt(row);

		if (existingRow == null) { // There does not exist a row in the sparse matrix with the 'row' index equal to row

			if (data == 0) {
				return;
			} else {
				// This is the  case where we actually add a row.
				
				Row temp = head;
				while (temp.next != null) temp = temp.next;

				temp.next = new Row(this.numCols, row);
				temp.next.add(data, col);
			}

		} else { // existingRow is the reference to the found Row
			
			if (data == 0 && existingRow.isAllZeroes()) {
				
				this.removeRow(existingRow);

			} else {
				existingRow.add(data, col);
			}
		}

	}

	// Removes the Row referenced by 'ref'.
	// This method is private due to the fact that a reference to a Row can
	// and should not be publicly accessible.
	private void removeRow(Row ref) {
		Row temp = head;

		if (temp == ref) {
			// Will make head null if head is the only element
			this.head = temp.next;
			return;
		}

		while (temp.next != ref && temp.next != null) {
			temp = temp.next;
		}

		if (temp.next == null) {
			// ref does not exist in the SparseMatrix
			throw new Error("Attempted to remove row that does not exist in SparseMatrix");
		}

		temp.next = temp.next.next;
	}

	// Removes the element at the specified row and col. If the row is all zeroes, it removes it too.
	public void removeElement(int row, int col) {
		if (row >= this.getNumRows() || col >= this.getNumCols()) throw new Error("Attempted to remove element at out of bounds index from SparseMatrix.");

		Row temp = this.referenceAt(row);
		if (temp != null) {
			temp.makeZero(col);

			if (temp.isAllZeroes()) {
				this.removeRow(temp);
			}
		}
	}

	// Returns the element at the specified row, col
	public int getElement(int row, int col) {
		if (row >= this.getNumRows() || col >= this.getNumCols()) throw new Error("Attempted to get element at out of bounds index from SparseMatrix.");
		
		Row destRow = this.referenceAt(row);
		if (destRow == null) {
			return 0;
		}
		
		return destRow.at(col);
	}

	// Returns the sum of this instance of a SparseMatrix and matrixToAdd
	public SparseInterface addMatrices(SparseInterface matrixToAdd) {
		
		// Checking the dimensions
		if (matrixToAdd.getNumRows() != this.getNumRows() || matrixToAdd.getNumCols() != this.getNumCols()) {
			return null;
		}
		
		SparseMatrix resultMatrix = new SparseMatrix(this.getNumRows(), this.getNumCols());
		for (int currRow = 0; currRow < this.getNumRows(); currRow++) {
			for (int currCol = 0; currCol < this.getNumCols(); currCol++) {
				int sum = this.getElement(currRow, currCol) + matrixToAdd.getElement(currRow, currCol);
				resultMatrix.addElement(currRow, currCol, sum);
			}
		}
		
		return resultMatrix;
	}

	// Returns the product of this instance of a SparseMatrix and matrixToAdd
	public SparseInterface multiplyMatrices(SparseInterface matrixToMultiply) {
		// Setting some final variables for more legibility and safety
		final int aRows = this.getNumRows();
		final int aCols = this.getNumCols();
		final int bRows = matrixToMultiply.getNumRows();
		final int bCols = matrixToMultiply.getNumCols();
		final SparseMatrix a = this;
		final SparseMatrix b = (SparseMatrix) matrixToMultiply;
		
		// Checking if dimensions match
		if (aCols != bRows) return null;
		
		SparseMatrix c = new SparseMatrix(this.getNumRows(), matrixToMultiply.getNumCols());
		
		// Naive matrix multiplication algorithm implementation
		for (int i = 0; i < aRows; i++) {
			for (int j = 0; j < bCols; j++) {
				int sum = 0;
				for (int k = 0; k < aCols; k++) { // aCols == bRows
					sum += a.getElement(i, k) * b.getElement(k, j);
				}
				
				c.addElement(i, j, sum);
			}
		}
		
		return c;
	}

	// Returns a string representing the SparseMatrix.
	public String toString() {
		Row temp = head;
		String result = "";

		while (temp != null) {
			result += temp.toString();
			temp = temp.next;
		}

		return result;
	}
	
	public String altToString() {
		String result = "";

		for (int tempRow = 0; tempRow < this.getNumRows(); tempRow++) {
			for (int tempCol = 0; tempCol < this.getNumCols(); tempCol++) {
				result += this.getElement(tempRow, tempCol) + " ";
			}
			
			result += "\n";
		}

		return result;
	}

}

package solver;

import java.util.ArrayList;

public class DaSudoku  {

	// Variable Declaration for sudoku board 
	private int boxval;
	private int gridlen;
	int [][] dancingboard;


// Constructor to initialize the board. a method setboardcreate is called here to construct the board
	
	public DaSudoku(int width) {
		
		boxval= width;
		gridlen = boxval* boxval;
		dancingboard = new int[gridlen][gridlen];
		for (int i = 0; i < gridlen; i++) {
			for (int j = 0; j < gridlen; j++) {
				setboardcreate(i, j, 0);
			}
		}
	}

// Constructor to build board 
	public DaSudoku(int[][] b) {

		
		gridlen = b.length;
		boxval = (int) Math.sqrt(gridlen);
		
	}

// Board Filling from values passed throgh the string of array 
	public void boardcreation(ArrayList arr1,ArrayList arr2,ArrayList arr3) {
		
		for(int i=0;i<arr1.size();i++)
		{
			for(int j=0;j<arr2.size();j++)
			{
				if(i==j)
				{
					int m=(int) arr1.get(i);
					int n=(int) arr2.get(i);
					int o=(int) arr3.get(i);
					setboardcreate(m, n, o);
					
				}
		
			}
		}


	}

	// Method to solve sudoku board that will return solution
	public static DaSudoku solve(DaSudoku p) {
		DancingLinks op = new DancingLinks(p);
		op.solve();
		return op.result();
	}


// Method That create Board from based on co ordinates and corresponding values 
	public int setboardcreate(int i, int j, int val) {

		return dancingboard[i][j] = val;
	}

	// get method for sudoku board
	public int getboardcreate(int i, int j) {

		return dancingboard[i][j];
	}




// get method that will return board as a 2D array
	public int[][] getBoard() {
		return dancingboard;
	}
	
	// get method that will return box values
	public int getboxval() {
			return boxval;
		}


}
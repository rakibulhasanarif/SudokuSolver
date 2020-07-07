/*
 * @author Jeffrey Chan & Minyi Li, RMIT 2020
 */

package solver;

import grid.StdSudokuGrid;
import grid.SudokuGrid;

/**
 * Backtracking solver for standard Sudoku.
 */
public class BackTrackingSolver extends StdSudokuSolver {

	public BackTrackingSolver() {
		super(board);
	} // end of BackTrackingSolver()

	// Here Board will be copied from StdsudokuGrid and method solution is
	// implemented to solve sudoku.

	@Override
	public boolean solve(SudokuGrid grid) {

		int x1 = Integer.parseInt(StdSudokuGrid.rawtextdata.get(0));
		board = new int[x1][x1];

		for (int i = 0; i < StdSudokuGrid.sudokuboard.length; i++) {
			for (int j = 0; j < StdSudokuGrid.sudokuboard[i].length; j++) {

				board[i][j] = StdSudokuGrid.sudokuboard[i][j];

			}
		}

		int totboardlength = board.length;
		solution(board, totboardlength);
		
   	  for (int r = 0; r < board.length; r++) 
	      { 
	        for (int d = 0; d < board.length; d++) 
	        { 
	            System.out.print(board[r][d]); 
	            System.out.print(" "); 
	        } 
	        System.out.print("\n"); 
	          
	      }

		return false;
	}
	// end of solve()

	// Method Solution takes board and boardlength as parameters and checks for
	// blank spaces into the board.
	// when it finds empty space into the box it assigns value and checks for board
	// conditions
	// if assigned value is okay then it moves to next space else it recursively use
	// bactracking to find the acurat value

	public boolean solution(int boardgrid[][], int x) {
		// initializing parameters
		// emptyok is used to check for blank spaces
		// when there are missing values into the board it return false ,otherwise is
		// return true
		int row = 0;
		int column = 0;
		boolean emptyok = true;
		for (int i = 0; i < x; i++) {
			for (int j = 0; j < x; j++) {
				if (boardgrid[i][j] == 0) {
					row = i;
					column = j;
					emptyok = false;
					break;

				}
			}
			if (!emptyok) {
				break;
			}
		}
		if (emptyok) {
			return true;
		}

		for (int i = 1; i <= x; i++) {
			// safety check method is called to check for row constraints ,column
			// constraints and box constraints
			// if assigned value full fill all of those conditions, it considerd it true
			// otherwise new value is assigned recurevly using backtrak until exact value
			// found
			if (safetycheck( row, column,boardgrid, i)) {
				boardgrid[row][column] = i;
				if (solution(boardgrid, x)) {
					return true;
				} else {
					boardgrid[row][column] = 0;
				}
			}
		}

		return false;

	}

// safetycheck method checks if the assigned value full fill all of the three constaints 
	private boolean safetycheck(int row, int column,int[][] boardgrid, int i) {
		return rowcheck(row, column,boardgrid, i) && colcheck(row, column,boardgrid, i)
				&& boxcheck(row, column,boardgrid, i);
	}

	public boolean rowcheck(int row, int column,int[][] boardgrid, int i) {
		for (int a = 0; a < boardgrid.length; a++) {
			// when assigned value already exists into current row ,it will be considered as
			// false as it violates constaints
			if (boardgrid[row][a] == i) {
				return false;
			}
		}
		return true;
	}

	public boolean colcheck(int row, int column,int[][] boardgrid, int i) {
		for (int b = 0; b < boardgrid.length; b++) {
			// when assigned value already exists into current column ,it will be considered
			// as false as it violates constaints
			if (boardgrid[b][column] == i) {
				return false;
			}
		}

		return true;

	}

	public boolean boxcheck(int row, int column,int[][] boardgrid, int i) {
		int e = (int) Math.sqrt(boardgrid.length);
		int initialboxrow = row - row % e;
		int initialboxcolumn = column - column % e;

		for (int r = initialboxrow; r < initialboxrow + e; r++) {
			for (int d = initialboxcolumn; d < initialboxcolumn + e; d++) {
				// when assigned value already exists into current box ,it will be considered as
				// false as it violates constaints
				if (boardgrid[r][d] == i) {
					return false;
				}
			}
		}
		return true;

	}

} // end of class BackTrackingSolver()

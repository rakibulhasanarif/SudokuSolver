 package solver;

/*
 * @author Jeffrey Chan & Minyi Li, RMIT 2020
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import grid.Cage;

/**
 * Abstract class for common attributes or methods for solvers of Killer Sudoku.
 * Note it is not necessary to use this, but provided in case you wanted to do
 * so and then no need to change the hierarchy of solver types.
 */
public abstract class KillerSudokuSolver extends SudokuSolver {
	public KillerSudokuSolver() {
		super(board);
		// TODO Auto-generated constructor stub
	}

	int boardsize;
	int boardstart;
	int boxsize;
	String boardRow[];
	int killerboard[][];
	String val[];
	Map<List<Cage>, Integer> cageSum;
	
	
// getter and setter methods 
	public Map<List<Cage>, Integer> getCageSum() {
		return this.cageSum;
	}

	public int getBoardsize() {
		return this.boardsize;
	}

	public void setBoardsize(int boardsize) {
		this.boardsize = boardsize;
	}

// Check rows if the same number exists

	public boolean rowcheck(int row, int number) {
		for (int i = boardstart; i < boardsize; i++) {

			if (killerboard[row][i] == number) {
				return true;
			}

		}
		return false;
	}
	// Check columns if the same number exists
	public boolean colcheck(int col, int number) {
		for (int i = boardstart; i < boardsize; i++) {

			if (killerboard[i][col] == number) {
				return true;
			}

		}
		return false;
	}
	// Check boxs if the same number exists
	public boolean boxcheck(int row, int col, int number) {
		int sqrt = (int) Math.sqrt(boardsize);
		int r = row - row % sqrt;
		int c = col - col % sqrt;
		for (int i = r; i < r + sqrt; i++) {
			for (int j = c; j < c + sqrt; j++) {

				if (killerboard[i][j] == number) {
					return true;
				}

			}

		}
		return false;
	}
// Method for all condition check
	public boolean isOk(int row, int col, int number) {
		return !(rowcheck(row, number) || colcheck(col, number) || boxcheck(row, col, number));
	}
	
	// Method that will deal with the subset records

	public List<String> subsetrec(String arr[], int n, List<String> o, int sum) {
		ArrayList<String> list = new ArrayList<String>();
		// While rest of the sum is 0 all the currents elements of current subset
		if (sum == 0) {

			for (int i = 0; i < o.size(); i++) {
				list.add(o.get(i));
				list.add(" ");
			}
			return list;
		}
 
		if (n == 0) {
			return list;
		}
		// Include last element or do not include into current one 
		list.addAll(subsetrec(arr, n - 1, o, sum));
		List<String> wr = new ArrayList<String>(o);
		wr.add(arr[n - 1]);
		list.addAll(subsetrec(arr, n - 1, wr, (sum - Integer.parseInt(arr[n - 1]))));
		return list;
	}

}
// end of class KillerSudokuSolver

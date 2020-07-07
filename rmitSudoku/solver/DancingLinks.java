package solver;

import java.util.Stack;

public class DancingLinks extends DanAbstractDancingLinks {

	// variable for box size
	private int boxsi;
	// Variable for Grid Size
	private int grisi;
	// 4 constrains for Standard Sudoku
	private int constr = 4;

	// Array that will take nodes each of the column
	private Columnnode[] colhead;
	// Array that will take column nodes
	private Node[] rowhead;

	// Constructor that constructs and adjust cover matrix for dancing links apply
	public DancingLinks(DaSudoku s) {
		super(dancingboard);

		boxsi = s.getboxval();
		grisi = boxsi * boxsi;
		dancingboard = new int[grisi][grisi];
		colhead = new Columnnode[grisi * grisi * constr];
		// satisfied
		rowhead = new Node[grisi * grisi * grisi];

		Columnnode c = new Columnnode("-1");
		super.setEntryNode(c);
		headnodebuild(c);
		matcovbuild();
		supvalcheck(s.getBoard());
	}

	// Constructs column headers based on four constrains
	private void headnodebuild(Columnnode con) {
		Columnnode curr = con;
		for (int i = 0; i < constr; i++) {
			for (int j = 0; j < grisi; j++) {
				for (int k = 0; k < grisi; k++) {
					curr.setRight(new Columnnode(i + ":" + j + ":" + k));
					curr.getRight().setLeft(curr);
					curr = (Columnnode) curr.getRight();
					colhead[colindex(i, j, k)] = curr;
				}
			}
		}
		curr.setRight(con);
		con.setLeft(curr);
	}

	// Method to construct cover matrix based on four constrains
	private void matcovbuild() {
		Node[] en = new Node[colhead.length];
		for (int i = 0; i < en.length; i++) {
			en[i] = colhead[i];
		}
		int index = 0;

		for (int i = 0; i < grisi; i++) {
			for (int j = 0; j < grisi; j++) {
				for (int k = 0; k < grisi; k++) {
					Node prev = null;
					// four nodes row,col,square,value
					for (int l = 0; l < 4; l++) {
						index = columnIndex(l, i, j, k);
						Node n = new Node(colhead[index]);
						colhead[index].incrementSize();

						n.setUp(en[index]);
						n.getUp().setDown(n);
						n.setLeft(prev);
						if (prev != null)
							prev.setRight(n);

						en[index] = n;
						prev = n;
					}
					prev.setRight(en[colindex(0, i, j)]);// square node for row 0
					en[colindex(0, i, j)].setLeft(prev);
					rowhead[rowIndex(i, j, k)] = prev;
				}
			}
		}
		for (int i = 0; i < en.length; i++) {
			colhead[i].setUp(en[i]);
		}
	}

	// Method that deals with no zero values in the given puzzle and pust them into
	// stack of solution
	private void supvalcheck(int[][] bo) {
		for (int i = 0; i < grisi; i++) {
			for (int j = 0; j < grisi; j++) {
				int given = bo[i][j] - 1;
				if (given < 0) {
					continue;
				}

				super.getOutput().push(rowhead[rowIndex(i, j, given)]);
				for (int l = 0; l < 4; l++) {
					super.cover(colhead[columnIndex(l, i, j, given)]);
				}
			}
		}
	}

	// Method that deals with the solution stack and returns final solution
	public DaSudoku result() {
		Stack<Node> outres = super.getOutput();
//		int[][] initboard = new int[grisi][grisi];
		int count = 0;
		while (!outres.isEmpty() && count < grisi * grisi) {
			boolean okk = false;
			Node curr = outres.pop();
			int r = -1, c = -1, v = -1;
			while (!okk) {
				int[] i = parts(curr.getBr().getId());
				switch (i[0]) {
				case 0: // constraint: cell filled
					r = i[1]; // row id
					c = i[2]; // col id
					break;
				case 1: // constraint: row has one of each value 1..N
					r = i[1]; // row id
					v = i[2]; // value
					break;
				case 2: // constraint: column has one of each value 1..N
					c = i[1]; // col id
					v = i[2]; // value
					break;
				default: // constraint 3 is box, but the embedded information in
							// those headers
							// is useless to us, so we ignore it
					break;
				}
				if (r > -1 && c > -1 && v > -1)
					okk = true;
				curr = curr.getRight();
			}
			dancingboard[r][c] = v + 1; // 0..N-1 ==> 1..N
			count++;
		}

		return new DaSudoku(dancingboard);
	}

	// Method that deals with the values of cover matrix based on the format
	private int[] parts(String s) {
		String[] parts = s.split(":");
		int[] result = new int[parts.length];
		for (int i = 0; i < parts.length; i++) {
			result[i] = Integer.parseInt(parts[i]);
		}
		return result;
	}

	// Method to determine column header index for a given section row ,column and
	// value
	private final int columnIndex(int section, int row, int col, int val) {
		switch (section) {
		case 0:
			return colindex(section, row, col); // value section
		case 1:
			return colindex(section, row, val); // row section
		case 2:
			return colindex(section, col, val); // column section
		case 3:
			return colindex(section, ((row / boxsi) * boxsi) + (col / boxsi), val); // subsquare section

		default:
			return -1;
		}
	}

// Method that returns row index  
	private int rowIndex(int r, int c, int v) {
		return ((r * grisi + c) * grisi + v);
	}

	// Method that returns column index
	private int colindex(int section, int row, int col) {
		return ((section * (grisi * grisi)) + col + (row * grisi));
	}

}
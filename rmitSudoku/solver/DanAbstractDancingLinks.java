package solver;
import java.util.Stack;

public abstract class DanAbstractDancingLinks  {


	// Main node of matrix from where algo start to work
	private Columnnode entrynode;

	// Store the nodes in rows that solve exact cover problem 
	private Stack<Node> output = new Stack<Node>();

	// Check if solution is ok
	private boolean okay = false;
	public static int[][]dancingboard;

	public DanAbstractDancingLinks(int[][]dancingboard) {
		
	}



	// This is the main method that solve exact cover problem recursive way
	public void solve() {

		if (entrynode.getRight() == entrynode ) 
		{
			// Algorithm is stopped here and result is returned 
			okay = true;
			return;
		}
		
		else
		{
			// one column is chosen 
			Columnnode r = nextColumn();
			cover(r);

			for (Node i = r.getDown(); i != r && !okay; i = i.getDown()) 
			{
				// Line i is added to the partial solution
				output.push(i);
				// Columns are covered 
				for (Node j = i.getRight(); j != i; j = j.getRight()) 
				{
					cover(j.getBr());
				}
				// Recursive call for solution
				solve();
				if (okay)
					break;
				
				output.pop();
				// Uncover Columns 
				for (Node j = i.getLeft(); j != i; j = j.getLeft()) 
				{
					uncover(j.getBr());
				}
			}
			
 //  Uncover selected row
			uncover(r);
		}

	}

	// Method to cover a column and isolate its corresponding rows
	public void cover(Columnnode cn) {
		cn.getLeft().setRight(cn.getRight());
		cn.getRight().setLeft(cn.getLeft());
		for (Node i = cn.getDown(); i != cn; i = i.getDown()) {
			for (Node j = i.getRight(); j != i; j = j.getRight()) {
				j.getDown().setUp(j.getUp());
				j.getUp().setDown(j.getDown());
				j.getBr().decrementSize();
			}
		}
	}


	// Method to uncover a column and reinstates corresponding rows
	public void uncover(Columnnode un) {
		un.getRight().setLeft(un);
		un.getLeft().setRight(un);		
		for (Node i = un.getUp(); i != un; i = i.getUp()) {
			for (Node j = i.getLeft(); j != i; j = j.getLeft()) {
				j.getDown().setUp(j);
				j.getUp().setDown(j);
				j.getBr().incrementSize();
			}
		}
	}

	// Method to select column having fewest components rows
	public Columnnode nextColumn() {
		Columnnode n = (Columnnode) entrynode.getRight();
		for (Columnnode i = (Columnnode) entrynode.getRight(); i != entrynode; i = (Columnnode) i
				.getRight()) {
			n = (i.getSize() < n.getSize()) ? i : n;
		}
		return n;
	}


	// get method for stack that contains constrain fields to solve box
	public Stack<Node> getOutput() {
		return output;
	}

// Getter & Setter Method for EntryNode
	public void setEntryNode(Columnnode entrynode) {
		this.entrynode = entrynode;
	}

	public Columnnode getEntryNode() {
		return entrynode;
	}

}

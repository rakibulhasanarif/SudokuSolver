package solver;

public class Node  {

//	 Initializing node for right ,left,up and down position
	private Node right,left,up,down;
//	 Header node for column
	private Columnnode br;
	
	public Node() 
	{
		
		right =left=up=down= this;

	}

// Constructor For Node Creation
	public Node(Columnnode h) 
	{
		
		br = h;
		up=down=h;
		right=left=this;

	}

// Getter & Setter Method for all variables
	public Node getDown() {
		return down;
	}


	public Columnnode getBr() {
		return br;
	}


	public Node getLeft() {
		return left;
	}


	public Node getRight() {
		return right;
	}


	public Node getUp() {
		return up;
	}


	public void setDown(Node down) {
		this.down = down;
	}


	public void setBr(Columnnode br) {
		this.br = br;
	}


	public void setLeft(Node left) {
		this.left = left;
	}


	public void setRight(Node right) {
		this.right = right;
	}

	public void setUp(Node up) {
		this.up = up;
	}

	public boolean compare(Columnnode o) {
		
		return false;
	}


}

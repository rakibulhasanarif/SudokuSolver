package solver;

public class Columnnode extends Node {


	// Size of Linked List for current node
	private int size;
	private String id;

// Constructor for Columnode creation
	public Columnnode(String n) 
	{

		id = n;
	}


// Column size decrease method for covering
	public void decrementSize() {
		size--;
	}

	// Column size increase method for uncovering
	public void incrementSize() 
	{
		size++;
	}
	
	@Override 
	public boolean compare(Columnnode o) 
	{
		
		Columnnode h = (Columnnode) o;
		h.id.equals(this.id);
		
		return false;
	}
	
	// Getter and Setter method for id and size
	
	public int getSize() {
		return size;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}
	 

}

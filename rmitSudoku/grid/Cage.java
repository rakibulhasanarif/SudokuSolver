package grid;

public class Cage {
	
	public int row; 
    public int column;
    
    public Cage(int row, int column) {
        this.row = row;
        this.column = column;
    }
   
    public void setRow(int row) {
		this.row = row;
	}

	public void setColumn(int column) {
		this.column = column;
	}

    public int getColumn() {
        return this.column;
    }

    public int getRow() {
        return this.row;
    }

 

}
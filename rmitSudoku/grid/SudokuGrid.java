package grid;

/**
 * @author Jeffrey Chan & Minyi Li, RMIT 2020
 */

 import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;



/**
 * Abstract class representing the general interface for a Sudoku grid.
 * Both standard and Killer Sudoku extend from this abstract class.
 */
public abstract class SudokuGrid
{
	public static ArrayList<String> rawtextdata;
	public static ArrayList<Integer> testdata1;
	public static ArrayList<Integer> testdata2;
	public static ArrayList<Integer> testdata3;
	public static ArrayList<String> testdata4;
	public static ArrayList<String> testdata5;
	public static ArrayList<ArrayList<String> > totallist;
	public static HashMap<String,Integer> cagedata;
	public static int [][] sudokuboard;
	

    public SudokuGrid(ArrayList<String> rawtextdata,ArrayList<Integer> testdata1, ArrayList<Integer> testdata2, ArrayList<Integer> testdata3,ArrayList<String> testdata4,ArrayList<String> testdata5,ArrayList<ArrayList<String> > totallist,int [][] sudokuboard,HashMap<String,Integer> cagedata) 
    {
    	
	}


	/**
     * Load the specified file and construct an initial grid from the contents
     * of the file.  See assignment specifications and sampleGames to see
     * more details about the format of the input files.
     *
     * @param filename Filename of the file containing the intial configuration
     *                  of the grid we will solve.
     *
     * @throws FileNotFoundException If filename is not found.
     * @throws IOException If there are some IO exceptions when openning or closing
     *                  the files.
     */
    public abstract void initGrid(String filename)
        throws FileNotFoundException, IOException;


    /**
     * Write out the current values in the grid to file.  This must be implemented
     * in order for your assignment to be evaluated by our testing.
     *
     * @param filename Name of file to write output to.
     *
     * @throws FileNotFoundException If filename is not found.
     * @throws IOException If there are some IO exceptions when openning or closing
     *                  the files.
     */
    public abstract void outputGrid(String filename)
        throws FileNotFoundException, IOException;


    /**
     * Converts grid to a String representation.  Useful for displaying to
     * output streams.
     *
     * @return String representation of the grid.
     */
    public abstract String toString();


    /**
     * Checks and validates whether the current grid satisfies the constraints
     * of the game in question (either standard or Killer Sudoku).  Override to
     * implement game specific checking.
     *
     * @return True if grid satisfies all constraints of the game in question.
     */
    public abstract boolean validate();





	



} // end of abstract class SudokuGrid

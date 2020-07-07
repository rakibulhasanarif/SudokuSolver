  package grid;

/**
 * @author Jeffrey Chan & Minyi Li, RMIT 2020
 */


import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import solver.SudokuSolver;

/**
 * Class implementing the grid for Killer Sudoku. Extends SudokuGrid (hence
 * implements all abstract methods in that abstract class). You will need to
 * complete the implementation for this for task E and subsequently use it to
 * complete the other classes. See the comments in SudokuGrid to understand what
 * each overriden method is aiming to do (and hence what you should aim for in
 * your implementation).
 */
public class KillerSudokuGrid extends SudokuGrid {
	int q1 = 0;
	int q2 = 0;
	int x3=0;
    String[] values;

    public String[] getValues() {
        return this.values;
    }

    public void setValues(String[] values) {
        this.values = values;
    };
    int blocks;
    ArrayList<Cage> cages;
    ArrayList<Cage> allCages;   
    Map<List<Cage>, Integer> cageSum;

    public Map<List<Cage>, Integer> getCageSum() {
        return this.cageSum;
    }


	public KillerSudokuGrid() {
		super(rawtextdata, testdata1, testdata2, testdata3, testdata4, testdata5, totallist, sudokuboard, cagedata);
		rawtextdata = new ArrayList<String>();
		testdata1 = new ArrayList<Integer>();
		testdata2 = new ArrayList<Integer>();
		testdata3 = new ArrayList<Integer>();
		testdata4 = new ArrayList<String>();
		testdata5 = new ArrayList<String>();
		totallist = new ArrayList<ArrayList<String>>(2);
		cagedata = new HashMap<String, Integer>();
		cages = new ArrayList<Cage>();
		allCages = new ArrayList<Cage>(); 
        cageSum = new HashMap<List<Cage>, Integer>();

	}

    /* ********************************************************* */

    @Override
    public void initGrid(String filename)
        throws FileNotFoundException, IOException
    { 
    	
    	
    	Scanner s = new Scanner(new File(filename));
		while (s.hasNextLine()) {
			String line = s.nextLine();
			rawtextdata.add(line);
		}
		s.close();
		int x2 = rawtextdata.size();

		x3 = Integer.parseInt(rawtextdata.get(0));
		sudokuboard = new int[x3][x3];
		// Only Co ordinates and co responding values are stored into the arraylist
		List<String> partitiontwodata = rawtextdata.subList(3, x2);

		// Co ordinates and coresponding values are splitted and stored into array at
		// first and then arraylist
		for (int i = 0; i < partitiontwodata.size(); i++) {
			String q = partitiontwodata.get(i);
			String[] arrSplit = q.split(" ", 2);
			String a = arrSplit[0];
			String v = arrSplit[1];
			testdata4.add(a);
			testdata5.add(v);
			totallist.add(testdata4);
			totallist.add(testdata5);

		}
		
		for (int i = 0; i < testdata5.size(); i++) {
			int p = Integer.parseInt(totallist.get(0).get(i));
			String q = totallist.get(1).get(i);
			// Splitting when comma will be found
			String[] arrSplit = q.split("[\\s,]+");
			int[] arr = new int[arrSplit.length];
			for (int k = 0; k < arrSplit.length; k++) {
				arr[k] = Integer.parseInt(arrSplit[k]);
			}
			for (int k = 0; k < arr.length; k++) {
// Variable will take row and column values
				if (k % 2 == 0) {
					q1 = arr[k];
					q2 = arr[k + 1];
					sudokuboard[q1][q2] = p;
					
				}

			}


		}
		    	

            values = rawtextdata.get(1).split(" ");
            blocks = Integer.parseInt(rawtextdata.get(2));

            for (int i = 0; i <  partitiontwodata.size(); i++) {
               String[] val =  partitiontwodata.get(i).split(" ");
                int sum = Integer.parseInt(val[0]);
                for(int k = 1;k < val.length; k++){
                    
                    String[] block = val[k].split(",");

                    int m = Integer.parseInt(block[0]);
                    int n = Integer.parseInt(block[1]);
                    cages.add( new Cage(m, n));
                    allCages.add(new Cage(m,n));
                }
                 cageSum.put( cages,sum);
               
            }
            System.out.println(toString());

    
    } // end of initBoard()

    @Override
    public void outputGrid(String filename) throws FileNotFoundException, IOException {
    	if(filename!=null)
    	{
        	StringBuilder sb= new StringBuilder();
        	// This will separate output using comma in each line
        	for(int i=0;i<SudokuSolver.board.length;i++)
        	{
        		for(int j=0;j<SudokuSolver.board.length;j++)
        		{
        			sb.append(SudokuSolver.board[i][j]+"");
        			if(j<SudokuSolver.board.length-1)
        			{
        				// This will separate output using comma in each line 
        				sb.append(",");
        			}
        		}
        		sb.append("\n");
        	}
        	// File writing start
        	BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
        	writer.write(sb.toString());
        	// File Writing Stop
        	writer.close();
        	// Display of sudoku board after file writing completed
         	  for (int r = 0; r < SudokuSolver.board.length; r++) 
      	      { 
      	        for (int d = 0; d < SudokuSolver.board.length; d++) 
      	        { 
      	            System.out.print(SudokuSolver.board[r][d]); 
      	            System.out.print(" "); 
      	        } 
      	        System.out.print("\n"); 
      	          
      	      } 
    		
    	}
    	else
    	{
    		System.out.println("Please Provide a Valid File Name with valid Path");
    	}
    } // end of outputBoard()

    @Override
    public String toString() {
    	String gridrep="";
    	
		for(int i=0;i<sudokuboard.length;i++)
			
		{
			gridrep=gridrep +"|";
			for(int j=0;j<sudokuboard.length;j++)
			{
				
				gridrep=gridrep +String.format("%-5s|",sudokuboard[i][j]);
				
			}
			gridrep+="\n";
			
 			
		}
		
//        System.out.println(gridrep);

        // placeholder
        return String.valueOf(gridrep);
    } // end of toString()

    @Override
    public boolean validate() {

    	// Validation for row
    	for(int i=0; i<x3;i++)
    	{
    		for(int j=0;j<(x3-1);j++)
    		{
    			for(int j2=j + 1;j2<(x3-1);j2++)
    			{
    				if(SudokuSolver.board[i][j]==SudokuSolver.board[i][j2])
    				{
    					return false;
    				}

    			}
    		}
    	}
    	// Validation for Column	
    	for(int j=0;j< x3;j++)
    	{
    		for(int i=0;i<(x3-1);i++)
    		{
    			for(int i2=i+1; i2<x3; i2++)
    			{
    				if(SudokuSolver.board[i][j]==SudokuSolver.board[i2][j])
    				{
    					return false;
    				}

    			}
    		}
    	}
    		  
        return true;
    } // end of validate()

} // end of class KillerSudokuGrid

package grid;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import solver.SudokuSolver;

public class StdSudokuGrid extends SudokuGrid
{

	List<String> partitiontwodata=new ArrayList<String>();

  

    public StdSudokuGrid() 
    {
        super(rawtextdata, testdata1,testdata2,testdata3, testdata4, testdata5, totallist,sudokuboard, cagedata); 
        rawtextdata = new ArrayList<String>();
        testdata1 = new ArrayList<Integer>();
        testdata2=new ArrayList<Integer>();
        testdata3=new ArrayList<Integer>();
        
    } // end of StdSudokuGrid()



 int x1=0;
    @Override
    public void initGrid(String filename)
        throws FileNotFoundException, IOException
    {
        
    	// Reading From File 		
    			Scanner s = new Scanner(new File(filename));
    			while (s.hasNext()){
    			    rawtextdata.add(s.next());
    			}
    			s.close();
    			
    			
    			int x2=rawtextdata.size();
    			int x3=Integer.parseInt(rawtextdata.get(0));
    			int x4=x3+1;
    	// Creating Sublists that will hold only the co ordinates and values
    			List<String> partitiontwodata = rawtextdata.subList(x4,x2);
    	// As each row has co oridate values separating wth comma and coresponding values, comma will be eleminated and 
    	// two arraylists will hold the values for x and y co ordinate value
    	// another arraylist will hold the value for that spacific co ordinate 
    			for(int i=0;i<partitiontwodata.size();i++)
    			{
    				if(i%2==0)
    				{
    					String q=partitiontwodata.get(i);
    					String[] arrSplit = q.split(",");
    					String a = arrSplit[0];
    					String v = arrSplit[1];
    					int c=Integer.parseInt(a);
    					int d=Integer.parseInt(v);
    					testdata1.add(c);
    					testdata2.add(d);
    					
    				}
    				else
    				{
    					String q=partitiontwodata.get(i);
    					int c=Integer.parseInt(q);
    					testdata3.add(c);
    				}
    			}
    	// Sudoku board will be inistialized from the first value that was stored after reading data from file
    			
    			x1=Integer.parseInt(rawtextdata.get(0));
    			sudokuboard=new int[x1][x1];
    			
    		// To insert element into 2D Array, one arraylist will give x co ordinate value and another one will give y co ordinate value
    		// Third arraylist will give the value for that specific co ordinate	
    			
    			for(int i=0;i<testdata1.size();i++)
    			{
    				for(int j=0;j<testdata2.size();j++)
    				{
    					if(i==j)
    					{
    						int m=testdata1.get(i);
    						int n=testdata2.get(i);
    						int o=testdata3.get(i);
    						sudokuboard[m][n]=o;
    					}
    			
    				}
    			}
    			    		
    		     System.out.println(toString());   
    		    
    } // end of initBoard()
    


    @Override
    public void outputGrid(String filename)
        throws FileNotFoundException, IOException
    {
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
    	for(int i=0; i<x1;i++)
    	{
    		for(int j=0;j<(x1-1);j++)
    		{
    			for(int j2=j + 1;j2<(x1-1);j2++)
    			{
    				if(SudokuSolver.board[i][j]==SudokuSolver.board[i][j2])
    				{
    					return false;
    				}

    			}
    		}
    	}
    	// Validation for Column	
    	for(int j=0;j< x1;j++)
    	{
    		for(int i=0;i<(x1-1);i++)
    		{
    			for(int i2=i+1; i2<x1; i2++)
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

} // end of class StdSudokuGrid

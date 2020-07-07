/*
 * @author Jeffrey Chan & Minyi Li, RMIT 2020
 */

package solver;


import java.util.ArrayList;
import grid.StdSudokuGrid;
import grid.SudokuGrid;



/**
 * Dancing links solver for standard Sudoku.
 */
public class DancingLinksSolver extends StdSudokuSolver
{


    public DancingLinksSolver() 
    {
    	super(board);
    } // end of DancingLinksSolver()


    @Override
    public boolean solve(SudokuGrid grid) {
    	ArrayList<String> newlistraw = new ArrayList<>(StdSudokuGrid.rawtextdata);   	
    	ArrayList<Integer> newlist1 = new ArrayList<>(StdSudokuGrid.testdata1);
    	ArrayList<Integer> newlist2 = new ArrayList<>(StdSudokuGrid.testdata2);
    	ArrayList<Integer> newlist3 = new ArrayList<>(StdSudokuGrid.testdata3);
    	

    	int x3=Integer.parseInt(newlistraw.get(0));  
    	int x4=(int) Math.sqrt(x3);
    	
		DaSudoku s = new DaSudoku(x4);
		s.boardcreation(newlist1,newlist2,newlist3);

		DaSudoku.solve(s);
		board=new int[x3][x3];
		
		for(int i=0; i<DanAbstractDancingLinks.dancingboard.length; i++)
		{
			for(int j=0; j<DanAbstractDancingLinks.dancingboard[i].length; j++)
				
			{
				board[i][j]=DanAbstractDancingLinks.dancingboard[i][j];
			}
		}
		
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
    } // end of solve()
    
 
} // end of class DancingLinksSolver




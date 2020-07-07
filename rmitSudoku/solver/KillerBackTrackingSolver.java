package solver;

/*
 * @author Jeffrey Chan & Minyi Li, RMIT 2020
 */



import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import grid.Cage;
import grid.KillerSudokuGrid;
import grid.StdSudokuGrid;
import grid.SudokuGrid;



/**
 * Backtracking solver for Killer Sudoku.
 */
public class KillerBackTrackingSolver extends KillerSudokuSolver
{
   

    public KillerBackTrackingSolver() {
    	super();
    	boardstart = 0;

    } // end of KillerBackTrackingSolver()


    @Override
    // Method for solving sudoku
    public boolean solve(SudokuGrid grid) {

        boardsize = Integer.parseInt(KillerSudokuGrid.rawtextdata.get(0));
        killerboard = new int[boardsize][boardsize];
        board=new int[boardsize][boardsize];
        val = ((KillerSudokuGrid)grid).getValues();
        Map<List<Cage>,Integer> cagetotal = ((KillerSudokuGrid)grid).getCageSum();
        // Initialize board for solution
        for(int row = boardstart;row < boardsize; row++)
		{
			for(int col = boardstart;col < boardsize; col++){
				killerboard[row][col]= 0;
            }
        }
        
        boolean solveKillerSudoku = solvelogic(cagetotal);
        if(solveKillerSudoku)
        {
    		for (int i = 0; i < killerboard.length; i++) {
    			for (int j = 0; j < killerboard[i].length; j++) {

    				board[i][j] = killerboard[i][j];

    			}
    		}

       	  for (int r = 0; r < killerboard.length; r++) 
  	      { 
  	        for (int d = 0; d < killerboard.length; d++) 
  	        { 
  	            System.out.print(killerboard[r][d]); 
  	            System.out.print(" "); 
  	        } 
  	        System.out.print("\n"); 
  	          
  	      } 
            return true;
        }
            
        return false;
    } // end of solve()
  // Main logic for sudoku solution 
    public boolean solvelogic(Map<List<Cage>,Integer> cagetotal)
	{  
        for (Map.Entry<List<Cage>,Integer> entry : cagetotal.entrySet())  
        {
            for (int i = 0; i < entry.getKey().size(); i++) 
            {
                List<String> str = new ArrayList<String>();
                str = subsetrec(val, val.length, str, entry.getValue());

			for(int j = 0; j < entry.getKey().size(); j++)
			{  
                if(killerboard[entry.getKey().get(j).getRow()][entry.getKey().get(j).getColumn()]==0)
				{     
					for(int k = 0; k < str.size(); k++)
					{   String subNum[] = str.get(k).split(" "); 
                        for (int l= 0; l < subNum.length; l++ ){
// Checks if the number full fill row ,column and box conditions 
                            if(isOk(entry.getKey().get(j).getRow(),entry.getKey().get(j).getColumn(),
                             Integer.parseInt(subNum[l])))
	    					{   
                            	killerboard[entry.getKey().get(j).getRow()][entry.getKey().get(j).getColumn()] = Integer.parseInt(subNum[l]);
			    				// Recursive call until final
                            	if(solvelogic(cagetotal))
				    			{
					    			return true;
						    	}
							    else
							    {
							    	killerboard[entry.getKey().get(j).getRow()][entry.getKey().get(j).getColumn()] = 0;
                                    
							    }
						    }
                        }
                    }
					return false;
				}
			}
		}
		
    }
    return true;
}
} // end of class KillerBackTrackingSolver()

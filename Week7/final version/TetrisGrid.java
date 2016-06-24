/* 
* NAME: Pengbo Li
* LOGIN: cs11sdc
* ID: A53079916
*
* NAME: Kuangyi Yang
* LOGIN: cs11sgy
* ID: A53083212
*/

import java.util.ArrayList;

/**
 * A class that creats a bounded coordinate plane on which the shapes 
 * are placed.
 * @author NAME: Pengbo Li LOGIN: cs11sdc ID: A53079916 
 * @author NAME: Kuangyi Yang LOGIN: cs11sgy ID: A53083212
 * @version CSE11-Spring
 */
public class TetrisGrid 
{
    public static final int ROW_NUMBER = 20;
    public static final int COLUMN_NUMBER = 10;
    public static final int INITIAL_ROW = 0;
    public static final int INITIAL_COLUMN = 5;

    private boolean[][] grid;
    private TetrisShape shape;
    private int currentRow;
    private int currentColumn;

    /**
     * Constructor build a 10 wide x 20 high grid 
     */
    public TetrisGrid() 
    {
        grid = new boolean[ROW_NUMBER][COLUMN_NUMBER];
        shape = new TetrisShape();
        currentRow = INITIAL_ROW;
        currentColumn = INITIAL_COLUMN;
    }

    /**
     * Get an array of blocks that are occupied
     * @return an ArrayList of occupied blocks
     */
    public ArrayList<Coord> getOccupiedBlocks() 
    {
    	ArrayList<Coord> temp = new ArrayList<Coord>();
    	for (int i = 0; i < ROW_NUMBER; i++) 
        {
    		for (int j = 0; j < COLUMN_NUMBER; j++) 
            {
    			if (grid[i][j]) {
    				temp.add(new Coord(i,j));
    			}
    		}
    	}
    	for (Coord i : shape.getArray(currentRow, currentColumn)) 
        {
    		if (i.row >= 0) 
            {
    			temp.add(new Coord(i.row, i.col));
    		}
    	}
    	return temp;
    }

    /**
     * Fix the moving shape on the grid
     */
    public void setGrid() 
    {
    	for (Coord i: shape.getArray(currentRow, currentColumn)) 
        {
    		if (i.row >= 0) {
    			grid[i.row][i.col] = true;
    		}
    	}
    }

    /**
     * Generate a new shape randomly
     */
    public void addShape() 
    {
        shape = new TetrisShape();
        currentRow = INITIAL_ROW;
        currentColumn = INITIAL_COLUMN;
    }

    /**
     * Determine whether a shape intersects any occupied block in the grid 
     * or hit the bounds
     * @return true if the shape neither interset the occupied blocks
     * nor hit the bounds, false otherwise
     */
    public boolean isValid() 
    {
    	for (Coord i: shape.getArray(currentRow, currentColumn)) 
        {
    		if (i.row >= ROW_NUMBER) return false;
    		if (i.col < 0 || i.col >= COLUMN_NUMBER) return false;
    		if (i.row >= 0 && grid[i.row][i.col]) return false;
    	}
    	return true;
    }

    /**
     * Check if movement should stop and game over
     * @return true if shape hit the upperbound
     */
    public boolean isStop() 
    {
    	for (Coord i: shape.getArray(currentRow, currentColumn)) 
        {
    		if (i.row <= 0) 
            {
    			return true;
    		}
    	}
    	return false;
    }

    /**
     * Move the Tetris shape left
     */
    public void moveLeft() 
    {
    	currentColumn = currentColumn - 1;
    }
    /**
     * Move the Tetris shape right
     */
    public void moveRight() 
    {
    	currentColumn = currentColumn + 1;
    }

    /**
     * Move the Tetris shape up
     */
    public void moveUp() 
    {
    	currentRow = currentRow - 1;
    }

    /**
     * Move the Tetris shape down 
     */
    public void moveDown() 
    {
    	currentRow = currentRow + 1;
    }

    /**
     * Rotate the Tetris shape counterclockwise
     */
    public void rotateLeft() 
    {
    	shape.rotateLeft();
    }

    /**
     * Rotate the Tetris shape clockwise
     */
    public void rotateRight() 
    {
    	shape.rotateRight();
    }

    /**
     * Count the number of rows which is completed thus need to be deleted
     * @return the number of compeleted rows
     */
    public int deleteRows() 
    {
    	int temp = 0;
        for (int i = 0; i < ROW_NUMBER; i++) 
        {
            int number = 0;
            for (int j = 0; j < COLUMN_NUMBER; j++) 
            {
                if (grid[i][j]) number++;
            }
            if (number == COLUMN_NUMBER) 
            {
            	temp = temp + 1;
            	for (int l = i; l > 0; l--) 
                {
            		for (int n = 0; n < COLUMN_NUMBER; n++) 
                    {
            			grid[l][n] = grid[l - 1][n];
            		}
            	}
            	for (int n = 0; n < COLUMN_NUMBER; n++) 
                {
            		grid[0][n] = false;
            	}
            }
        }
        return temp;
    }
}

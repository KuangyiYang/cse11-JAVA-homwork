/** define a 2D array of chars as a way to make ascii art.
 * can place and clear an arbitrary 2D array of chars in the grid
 * if asked-for array fits.
 * @author KUANGYI YANG
 * @version CSE11 Spring 2015
 */
public class AsciiGrid
{
	private static char [][] grid;
	private static int[] size;
	final char empty = ' ';

	/** Constructor 
	 */
	public AsciiGrid()
	{
		grid = new char [25][40];
		for(int i = 0; i < grid.length; i++)
		{
			for(int j = 0; j < grid[0].length; j++)
			{
				grid [i][j] = empty;
			}
		}
	}
	/** Constructor 
	 * @param row number of rows in the ascii grid 
	 * @param col number of columns in the ascii grid 
	 */
	public AsciiGrid(int row, int col)
	{
		grid = new char [row][col];
		for(int i = 0; i < row; i++)
		{
			for(int j = 0; j < col; j++)
			{
				grid [i][j] = empty;
			}
		}
	}
	/** return a row x col array of the current char array  
	 * This should be a full/deep copy, not a reference to internal
	 * storage
	 * @return array of chars 
	 */
	public char [][] getChars()
	{
		if(grid.length != 0 && grid[0].length != 0)
		{
			char [][] copyArray = new char [grid.length][grid[0].length];
			for(int i = 0; i < grid.length; i++)
			{
				for(int j = 0; j < grid[0].length; j++)
				{
					copyArray[i][j] = grid[i][j];
				}
			}
			return copyArray;
		}else
		{
			return new char [0][0];
		}
	}
		
	/** 
	 * place the 2D shape in the grid at location (r,c) 
	 * only if every row of the shape fits in the grid and
	 * doesn't intersect any non-empty spaces. Otherwise don't
	 * place the shape
	 * @param shape 2D array of chars to put on the Grid
	 * @param r row in the grid where to place the first row of the shape
	 * @param c column in the grid where to place the first column of the shape
	 * @return true, if successful false otherwise
	 */
	
	public boolean placeShape(char [][] shape, int r, int c)
	{
		if( shape.length + r <= grid.length && shape[shape.length-1].length + c <= grid[0].length )
		{
			for(int i = r; i < r + shape.length; i++)
			{
				for(int j = c; j< c + shape[i-r].length ; j++ )
				{
					if(grid[i][j] == empty)
					{
						grid[i][j] = shape[i-r][j-c];
					}else
					{
						return false;
					} 
				}
			}
			return true;
		}else
		{
			return false;
		}
	}
	
	/** 
	 * clear the elements in the grid  defined by the 2D shape 
	 * starting at grid at location (r,c). The contents of the 
	 * shape are irrelevant only the dimensions of each row are used.
	 * Clear only if every row of the shape fits in the grid. 
	 * Cleared elements in the grid are set to the EMPTY char.
	 *
	 * @param shape 2D array of chars. Defines the shape to  
	 * @param r row in the grid where to start the clearing 
	 * @param c column in the grid where to start the clearing 
	 * @return true, if successful false otherwise
	 */
	public boolean clearShape(char [][] shape,int r, int c)
	{
		if( shape.length + r <= grid.length && shape[shape.length-1].length + c <= grid[0].length )
		{
			for(int i = r; i < r + shape.length; i++)
			{
				for(int j = c; j< c + shape[i-r].length; j++ )
				{
					grid[i][j] = empty;
				}
			}
			return true;
		}else
		{
			return false;
		}
	}
	
	/** Return the width and height of the grid 
	 *  @return array where index=0 is nrows, index=1 nrows 
	 */
	public int [] getSize()
	{
		size = new int[2];
		size[0] = grid.length;
		size[1] = grid[0].length;
		return size; 
	}

	/** create a nice, printable representation of the grid and
	 * filled coordinates
	 *
	 * the grid should be framed. A row of "=' (length = width of grid + 2)
	 * should be used to frame the top and bottom of the grid. The '|' should
	 * be used to frame the left and right side of each row of the grid. e.g 
	 * 1x1  empty grid      2 x 2 empty grid
	 * ===                  ====
	 * | |                  |  |
	 * ===                  |  |
	 *                      ====
	 */
	@Override
	public String toString()
	{
		String frame = "";
		for(int i = 0; i < grid[0].length+2; i++)
		{
			frame = frame + "=";
		}
		frame = frame + "\n";
		for(int j = 1; j <= grid.length; j++)
		{
			frame = frame + "|";
			for(int k = 2; k < grid[0].length+2; k++)
			{
				frame = frame + Character.toString(grid[j-1][k-2]);
			}
			frame = frame + "|" + "\n";
		}
		for(int i = 0; i < grid[0].length+2; i++)
		{
			frame = frame + "=";
		}	
		return frame;
	}
	

}
// vim: ts=4:sw=4:tw=78

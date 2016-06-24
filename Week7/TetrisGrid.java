import java.util.Random;
import java.util.ArrayList;
public class TetrisGrid
{
    private int cols;
    private int rows;

    private TetrisShape myShape;
    private ArrayList<Coord> block;
    private char [][] grid;
    
    private int[] currDirect;

    public TetrisGrid(int numRow, int numCol, ArrayList<Coord> aShape)
    {
        rows = numRow;
        cols = numCol;
        grid = new char [rows][cols];
        for(int i = 0; i < rows; i++)
        {
            for(int j = 0; j < cols; j++)
            {
                grid [i][j] = '.';
            }
        }

        myShape = new TetrisShape(aShape); //FIXME
        block = new ArrayList<Coord> ();
        currDirect = new int[] {1,0};
    }

    public int[] getDirect()
    {
        return currDirect;
    }

    public void setDirect(int[] direct)
    {
        currDirect[0] = direct[0];
        currDirect[1] = direct[1];
    }

    public TetrisShape getShape()
    {
        return myShape;
    }

    public ArrayList<Coord> getBlock()
    {
        return block;
    }

    public boolean placeShape(ArrayList<Coord> shape, int r, int c)
    {
        myShape.getNewShape(r,c);
        if( 0 <= myShape.getMinCol() && cols >= myShape.getMaxCol() && 0 <= myShape.getMinRow() && rows >= myShape.getMaxRow())
        {
            for(int i = 0; i < 4; i++)
            {
               grid[myShape.getCoord(i).getRow()][myShape.getCoord(i).getCol()] = '#';
            }
            return true;
        }
        else
            return false;
    }
    

    public void removeShape(ArrayList<Coord> shape, int r, int c)
    {
        myShape.getNewShape(r,c);
        for(int i = 0; i < 4; i++)
        {
           grid[myShape.getCoord(i).getRow()][myShape.getCoord(i).getCol()] = '.';
        }
    }
    

    public boolean moveShape(int x, int y)
    {
       if (myShape.move(x,y))
           return (bound(myShape) && hitBlock(myShape)); //FIXME
       else
           return false;
    }

    // FIXME
    public TetrisShape addShape()
    {
        Random random = new Random();
        return myShape.randomShape(2);
    }


    public boolean checkRow(int k)
    {
        boolean complete = true;
        for(int j = 0; j < cols; j++)
        {
            complete = true && (grid[k][j] == '#');
        }
        return complete;      
    }

    public void deleteRow()
    {
        for(int i = 0; i < rows; i++)
        {
            if(checkRow(i))
            {
                for(int j = 0; j < cols; j++)
                {
                    grid[i][j] = '.';
                }
            }
        }
    }

    public void addBlocks(ArrayList<Coord> coords)
    {
        for(int i=0; i<coords.size(); i++)
        {
            block.add(coords.get(i));
        }
    }

    public boolean checkMove(int x, int y)
    {
        if(myShape.move(x,y))
            return (bound(myShape) && hitBlock(myShape));
        else
            return false;
    }

    /* FIXME
    public boolean checkRotateRight()
    {
        if(bound(myShape.rotateRight()) && hitBlock(myShape.rotateRight()))
            return true;
        else
            return false;
    }

    public boolean checkRotateLeft()
    {
        if(bound(myShape.rotateLeft()) && hitBlock(myShape.rotateLeft()))
            return true;
        else
            return false;
    }
    */
    
    public boolean bound(TetrisShape aShape) 
    {
        if( aShape.getMinCol() < 0 || aShape.getMaxCol() >= cols || aShape.getMinRow() < 0 || aShape.getMaxRow() >= rows)
        {
            System.out.println("The shape hit the bound");
            return false;
        }
        else 
            return true;
    }

    public boolean hitBlock(TetrisShape aShape)
    {
        for(int i = 0; i < block.size(); ++i)
        {
            for(int j = 0; j < 4; ++j)
            {    if (aShape.getCoord(j).equals(block.get(i)))
                {
                    System.out.println("The shape hits the occupied block");
                    return false;
                }
                else return true;
            }
            return true;
        }
        return true;
        
    }

}



//Ning Ma
//A50055399

import java.awt.*;

public class Coord extends Point
{
    private int row;
    private int col;

    public Coord (int r, int c)
    {
        row = r;
        col = c;
    }

    public Coord (Coord initial)
    {
        row = initial.getRow();
        col = initial.getCol();
    }

    public int getRow()
    {
        return row;
    }
    
    public int getCol()
    {
        return col;
    }

    public boolean equals(Coord coord)
    {
        if (coord.getRow() == row && coord.getCol() == col)
            return true;
        else
            return false;
    }

    public String toString()
    {
        return ("row:" + row + " " + "col:" + col);
    }


}



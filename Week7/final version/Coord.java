/* 
* NAME: Pengbo Li
* LOGIN: cs11sdc
* ID: A53079916
*
* NAME: Kuangyi Yang
* LOGIN: cs11sgy
* ID: A53083212
*/

import java.awt.*;

/**
 * A class with integer pair(r,c) instance
 * @author NAME: Pengbo Li LOGIN: cs11sdc ID: A53079916 
 * @author NAME: Kuangyi Yang LOGIN: cs11sgy ID: A53083212
 * @version CSE11-Spring
 */
public class Coord extends Point
{
    public int row;
    public int col;

    /**
     * Constructor Create a coordinate pair of r,c
     * @param r row in the pair(r,c)
     * @param c column in the pair(r,c)
     */
    public Coord (int r, int c)
    {
        row = r;
        col = c;
    }

    /**
     * Constructor Create a copy of coordinate from an original coordinate instance
     * @param initial original coordinate instance
     */
    public Coord (Coord initial)
    {
        row = initial.getRow();
        col = initial.getCol();
    }

    /**
     * Return value of row from coordinate pair(r,c)
     * @return row r from (r,c)
     */
    public int getRow()
    {
        return row;
    }
    
    /**
     * Return value of column from coordinate pair(r,c)
     * @return column c from (r,c)
     */
    public int getCol()
    {
        return col;
    }

    /**
     * Check if one coordinate instance is equal to another coordinate instance
     * @param coord a coordinate instance used for comparison
     * @return true if the two instances is equal
     */
    public boolean equals(Coord coord)
    {
        if (coord.getRow() == row && coord.getCol() == col)
            return true;
        else
            return false;
    }

    /**
     * Return a string represents row and column
     * @return a string represents row and column
    */
    @Override
    public String toString()
    {
        return ("row:" + row + " " + "col:" + col);
    }


}



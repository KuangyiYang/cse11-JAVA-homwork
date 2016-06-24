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
import java.util.Random;

/** 
 * A class that creates Tetris shape, provide the function of 
 * rotate
 * @author NAME: Pengbo Li LOGIN: cs11sdc ID: A53079916 
 * @author NAME: Kuangyi Yang LOGIN: cs11sgy ID: A53083212
 * @version CSE11-Spring
 */
public class TetrisShape 
{

    public static final int SHAPE_O = 0;
    public static final int SHAPE_I = 1;
    public static final int SHAPE_S = 2;
    public static final int SHAPE_Z = 3;
    public static final int SHAPE_L = 4;
    public static final int SHAPE_J = 5;
    public static final int SHAPE_T = 6;
    public static final int SHAPE_NUMBER = 7;

    private static Random random = new Random();

    private ArrayList<Coord> shape;
    private int type;

    /**
     * Constructor create 7 spcific Tetris shapes, use random number 
     * to create random shape
     */
    public TetrisShape() 
    {
        shape = new ArrayList<Coord>();
        shape.add(new Coord(0,0));

        type = random.nextInt(SHAPE_NUMBER);

        switch (type) 
        {
            case SHAPE_O: 
            {
        	   shape.add(new Coord(0,1));
        	   shape.add(new Coord(1,0));
        	   shape.add(new Coord(1,1));
        	   break;
            }
            case SHAPE_I: 
            {
        	   shape.add(new Coord(-2,0));
        	   shape.add(new Coord(-1,0));
        	   shape.add(new Coord(1,0));
        	   break;
            }
            case SHAPE_S: 
            {
        	   shape.add(new Coord(0,-1));
        	   shape.add(new Coord(-1,0));
        	   shape.add(new Coord(-1,1));
        	   break;
            }
            case SHAPE_Z: 
            {
        	   shape.add(new Coord(-1,-1));
        	   shape.add(new Coord(-1,0));
        	   shape.add(new Coord(0,1));
        	   break;
            }
            case SHAPE_L: 
            {
        	   shape.add(new Coord(-2,0));
        	   shape.add(new Coord(-1,0));
        	   shape.add(new Coord(0,1));
        	   break;
            }
            case SHAPE_J: 
            {
        	   shape.add(new Coord(-2,0));
        	   shape.add(new Coord(-1,0));
        	   shape.add(new Coord(0,-1));
        	   break;
            }
            case SHAPE_T: 
            {   
        	   shape.add(new Coord(0,-1));
        	   shape.add(new Coord(0,1));
        	   shape.add(new Coord(1,0));
        	   break;
            }
            default: 
            {
        	   System.out.println("Uknown shape");
        	   System.exit(1);
            }
        }
    }

    /**
     * Check whether a certain shape can be rotated
     * @return true if the shape can be rotated, false otherwise
     */
    public boolean rotate()
    {
        if(type == SHAPE_O)
            return false;
        else
            return true;
    }

    /**
     * Rotate the shape clockwise
     */
    public void rotateLeft()
    {
        if (rotate()) 
        {
            for (int i = 0; i < shape.size(); i++) 
            {
                int temp = shape.get(i).row;
                shape.get(i).row = -shape.get(i).col;
                shape.get(i).col = temp;
            }
        }
    }

    /**
     * Rotate the shape counterclockwise
     */
    public void rotateRight() 
    {
        if (rotate()) 
        {
            for (int i = 0; i < shape.size(); i++) 
            {
               int temp = shape.get(i).row;
               shape.get(i).row = shape.get(i).col;
               shape.get(i).col = -temp;
            }
        }
    }
    
    /**
     * Retrieve a copy of the stored ArrayList
     * @return a deep copy of the ArrayList.
     */
    public ArrayList<Coord> getArray() {
    	ArrayList<Coord> temp = new ArrayList<Coord>();
    	for (int i = 0; i < shape.size(); i++) {
    		temp.add(new Coord(shape.get(i).row, shape.get(i).col));
    	}
    	return temp;
    }

    /**
     * Retrieve a copy of the stored ArrayList
     * @param x x coordinate of a given offset
     * @param y y coordinate of a given offset
     * @return a deep copy of the ArrayList with an given offset.
     */
    public ArrayList<Coord> getArray(int x, int y) {
    	ArrayList<Coord> temp = new ArrayList<Coord>();
    	for (int i = 0; i < shape.size(); i++) {
    		temp.add(new Coord(shape.get(i).row + x, shape.get(i).col + y));
    	}
    	return temp;
    }
}

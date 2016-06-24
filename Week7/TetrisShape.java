import java.util.ArrayList;
import java.util.Random;

public class TetrisShape
{
    private ArrayList<Coord> shape = new ArrayList<Coord>();

    public TetrisShape (ArrayList<Coord> aShape)
    {
        shape = aShape;
    }

    public ArrayList<Coord> getShape()
    {
        return shape;
    }

    public Coord getCoord(int i)
    {
        return shape.get(i);
    }

    public ArrayList<Coord> getNewShape(int x, int y)
    {
        ArrayList<Coord> newShape = new ArrayList<Coord>();
        for(int i = 0; i < 4; i++)
        {
            newShape.add(new Coord(x+shape.get(i).x, y+shape.get(i).y));
        }
        return newShape;
    }

    public int getMinRow()
    {
        int minRow = 0;
        for(Coord coord : shape)
        {
            if(coord.getRow() < minRow)
            {
                minRow = coord.getRow();
            }
        }
        return minRow;
    }

    public int getMaxRow()
    {
        int maxRow = 0;
        for(Coord coord : shape)
        {
            if(coord.getRow() > maxRow)
            {
                maxRow = coord.getRow();
            }
        }
        return maxRow;
    }

    public int getMinCol()
    {
        int minCol = 0;
        for(Coord coord : shape)
        {
            if(coord.getCol() > minCol)
            {
                minCol = coord.getCol();
            }
        }
        return minCol;
    }

    public int getMaxCol()
    {
        int maxCol = 0;
        for(Coord coord : shape)
        {
            if(coord.getCol() < maxCol)
            {
                maxCol = coord.getCol();
            }
        }
        return maxCol;
    }

    

    public ArrayList<Coord> rotateLeft()
    {
        ArrayList<Coord> rotateShape = new ArrayList<Coord>();
        for(int i = 0; i < 4; i++)
        {
            rotateShape.add(new Coord(-shape.get(i).y, shape.get(i).x));
        }
        return rotateShape;
    }

    public ArrayList<Coord> rotateRight()
    {
        ArrayList<Coord> rotateShape = new ArrayList<Coord>();
        for(int i = 0; i < 4; i++)
        {
            rotateShape.add(new Coord(shape.get(i).y, -shape.get(i).x));
        }
        return rotateShape;
    }


    public boolean move(int x, int y)
    {
        if(!checkMoveDirection(x,y))
            return false;
        else
        {
            for(int i = 0; i < 4; i++)
            {
                shape.set(i, getNewShape(x,y).get(i));
            }
            return true;
        }   
    }


    public boolean checkMoveDirection(int x, int y)
    {
        boolean case1 = ((x == 1) && (y == 0));
        boolean case2 = ((x == -1) && (y == 0));
        boolean case3 = ((x == 0) && (y == 1));

        if(case1 || case2 || case3)
            return true;
        else
        {
            System.out.println("Not a good move direction");
            return false;
        }
    }

 
    public TetrisShape shape_o ()
    {
        ArrayList<Coord> o = new ArrayList<Coord>();
        o.add(new Coord(-1,-1));
        o.add(new Coord(0,-1));
        o.add(new Coord(0,0));
        o.add(new Coord(-1,0));
        return new TetrisShape(o);   
    }

    public TetrisShape shape_i ()
    {
        ArrayList<Coord> i = new ArrayList<Coord>();
        i.add(new Coord(-3,0));
        i.add(new Coord(-2,0));
        i.add(new Coord(0,0));
        i.add(new Coord(-1,0));
        return new TetrisShape(i);
    }

    public TetrisShape shape_s()
    {
        ArrayList<Coord> s = new ArrayList<Coord>();
        s.add(new Coord(-1,0));
        s.add(new Coord(0,-1));
        s.add(new Coord(0,0));
        s.add(new Coord(-1,1));
        return new TetrisShape(s);
    }

    public TetrisShape shape_t()
    {
        ArrayList<Coord> t = new ArrayList<Coord>();
        t.add(new Coord(0,0));
        t.add(new Coord(0,-1));
        t.add(new Coord(1,0));
        t.add(new Coord(0,1));
        return new TetrisShape(t);
    }

    public TetrisShape shape_z()
    {
        ArrayList<Coord> z = new ArrayList<Coord>();
        z.add(new Coord(-1,0));
        z.add(new Coord(-1,-1));
        z.add(new Coord(0,0));
        z.add(new Coord(0,1));
        return new TetrisShape(z);
    }

    public TetrisShape shape_l()
    {
        ArrayList<Coord> l = new ArrayList<Coord>();
        l.add(new Coord(-2,0));
        l.add(new Coord(-1,0));
        l.add(new Coord(0,0));
        l.add(new Coord(0,1));
        return new TetrisShape(l);
    }

    public TetrisShape shape_j()
    {
        ArrayList<Coord> j = new ArrayList<Coord>();
        j.add(new Coord(-2,0));
        j.add(new Coord(0,-1));
        j.add(new Coord(0,0));
        j.add(new Coord(-1,0));
        return new TetrisShape(j);
    }

    public TetrisShape randomShape(int x)
    {
        switch (x)
        {
            case 0:
                return shape_o();
            case 1:
                return shape_j();
            case 2:
                return shape_l();
            case 3:
                return shape_z();
            case 4:
                return shape_t();
            case 5:
                return shape_s();
            case 6:
                return shape_i();
            default:
                return shape_i();//FIX!!!!throw new Exception("Generated random integer outside of range 0 - 6");
        }
    }

    public void printSnake()
    {
        System.out.println("The shape is");
        for (int i = 0; i < shape.size(); ++i)
            System.out.println("The " + i + "th" + ":" + shape.get(i));
    }

}

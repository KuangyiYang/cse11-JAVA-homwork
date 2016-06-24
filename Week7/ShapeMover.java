import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import java.io.IOException;
import java.lang.Thread;
import java.lang.Runnable;
import java.util.concurrent.TimeUnit;
import java.awt.event.*;

public class ShapeMover implements Runnable, KeyListener
{
    private GraphicsGrid myGraphicsGrid;
    private Tetris myTetris;
    private String input; 
    private int count;
    private boolean move;
    private long MILLISWAIT = 50;

    ShapeMover(GraphicsGrid graphicsGrid, Tetris tetris)
    {
        myGraphicsGrid = graphicsGrid;
        myTetris = tetris;
        input = "k";
        count = 0;
        move = true;
    }
    @Override
    public void keyPressed(KeyEvent e)
    {
        input = String.valueOf(e.getKeyChar());
        if (input.equals("l") || input.equals("j") || input.equals("h") || input.equals("k"))
            count = 0;
    }
    @Override
    public void keyTyped(KeyEvent e)
    {
    } 

    public void keyReleased(KeyEvent e)
    {
    }

    public int[] getDirect()
    {
        return myGraphicsGrid.getGameGrid().getDirect();
    }

    public void setDirect(int[] direct)
    {
        myGraphicsGrid.getGameGrid().setDirect(direct);
    }

    public void setSpeed(long speed)
    {
        MILLISWAIT = speed;
    }


    public void run ()
    {
        int[] currDirect;
        int[] nextDirect;

        while(move)
        {
            currDirect = getDirect();
            nextDirect = currDirect;

            if (count == 20)
            {
                if(! myGraphicsGrid.getGameGrid().moveShape(currDirect[0],currDirect[1]))
                {
                    System.out.println("Not successful move");
                    myTetris.hasMovedGrowed(3);//FIXME
                    stopMove();
                }
                else
                {
                    myTetris.hasMovedGrowed(1);//FIXME
                    count = 0;
                    input = "d";
                }
            }
            //sleep if no valid key pressed within 1 s
            else if (count < 20 && (!input.equals("j")) && (!input.equals("l")) && (!input.equals("h")) && (!input.equals("k")))
            {
                try { TimeUnit.MILLISECONDS.sleep(MILLISWAIT);}
                catch (InterruptedException e){};
                ++count;
                continue;
            }
            //valid key is pressed. 
            else
            {
                nextDirect = nextDirection(currDirect,input);

                if(!myGraphicsGrid.getGameGrid().moveShape(nextDirect[0],nextDirect[1]))
                { 
                    myTetris.hasMovedGrowed(3); //FIXME
                    System.out.println("Not successfully grow the shape");
                    stopMove();
                }
                else
                {
                    myTetris.hasMovedGrowed(2); //FIXME
                    input = "d";
                    count = 0;
                }
            }
            //myGraphicsGrid.repaint();
            setDirect(nextDirect);
        }
    }

    public void stopMove()
    {
        move = false;
    }

    public int[] nextDirection(int[] curr, String input)
    {
        double angle = 0.0;
        double[][] rotationMatrix;
        int[] nextDirect;

        if (input == "d")
        {
           angle = 0.0;
        }
        else if(input == "j")
        {
            angle = 90.0;
        }
        else if(input == "k")
        {
            angle = -90.0;
        }
        else
        {
            System.out.println("invalid keyboard input");
        }

        rotationMatrix = rotation(angle);
        nextDirect = computeNextDirect(curr,rotationMatrix);
        return nextDirect;
    }

    public double[][] rotation(double angle)
    {
        double[][] matrix = new double[2][2];
        matrix[0][0] = Math.cos(Math.toRadians(angle));
        matrix[0][1] = -Math.sin(Math.toRadians(angle));
        matrix[1][0] = Math.sin(Math.toRadians(angle));
        matrix[1][1] = Math.cos(Math.toRadians(angle));

        return matrix;
    }

    public int[] computeNextDirect(int[] currDirect, double[][] matrix)
    {
        int[] a = new int[2];

        for (int i = 0; i < 2; ++i)
            for (int j = 0; j < 2; ++j)
                a[i] = a[i] + (int) matrix[i][j]*currDirect[j];
        return a;
    }

}

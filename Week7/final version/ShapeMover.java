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
import java.util.ArrayList;
import javax.swing.*;
import java.io.IOException;
import java.lang.Thread;
import java.lang.Runnable;
import java.util.concurrent.TimeUnit;
import java.awt.event.*;
import java.lang.Math;

/**
 * a class that move current piece downwards and respond to keystrock commands  
 * @author NAME: Pengbo Li LOGIN: cs11sdc ID: A53079916 
 * @author NAME: Kuangyi Yang LOGIN: cs11sgy ID: A53083212
 * @version CSE11-Spring
 */
public class ShapeMover implements Runnable, KeyListener
{
    private Tetris myTetris;
    private String input; 
    private boolean run;
    private boolean move = true;   
    private final int movePoints = 10;
    private final int rowPoints = 100;
    private long MILLISWAIT;

    /**
     * Constructor construct an instance of ShapeMover
     * @param tetris a Tetris reference
     */
    public ShapeMover(Tetris tetris)
    {
        myTetris = tetris;
        run = true;
        MILLISWAIT = 1000; 
    }

    /**
     * Determine if shape can continue to move down
     * @return true if shape can move down， false otherwise
     */
    public boolean checkMoveDown()
    {
        boolean moveDown = false;
        myTetris.getGraphGrid().getGameGrid().moveDown();
        if(checkValidMove())
        {
            moveDown = true;
        }
        myTetris.getGraphGrid().getGameGrid().moveUp();
        return moveDown;
    }

    /** 
     * Determine if the game should stop and game over
     * @return true if game should stop, false otherwise
     */
    public boolean gameStop()
    {
        if(myTetris.getGraphGrid().getGameGrid().isStop())
            return true;
        else
            return false;
    }

    /**
     * Fix the moving shape on the grid
     */
    public void fixShape()
    {
        myTetris.getGraphGrid().getGameGrid().setGrid();
    }

    /**
     * Add new random shape on the grid
     */
    public void addNewShape()
    {
        myTetris.getGraphGrid().getGameGrid().addShape();
    }

    /**
     * Determine whether a shape intersects any occupied block in the grid 
     * or hit the bounds
     * @return true if the shape neither interset the occupied blocks
     * nor hit the bounds, false otherwise
     */
    public boolean checkValidMove()
    {
        if(myTetris.getGraphGrid().getGameGrid().isValid())
            return true;
        else
            return false;
    }

    /**
     * Override the keyPressed method which responds to keyStrock commands from users
     * @param e KeyEvent object to get key input from users
     */
    @Override
    public void keyPressed(KeyEvent e)
    {
        if(move)
        {
            input = String.valueOf(e.getKeyChar());

            if(e.getKeyCode() == KeyEvent.VK_SPACE)
            {
                // while moving down renew the score, speed and grid
                while(checkMoveDown())
                {
                    myTetris.getGraphGrid().getGameGrid().moveDown();
                    // renew the score and set new speed according to current score
                    myTetris.setCurrScore(myTetris.getCurrScore()+ movePoints);
                    myTetris.setCountScore(myTetris.getCountScore() + movePoints);
                    if((myTetris.getCountScore()/2000)!=0)
                    {
                        myTetris.setSpeed(myTetris.getSpeed() + 1); 
                        myTetris.setCountScore(0);
                    }
                }
                myTetris.getGraphGrid().repaint();
                fixShape();

                // delete complete rows, renew scores, speed and grid 
                if(gameStop() == false)
                {
                    int removeRows = myTetris.getGraphGrid().getGameGrid().deleteRows();
                    if(removeRows == 1)
                    {
                        myTetris.setCurrScore(myTetris.getCurrScore() +  100);
                        myTetris.setCountScore(myTetris.getCountScore() + 100);
                    }else if(removeRows > 1)
                    {
                        myTetris.setCurrScore(myTetris.getCurrScore() +  (int) (Math.pow(2,(double)(removeRows))*rowPoints));
                        myTetris.setCountScore(myTetris.getCountScore() + (int) (Math.pow(2,(double)(removeRows))*rowPoints));
                    }
                    
                    if((myTetris.getCountScore()/2000)!=0)
                    {
                        myTetris.setSpeed(myTetris.getSpeed() + 1);
                        myTetris.setCountScore(0);
                    }
                    addNewShape();
                }

                // when game over, renew high score
                else
                {
                    myTetris.setGameOver();
                    if(myTetris.getCurrScore() > myTetris.getHighScore())
                    {
                        myTetris.setHighScore(myTetris.getCurrScore());
                    }
                }
            }

            else if(input.equals("h"))
            {
                myTetris.getGraphGrid().getGameGrid().moveLeft();
                if(checkValidMove() == false)
                {
                    myTetris.getGraphGrid().getGameGrid().moveRight();
                }
                myTetris.getGraphGrid().repaint();
            }

            else if(input.equals("l"))
            {
                myTetris.getGraphGrid().getGameGrid().moveRight();
                if(checkValidMove() == false)
                {
                    myTetris.getGraphGrid().getGameGrid().moveLeft();
                }
                myTetris.getGraphGrid().repaint();
            }

            else if(input.equals("j"))
            {
                myTetris.getGraphGrid().getGameGrid().rotateLeft();
                if(checkValidMove() == false)
                {
                    myTetris.getGraphGrid().getGameGrid().rotateRight();
                }
                myTetris.getGraphGrid().repaint();
            }

            else if(input.equals("k"))
            {
                myTetris.getGraphGrid().getGameGrid().rotateRight();
                if(checkValidMove() == false)
                {
                    myTetris.getGraphGrid().getGameGrid().rotateLeft();
                }
                myTetris.getGraphGrid().repaint();
            }
        }
    }

    /*
    Override the method keyTyped
    @param e KeyEvent object to get key input from users
    */
    @Override
    public void keyTyped(KeyEvent e){}

    /*
    Override the method keyReleased
    @param e keyEvent object to get key input from users
    */
    public void keyReleased(KeyEvent e){}

    /*
    set moving speed
    @param speed moving speed of shape
    */
    public void setSpeed(long speed) 
    {
        MILLISWAIT = speed;
    }

    /*
    Override the method run
    Take action to move shape downwards
    */
    @Override
    public void run()
    {                
        while(run)
        {
            myTetris.getGraphGrid().repaint();
            try{TimeUnit.MILLISECONDS.sleep(MILLISWAIT);}
            catch(InterruptedException e){}

            if(move)
            {
                // while keeps moving downwards, renew score, speed and grid
                if(checkMoveDown() == true)
                {
                    myTetris.getGraphGrid().getGameGrid().moveDown();
                    myTetris.setCurrScore(myTetris.getCurrScore() + movePoints);
                    myTetris.setCountScore(myTetris.getCountScore() + movePoints);
                    if((myTetris.getCountScore()/2000)!=0)
                    {
                        myTetris.setSpeed(myTetris.getSpeed() + 1); 
                        myTetris.setCountScore(0);
                    }
                }
                // while stop moving downwards, check delete rows, renew score, speed and grid
                if(checkMoveDown() == false)
                {
                    fixShape();
                    if(gameStop() == false)
                    {
                        myTetris.getGraphGrid().repaint();
                        try{TimeUnit.MILLISECONDS.sleep(MILLISWAIT);}
                        catch(InterruptedException e){}
                        
                        int removeRows = myTetris.getGraphGrid().getGameGrid().deleteRows();
                        if(removeRows == 1)
                        {
                            myTetris.setCurrScore(myTetris.getCurrScore() + 100);
                            myTetris.setCountScore(myTetris.getCountScore() + 100);
                        }
                        else if(removeRows > 1)
                        {
                            myTetris.setCurrScore(myTetris.getCurrScore() +  (int) (Math.pow(2,(double)(removeRows))*rowPoints));
                            myTetris.setCountScore(myTetris.getCountScore() + (int) (Math.pow(2,(double)(removeRows))*rowPoints));

                        }
                        if((myTetris.getCountScore()/2000)!=0)
                        {
                            myTetris.setSpeed(myTetris.getSpeed() + 1);
                            myTetris.setCountScore(0);
                        }
                        addNewShape();
                    }                         
                    else
                    {
                        myTetris.setGameOver();
                        if(myTetris.getCurrScore() > myTetris.getHighScore())
                        {
                            myTetris.setHighScore(myTetris.getCurrScore());
                        }
                    }
                }
            }    
        }
    }

    public void isMove(boolean m)
    {
        this.move = m;
    }
}

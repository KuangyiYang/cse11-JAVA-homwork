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
import java.util.Random;
import java.util.Scanner;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

/**
 * A class that generate the GUI interface for the game
 * @author NAME: Pengbo Li LOGIN: cs11sdc ID: A53079916 
 * @author NAME: Kuangyi Yang LOGIN: cs11sgy ID: A53083212
 * @version CSE11-Spring
 */
public class Tetris extends JFrame implements Runnable,ActionListener,ChangeListener
{
    private GraphicsGrid myGraphicsGrid;
    private ShapeMover myShapeMover;
    private JLabel scoreLabel, highScoreLabel, score, highScore, GAMEOVER;
    private JButton newGame, reset;
    private JSlider slider;
    private int currScore;
    private int countScore;
    private int highestScore;
    final int MIN_SPEED = 1;
    final int MAX_SPEED = 20;

    /**
     * Constructor Create a Tetris instance which is the interface of the game
     * @param pls size of each block segement in pixels, default is 20
     */
    public Tetris (int pls)
    {
        super();
        myGraphicsGrid  = new GraphicsGrid(pls);
        myShapeMover = new ShapeMover(this);
    }

    /**
     * Lay out the component so that scores, buttoms and sliders show up where you want them
     */
    public void run()
    {
        JPanel p1 = new JPanel();
        p1.setLayout(new GridLayout(2,3,5,5));       
        scoreLabel = new JLabel("Score");
        highScoreLabel = new JLabel("High Score");
        GAMEOVER = new JLabel(" ");
        score = new JLabel(Integer.toString(currScore));
        highScore = new JLabel(Integer.toString(highestScore));

        p1.add(scoreLabel);
        p1.add(score);
        p1.add(GAMEOVER);
        p1.add(highScoreLabel);
        p1.add(highScore);
        add(p1,BorderLayout.NORTH);

        JPanel p2 = new JPanel();
        p2.setLayout(new FlowLayout(FlowLayout.LEFT,5,5));
        newGame = new JButton("New Game");
        reset = new JButton("Reset");
        slider = new JSlider(MIN_SPEED,MAX_SPEED,1);
        newGame.addActionListener(this);
        reset.addActionListener(this);
        slider.addChangeListener(this);
        slider.setFocusable(false);

        p2.add(newGame);
        p2.add(reset);
        p2.add(slider);
        add(p2,BorderLayout.SOUTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        myGraphicsGrid.addKeyListener(myShapeMover);
        add(myGraphicsGrid,BorderLayout.CENTER);

        pack();
        validate();
        setVisible(true);
        myGraphicsGrid.setFocusable(false);
        myGraphicsGrid.requestFocusInWindow();
        reset.setFocusable(false);

        this.addKeyListener(myShapeMover);
        this.setFocusable(true);
        Thread thread = new Thread(myShapeMover);
        thread.start();
    }

    /**
     * Take action when you hit "New Game" and "Reset" buttom
     */
    public void actionPerformed(ActionEvent evt)
    {
        if (evt.getSource() == newGame)
        {
            if ( currScore > highestScore )
            {
                highestScore = currScore;
                highScore.setText(Integer.toString(currScore));
            }

            setCurrScore(0);
            countScore = 0;
            score.setText(Integer.toString(currScore));
            myShapeMover.isMove(true);
            GAMEOVER.setText("");
            myGraphicsGrid.newTetrisGrid();
            myGraphicsGrid.repaint();

            validate();
            setVisible(true);
            myGraphicsGrid.setFocusable(true);
            myGraphicsGrid.requestFocusInWindow();

        }

        if (evt.getSource() == reset)
        {
            setCurrScore(0);
            setHighScore(0);
            countScore = 0;
            myShapeMover.isMove(false);
            slider.setValue(1);
            GAMEOVER.setText("");
            myShapeMover.setSpeed(1000);
        }
    }

    /**
     * Invoked when the target of the listener has changed it state
     * @param evt a ChangeEvent object
     */
    public void stateChanged(ChangeEvent evt)
    {
        long speed = (long) slider.getValue();
        long waitInterval = 1050 - speed*50;
        myShapeMover.setSpeed(waitInterval);
    }
    
    /**
     * show up "GAME OVER" label
     */
    public void setGameOver()
    {
        GAMEOVER.setText("GAME OVER");
    }

    /**
     * Set leve of slider to control speed
     * @param value level of slider from 1 to 20
     */
    public void setSpeed(int value)
    {
        slider.setValue(value);
    }

    /**
     * Return speed
     * @return the value of speed 
     */
    public int getSpeed()
    {
        return slider.getValue();
    }

    /**
     * Set high score
     * @param value the value of high score
     */
    public void setHighScore(int value)
    {
        highestScore = value;
        highScore.setText(String.valueOf(highestScore));
    }

    /**
     * Return high score
     * @return the value of high score   
     */
    public int getHighScore()
    {
        return highestScore;
    }

    /**
     * Set current score
     * @param s current score value
     */
    public void setCurrScore(int s)
    {
        currScore = s;
        score.setText(String.valueOf(currScore));
    }

    /**
     * return current score
     * @return current score value
     */
    public int getCurrScore()
    {
        return currScore;
    }

    /**
     * Set temperary counting score
     * @param s the value count score
     */
    public void setCountScore(int s)
    {
        countScore = s;
    }

    /**
     * Return temperary counting score
     * @return return temperary counting score value 
     */
    public int getCountScore()
    {
        return countScore;
    }

    /**
     * Return a GraphicsGrid instance
     * @return a GraphicsGrid instance 
     */
    public GraphicsGrid getGraphGrid()
    {
        return myGraphicsGrid;
    }

    /**
     * Read pixels from command line arguments and create a Tetris game
     * @param args command line arguments(pixels)
     */
    public static void main (String[] args)
    {
        int pixels = 20;
        if(args.length != 0)
        {
            pixels = Integer.valueOf(args[0]);
        }

        Tetris myTetris = new Tetris(pixels);
        SwingUtilities.invokeLater(myTetris);

        try
        {
            System.out.format("Hit Return to exit program");
            System.in.read();
        }
        catch (IOException e){}

        myTetris.dispatchEvent(new WindowEvent(myTetris,WindowEvent.WINDOW_CLOSING));
        myTetris.dispose();
    }
}

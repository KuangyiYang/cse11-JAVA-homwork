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

public class Tetris extends JFrame implements Runnable,ActionListener, ChangeListener
{
    private GraphicsGrid myGraphicsGrid;
    private ShapeMover myShapeMover;
    private JLabel scoreLabel, highScoreLabel, score, highScore,GAMEOVER;
    private JButton newGame, reset;
    private JSlider slider;
    private int moveGrowCount;
    private int newScore;
    private int highestScore;
    final int minSpeed = 1;
    final int maxSpeed = 20;

    public Tetris (int wd, int ht, int pls)
    {
        super("Tetris");
        setSize(wd,ht);
        myGraphicsGrid  = new GraphicsGrid(wd,ht,pls);
        myShapeMover = new ShapeMover(myGraphicsGrid,this);
        moveGrowCount = 0;
        newScore = 0;
        highestScore = 0;
    }

    public void run()
    {
        JPanel p1 = new JPanel();
        p1.setLayout(new GridLayout(2,3,5,5));

        scoreLabel = new JLabel("Score");
        highScoreLabel = new JLabel("High Score");

        score = new JLabel(Integer.toString(newScore));
        highScore = new JLabel(Integer.toString(highestScore));
        GAMEOVER = new JLabel("GAME OVER!");

        p1.add(scoreLabel);
        p1.add(score);
        p1.add(GAMEOVER);
        p1.add(highScoreLabel);
        p1.add(highScore);
        
        add(p1,BorderLayout.NORTH);

        JPanel p2 = new JPanel();
        p2.setLayout(new FlowLayout(FlowLayout.LEFT,5,5));

        newGame = new JButton("New Game");
        newGame.addActionListener(this);
        reset = new JButton("Reset");
        reset.addActionListener(this);
        slider = new JSlider(minSpeed,maxSpeed,1);
        slider.addChangeListener(this);
        slider.setFocusable(false);

        p2.add(newGame);
        p2.add(reset);
        p2.add(slider);
        add(p2,BorderLayout.SOUTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        myGraphicsGrid.addKeyListener(myShapeMover);
        add(myGraphicsGrid,BorderLayout.CENTER);

        validate();
        setVisible(true);
        myGraphicsGrid.setFocusable(true);
        myGraphicsGrid.requestFocusInWindow();
    }

    public void actionPerformed(ActionEvent evt)
    {
        if (evt.getSource() == newGame)
        {

            int currWidth = myGraphicsGrid.getWidth();
            int currHeight = myGraphicsGrid.getHeight();
            System.out.println("CurrentWidth: " + currWidth + ", currHeight:" + currHeight);

            remove(myGraphicsGrid);
            myGraphicsGrid = new GraphicsGrid(currWidth,currHeight,10);
            myShapeMover = new ShapeMover(myGraphicsGrid,this);
            myGraphicsGrid.addKeyListener(myShapeMover);
            add(myGraphicsGrid,BorderLayout.CENTER);
            GAMEOVER.setText("");

            if ( newScore > highestScore)
            {
                highestScore = newScore;
                highScore.setText(Integer.toString(highestScore));
            }

            newScore = 0;
            score.setText(Integer.toString(newScore));
            slider.setValue(1);
            moveGrowCount = 0;

            validate();
            setVisible(true);
            myGraphicsGrid.setFocusable(true);
            myGraphicsGrid.requestFocusInWindow();

            Thread t = new Thread(myShapeMover);
            t.start();
        }
        else if (evt.getSource() == reset)
        {
            myShapeMover.stopMove();
            newScore = 0;
            highestScore = 0;
            slider.setValue(1);
            highScore.setText(Integer.toString(highestScore));
            moveGrowCount = 0;
        }
    }

    public void stateChanged(ChangeEvent evt)
    {
        long speed = (long) slider.getValue();
        long waitInterval = 1000/20/speed;
        myShapeMover.setSpeed(waitInterval);
    }



    public GraphicsGrid getGraphicsGrid()
    {
        return myGraphicsGrid;
    }

    public ShapeMover getShapeMover()
    {
        return myShapeMover;
    }

    // FIXME
    void hasMovedGrowed(int moveType)
    {
        moveGrowCount++;
        if (moveGrowCount == 10)
        {
            myGraphicsGrid.getGameGrid().addShape();
            moveGrowCount = 0;
        }
        switch (moveType)
        {
            //move
            case 1:
                myGraphicsGrid.repaint();
                break;
            case 2:
                myGraphicsGrid.repaint();
                int currScore = Integer.parseInt(score.getText());
                newScore = currScore + 10;
                
                int currSpeed = slider.getValue();
                
                if (((newScore % 100) == 0 ) && (currSpeed < slider.getMaximum()))
                {
                    slider.setValue(currSpeed + 1);
                }

                score.setText("" + newScore);

                if (newScore > highestScore)
                {
                    highestScore = newScore;
                    highScore.setText("" + highestScore);
                }
                break;
            default:
                setGameOver();
                myShapeMover.stopMove();
                break;
        }
    }

    void setGameOver()
    {
        GAMEOVER.setText("GAME OVER");
    }


    public static void main (String[] args)
    {

        Tetris myTetris = new Tetris(10,20,10);
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

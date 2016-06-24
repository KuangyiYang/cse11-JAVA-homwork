/* 
* NAME: Pengbo Li
* LOGIN: cs11sdc
* ID: A53079916
*
* NAME: Kuangyi Yang
* LOGIN: cs11sgy
* ID: A53083212
*/

import java.util.concurrent.TimeUnit;
import java.util.ArrayList;
import java.io.IOException;
import java.lang.Runnable;
import java.lang.Thread;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

/**
 * A class that constructs a graphical version of grid instance
 * @author NAME: Pengbo Li LOGIN: cs11sdc ID: A53079916 
 * @author NAME: Kuangyi Yang LOGIN: cs11sgy ID: A53083212
 * @version CSE11-Spring
 */
public class GraphicsGrid extends JPanel 
{
    private TetrisGrid myGameGrid;
    private int width = 10;
    private int height = 20;
    private int pixels = 20;
    private int sideWidth = 100;

    /**
     * Constructor creates a graphical version of grid instance 
     * @param pls size of each block segments in pixels, default is 20
     */
    public GraphicsGrid(int pls)
    {
        myGameGrid = new TetrisGrid();
        pixels = pls; 
    }

    /**
     * Get a TetrisGrid instance
     * @return a TetrisGrid instance
     */
    public TetrisGrid getGameGrid()
    {
        return myGameGrid;
    }

    /**
     * Generate a new TetrisGrid instance
     */
    public void newTetrisGrid()
    {
        myGameGrid = new TetrisGrid();
    }

    /**
     * Override the method getPreferredSize to get the preferred 
     * size properly
     * @return the dimension of preferred size
     */
    @Override
    public Dimension getPreferredSize()
    {
        return new Dimension(width*pixels+200,height*pixels+2);
    }

    /**
     * Override the method paintComponent to paint the lines 
     * and cells in the grid
     * @param g a Graphics object to paint on
     */
	@Override
	protected synchronized void paintComponent(Graphics g) 
    {   
		super.paintComponent(g);
    
        // paint occupied blocks with color blue
        for (Coord shapeCell : myGameGrid.getOccupiedBlocks())
        {
            int X  = shapeCell.getRow()*pixels;
            int Y = shapeCell.getCol()*pixels + sideWidth;
            g.setColor(Color.BLUE);
            g.fillRect(Y,X,pixels,pixels);
        }

        // draw grid with black lines
        g.setColor(Color.BLACK);
        g.drawRect(sideWidth,0, width*pixels,height*pixels);
		for (int i = sideWidth; i <  width*pixels + sideWidth; i += pixels) 
        {
			g.drawLine(i, 0, i, height*pixels);
		}

		for (int i = 0; i < height*pixels; i += pixels) 
        {
			g.drawLine(sideWidth, i, sideWidth + width*pixels, i);
		}
	}
}
// vim: ts=4:sw=4:tw=78

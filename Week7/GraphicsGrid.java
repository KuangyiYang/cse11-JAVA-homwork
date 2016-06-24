/** A program that move a center-colored cell move back-and-forth
  * @author Kuangyi Yang, A53083212
  * @version 28/Feb/2015
  */
import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import java.io.IOException;
import java.lang.Thread;
import java.lang.Runnable;
import java.util.concurrent.TimeUnit;
import java.awt.event.*;

public class GraphicsGrid extends JPanel 
{

    private TetrisGrid myGameGrid;
    private int width = 40;
    private int height = 40;
    private int pixels = 10;

    public GraphicsGrid(int wd, int ht, int pls)
    {
        super();
        width = wd;
        height = ht;
        pixels = pls;

        ArrayList<Coord> initial = new ArrayList<Coord> (); //FIXME
        myGameGrid = new TetrisGrid(width, height, initial);
    }

    public TetrisGrid getGameGrid()
    {
        return myGameGrid;
    }

    public int getWidth()
    {
        return width;
    }

    public int getHeight()
    {
        return height;
    }

    public int getPixels()
    {
        return pixels;
    }
    
	@Override
	protected synchronized void paintComponent(Graphics g) 
    {
        int realWidth = getWidth();
        int realHeight = getHeight();
    
        int widthOffset = (realWidth*pixels- width*pixels)/2;
        int heightOffset = (realHeight*pixels - height*pixels)/2;
        
		super.paintComponent(g);

        g.setColor(Color.BLUE);
        for (Coord shapeCell : myGameGrid.getShape().getShape())
        {
            int X  = shapeCell.getCol()*pixels + widthOffset;
            int Y = shapeCell.getRow()*pixels + heightOffset;
            g.fillRect(X,Y,pixels,pixels);
        }
        
        g.setColor(Color.BLUE);
        for (Coord block : myGameGrid.getBlock())
        {
            int X = block.getCol()*pixels + widthOffset;
            int Y = block.getRow()*pixels + heightOffset;
            g.fillRect(X,Y,pixels,pixels);
        }

        g.setColor(Color.BLACK);
        g.drawRect(widthOffset,heightOffset, width*pixels, height*pixels);
		for (int i = 0; i <=  width; i++) 
        {
			g.drawLine(i*pixels + widthOffset, heightOffset, i*pixels + widthOffset, heightOffset + height*pixels);
		}

		for (int i = 0; i <= height; i++) 
        {
			g.drawLine(widthOffset, i*pixels + heightOffset, widthOffset + width*pixels, i*pixels + heightOffset);
		}
	}
}
// vim: ts=4:sw=4:tw=78

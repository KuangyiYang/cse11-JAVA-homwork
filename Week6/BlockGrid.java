import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import java.io.IOException;
import java.lang.Exception;
import java.lang.Thread;
import java.lang.Runnable;
import java.util.concurrent.TimeUnit;
import java.awt.event.*;

/** 
  * A class that make a 2x2 box move back-and-forth
  * @author Kuangyi Yang, A53083212
  * @version CSE11 Spring 2015
  */
public class BlockGrid {

	/**
	  * read width, height and pixels from command line arguments.
	  * make a grid with a 2x2 box colored red move back-and-forth.
	  * @param a command line arguments (width height, pixels)
	  * @see MyWindow
	  * @see Mover
	  * @see Grid
	*/
	public static void main(String[] a) {

		int width = 0;
		int height = 0;
		int pixels = 0;
		// default condition
		if (a.length == 0) 
		{
			width = 40;
			height = 20;
			pixels = 20;
		}
		// read width height and pixels from command-line
		else if (a.length == 3)
		{
			try
			{
				width = Integer.parseInt(a[0]);
				height = Integer.parseInt(a[1]);
				pixels = Integer.parseInt(a[2]);

				if( width < 2 || height < 2 || pixels < 2)
				{
					System.out.println("Usage: BlockGrid [ width height pixels ]");
					System.exit(-1);
				}
			}
			catch (Exception e)
			{
				System.out.println("Usage: BlockGrid [ width height pixels ]");
				System.exit(-1);
			}
		}
		else
		{
			System.out.println("Usage: BlockGrid [ width height pixels ]");
			System.exit(-1);
		}
		// construct a window, add grid on that
		MyWindow window = new MyWindow(width, height, pixels);
		Grid grid = window.getGrid();
		// start moving
		Mover mover = new Mover(width,height,grid);
		Thread thread = new Thread(mover);
		SwingUtilities.invokeLater(window);
		thread.start();

		try 
		{
			System.out.format("Hit Return to exit program");
			System.in.read();
		}
		catch (IOException e){}
		// stop Mover thread before exit the program
		mover.exit();
		try {TimeUnit.MILLISECONDS.sleep(200);}
        catch (InterruptedException e){}

		window.dispatchEvent(new WindowEvent(window, 
			WindowEvent.WINDOW_CLOSING));
        window.dispose();	
	}
}

/**
  * A class construct a Mywindow instance
  * @author Kuangyi Yang
  * @version CSE11 Spring 2015
  */
class MyWindow extends JFrame implements Runnable
{
	private	Grid grid;
	private int width = 0;
	private	int height = 0;

	/**
 	  * Constructor  construct an instnce of window
 	  * @param width  width of the window
 	  * @param height height of the window
 	  * @param pixels pixels of the cell
 	  * @see Grid
 	  */
	public MyWindow(int width, int height, int pixels) {
		super();
		this.width = width;
		this.height = height;
		setMinimumSize(new Dimension(4,4));
		grid = new Grid(width*pixels,height*pixels,pixels);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		add(grid);
		pack();
		validate();
		setVisible(true);
	}

	/** return a grid instance
 	  * @return a Grid instance
 	  */
	public Grid getGrid(){
		return grid;
	}
	/** it may take any action whatsover
 	  */
	public void run() {
	}

}

/**
  * A class construct a Grid instance
  * @author Kuangyi Yang
  * @version CSE11 Spring 2015
  */
class Grid extends JPanel {
	private ArrayList<Point> fillCells;
	private int width, height;
	private int pixels;

	/**
 	  * Constructor  Construct an instance of Grid
 	  * @param w  width of the grid
 	  * @param h height of the grid
 	  * @param p pixels of the cell
 	  */
	public Grid(int w, int h, int p) {
		this.width = w;
		this.height = h;
		this.pixels = p;
		fillCells = new ArrayList<Point>();
	}

	/** 
	  * Override the method getPreferredSize
	  * get the prefeeredSize properly
 	  * @return return the value of the prefeeredSize properly
 	  */
	@Override
	public Dimension getPreferredSize()
	{
		return new Dimension(width+2, height+2);
	}

	/** 
	  * Override the method paintComponent
	  * paint the cells in the graphic
	  * @param g a Graphic object to paint on
 	  */
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (fillCells == null) return;
		for (Point fillCell : fillCells) {
			int cellX = (fillCell.x * pixels);
			int cellY = (fillCell.y * pixels);
			g.setColor(Color.RED);
			g.fillRect(cellX, cellY, pixels, pixels);
		}
		g.setColor(Color.BLACK);
		g.drawRect(0, 0, width*pixels, height*pixels);

		for (int i = 0; i < width*pixels; i += pixels) {
			g.drawLine(i, 0, i, height*pixels);
		}

		for (int i = 0; i < height*pixels; i += pixels) {
			g.drawLine(0, i, width*pixels, i);
		}
	}

	/** 
	  * add a cell to fillCells and repain the graphic
	  * @param x  x coordinate of the new cell
	  * @param y  y coordinate of the new cell
 	  */
	public void fillCell(int x, int y) {
		fillCells.add(new Point(x, y));
		repaint();
	}
	
	/** 
	  * clear a cell from fillCells and repain the graphic
	  * @param x  x coordinate of the cell
	  * @param y  y coordinate of the cell
 	  */
	public void clearCell(int x, int y){
		fillCells.remove(new Point(x,y));
		repaint();
	}
}

/**
  * A class construct a Mover instance to move box inside back-and-forth
  * A single Mover instance must be run in a sperate thread
  * @author Kuangyi Yang
  * @version CSE11 Spring 2015
  */
class Mover implements Runnable
{
	private Point upper_left;
	private Point upper_right;
	private Point lower_left;
	private Point lower_right;
	private boolean stop;
	private Grid grid;
	private final int WAIT_TIME = 150;
	private int width;

	/**
 	  * Constructor
 	  * @param width  width of the grid
 	  * @param height height of the grid
 	  * @param grid  a Grid instance where put 2x2 box on
 	  */
	public Mover(int width, int height, Grid grid)
	{
		this.width = width;
		this.grid = grid;
		upper_left = new Point(width/2-1, height/2-1);
		lower_left = new Point(width/2-1, height/2);
		upper_right = new Point(width/2, height/2-1);
		lower_right = new Point(width/2 , height/2);	
		stop = false;
	}

	/**
 	  * Take action to move 2x2 box in the center of grid back-and-forth
 	  */
	public void run()
	{
		int direction = 1;
		int upper_left_x = upper_left.x;
		int upper_left_y = upper_left.y;
		int upper_right_x = upper_right.x;
		int upper_right_y = upper_right.y;
		int lower_left_x = lower_left.x;
		int lower_left_y = lower_left.y;
		int lower_right_x = lower_right.x;
		int lower_right_y = lower_right.y;
		
		while(stop == false)
		{
			if( upper_right_x >= (width - 1) || (upper_left_x <= 0) )
			{
				direction = -direction;
			}

			if( width <= 2)
			{
				direction = 0;
			}
			
			grid.clearCell(upper_left_x,upper_left_y);
			grid.clearCell(upper_right_x,upper_right_y);
			grid.clearCell(lower_left_x,lower_left_y);
			grid.clearCell(lower_right_x,lower_right_y);

			upper_left_x = upper_left_x + direction;
			upper_right_x = upper_right_x + direction;
			lower_left_x = lower_left_x + direction;
			lower_right_x = lower_right_x + direction;

			grid.fillCell(upper_left_x,upper_left_y);
			grid.fillCell(upper_right_x,upper_right_y);
			grid.fillCell(lower_left_x,lower_left_y);
			grid.fillCell(lower_right_x,lower_right_y);

			try {TimeUnit.MILLISECONDS.sleep(WAIT_TIME);}
			catch (InterruptedException e) {}
		}
	}
	/** Stop moving
 	  */
	public void exit()
	{
		stop = true;
	}
}
// vim: ts=4:sw=4:tw=78

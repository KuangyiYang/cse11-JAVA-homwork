/*************************************************************************
 *  Compilation:  javac -classpath '*':'.' DiagonalLoop.java
 *  Execution:    java -classpath '*':'.' DiagonalLoop.java 
 *  Requires: objectdraw.jar
 *
 *  prints filled circles down the diagonal of the canvas
 *  - runs as  java program
 *************************************************************************/
import objectdraw.*;
import java.awt.*;

public class DiagonalLoop extends WindowController 
{
	private static final int WIN_SIZE = 400;
	private static final int DIAMETER = 25;
	private static final int FX=4, FY=54;
	private Text instructions;
	
	public void begin() 
	{
		instructions = new Text("Click mouse to draw Circles", 50,WIN_SIZE-100, canvas);
	}

    	public void onMouseClick(Location point) 
	{
		instructions.hide();
		int corner = 0; 		// where to draw

		while ( corner < WIN_SIZE) 	// Repeat until we run out of window
		{
			new FilledOval(corner,corner,DIAMETER,DIAMETER, canvas);
			corner += DIAMETER;
		}
		System.out.println("Panel Size - " + this.getSize());
	}

	public static void main(String[] args) {	 
 		new DiagonalLoop().startController(WIN_SIZE+FX,WIN_SIZE+FY); 
	} 
}

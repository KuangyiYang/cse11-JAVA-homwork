/*************************************************************************
 *  Name: Kuangyi Yang
 *  ID: A53083212
 *  Email: kuy006@ucsd.edu
 *************************************************************************/

import objectdraw.*;
import java.awt.*;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.lang.Math;

public class Countdown extends WindowController {
	private static final int CDIAMETER = 20; // initialize diameter of circle
	private static final int minLength = 10; // set minimum length of second hand larger than radius 
	private static final double PI = Math.PI;
	private static final double interval = 2*PI/60; // set degree of each interval in one second

	private static int LENGTH = 0; // initialize length of second hand
	private static int actualWidth = 0; // initialize actual width of canvas
	private static int actualHeight = 0; // initialize actual height of canvas
	private static int clockWidth = 0; // initialize input of clock width
	private static int clockHeight = 0; // initialize input of clock height
	private static int numOfSeconds = 0; // initialize input of number of seconds

	private FilledOval circle = new FilledOval(0,0,0,0,canvas);
	private AngLine secondHand = new AngLine(0,0,0,0,canvas);
	
	
	public void begin() {

		int xAxis = 0; // initialize location of center of canvas
		int yAxis = 0;
		final int T = 1000; // set milliseconds for waiting
		double degree = 0;

		actualWidth = canvas.getWidth(); // get actual size of canvas
		actualHeight = canvas.getHeight();
		xAxis = actualWidth/2; // find center of canvas
		yAxis = actualHeight/2;

		// Create a centered circle and color it blue
		circle = new FilledOval(xAxis-CDIAMETER/2,
                         yAxis-CDIAMETER/2,CDIAMETER,CDIAMETER,canvas);
		circle.setColor(Color.BLUE);

		// Set length of secondhand based on canvas size
		if( actualWidth <= actualHeight ){
			LENGTH = actualWidth/2;
		}else{
			LENGTH = actualHeight/2;
		}
		
		// draw second hand and move it by drawing,waiting erasing and redrawing
		while( numOfSeconds >= 0 ){

			degree = PI/2 + interval*numOfSeconds;
			secondHand.removeFromCanvas(); // erase secondhand
			secondHand = new AngLine(xAxis,yAxis,LENGTH,degree,canvas);

			try {TimeUnit.MILLISECONDS.sleep(T);} // wait for a second
			catch (InterruptedException e) {}
			numOfSeconds = numOfSeconds - 1;
		}
	}
 	
	// click mouse to end the program	
	public void onMouseClick(Location point){
		System.exit(0);
	}

	public static void main(String[] args) {
		int minWidth = 0;
		int minHeight = 0;
		minWidth = 2*(CDIAMETER/2+minLength);
		minHeight = 2*(CDIAMETER/2+minLength);
		
		// Read the inputs of width, height and number of seconds
		Scanner scnr = new Scanner(System.in);

		System.out.print("Enter clock width in pixels : ");
		clockWidth = scnr.nextInt();

		System.out.print("Enter clock height in pixels : ");
		clockHeight = scnr.nextInt();

		System.out.print("Enter countdown in seconds : ");
		numOfSeconds = scnr.nextInt();
		
		// Check bad inputs
		if((clockWidth < minWidth) || (clockHeight < minHeight) || (0 >  				numOfSeconds)){
			System.out.println("BAD INPUT");
			System.exit(0);
		}else{
			new Countdown().startController(clockWidth,clockHeight);
		}
	}   	
	
}

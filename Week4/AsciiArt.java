/*************************************************************************
 *  Name: Kuangyi Yang
 *  ID: A53083212
 *  Email: kuy006@ucsd.edu
 *************************************************************************/

import java.util.Scanner;
import java.util.ArrayList;

public class AsciiArt
{
	private static ArrayList<AsciiShape> myShape = new ArrayList<AsciiShape>();
	private static AsciiGrid aGrid;

	// Create a new grid
	private static void NEW(String [] input)
	{	
		if(input.length == 1)
		{
			aGrid = new AsciiGrid();
			System.out.println("OK");
		}else if(input.length == 3)
		{
			int row = Integer.parseInt(input[1]);
			int col = Integer.parseInt(input[2]);
			if(row >= 0 && col >= 0)
			{
				aGrid = new AsciiGrid(row, col);
				System.out.println("OK");
			}else
			{
				System.out.println("BAD INPUT");
			}
		}else
		{
			System.out.println("BAD INPUT");
		}
	
	}

	// Create a triangle shape
	private static void setTriangle(String[] input)
	{		
		if(input.length >= 4)
		{
			char symbol = input[1].charAt(0);
			int height = Integer.parseInt(input[input.length-2]);
			int width = Integer.parseInt(input[input.length-1]); 
			if( 0 <= height && 0 <= width )
			{
				myShape.add(new AsciiShape(true, height, width, symbol));
				System.out.println("OK");
			}
			else
			{
				System.out.println("BAD INPUT");
			}
		}else
		{
			System.out.println("BAD INPUT");
		}	
	}

	// Create a new rectangle shape
	private static void setRectangle(String [] input)
	{	
		if(4 <= input.length)
		{
			char symbol = input[1].charAt(0);
			int height = Integer.parseInt(input[input.length-2]);
			int width = Integer.parseInt(input[input.length-1]); 
			if( 0 <= height && 0 <= width)
			{
				myShape.add(new AsciiShape(false, height, width, symbol));
				System.out.println("OK");
			}else
			{
				System.out.println("BAD INPUT");
			}
		}else
		{
			System.out.println("BAD INPUT");
		}	
	}

	// list the shapes by index
	private static void list(String[] input)
	{
		if(myShape != null)
		{
			if(input.length == 1)
			{
				for(int i = 0; i < myShape.size(); i++)
				{
					System.out.println(i+":");
					System.out.print(myShape.get(i).toString());
				}
				System.out.println("OK");
			}
			else
			{
				System.out.println("BAD INPUT");
			}
		}
		else
		{
			System.out.println("BAD INPUT");
		}
	}


	// Print the current state of the grid	
	private static void print(String[] input)
	{
		if(aGrid != null)
		{
			if(input.length == 1)
			{
				System.out.println(aGrid.toString());
			}else
			{
				System.out.println("BAD INPUT");
			}
		}else
		{
			System.out.println("BAD INPUT");
		}
	}

	// Placethe shape with index on the grid starting at (r,c)
	private static void place(String[] input)
	{	
		if(aGrid != null && myShape != null)
		{
			if(input.length == 4)
			{
				int index = Integer.parseInt(input[1]);
				int row = Integer.parseInt(input[2]);
				int col = Integer.parseInt(input[3]);
				if( index < myShape.size())
				{
					char [][] copyShape = myShape.get(index).getShape();
					if(aGrid.placeShape(copyShape,row,col) == true)
					{
						System.out.println("OK");
					}else
					{
						System.out.println("BAD INPUT");
					}
				}
				else
				{
					System.out.println("BAD INPUT");
				}
			}else
			{
				System.out.println("BAD INPUT");
			}
		}else
		{
			System.out.println("BAD INPUT");
		}
	}

	// Clear the area defined by shape with index at (r,c)
	private static void clear(String[] input)
	{	
		if(aGrid != null && myShape != null)
		{
			if(input.length == 4)
			{
				int index = Integer.parseInt(input[1]);
				int row = Integer.parseInt(input[2]);
				int col = Integer.parseInt(input[3]);
				if( index < myShape.size())
				{
					char [][] copyShape = myShape.get(index).getShape();
					if(aGrid.clearShape(copyShape,row,col) == true)
					{
						System.out.println("OK");
				
					}else
					{
						System.out.println("BAD INPUT");
					};
				}else
				{
					System.out.println("BAD INPUT");
				}
			}else
			{
				System.out.println("BAD INPUT");
			}
		}else
		{
			System.out.println("BAD INPUT");
		}
	}

	// Set the symbol of the shape with index to new symbol
	private static void set(String[] input)
	{
		if(myShape != null)
		{
			if(input.length == 3)
			{
				char symbol = input[1].charAt(0);
				int index = Integer.parseInt(input[2]);
				if(index < myShape.size() )
				{
					myShape.get(index).setSymbol(symbol);
					System.out.println("OK");
				}else
				{
					System.out.println("BAD INPUT");
				}
			}else
			{
				System.out.println("BAD INPUT");
			}
		}else
		{
			System.out.println("BAD INPUT");
		}
	}

	// Exit the program
	private static void exit(String[] input)
	{
		System.out.println("OK");
		System.exit(0);
	}


	public static void main (String[] args)
	{
		Scanner scnr = new Scanner(System.in);
		String prompt = "> ";
		System.out.print(prompt);
		while (scnr.hasNext())
		{	
			String[] myInput = scnr.nextLine().split("\\s+");
			// Implement specific command based on input
			if(myInput[0].toUpperCase().equals("TRIANGLE"))
			{
				setTriangle(myInput);
			}
			else if(myInput[0].toUpperCase().equals("RECTANGLE"))
			{
				setRectangle(myInput);
			}
			else if(myInput[0].toUpperCase().equals("LIST"))
			{
				list(myInput);
			}
			else if(myInput[0].toUpperCase().equals("NEW"))
			{
				NEW(myInput);
			}
			else if(myInput[0].toUpperCase().equals("PRINT"))
			{
				print(myInput);
			}
			else if(myInput[0].toUpperCase().equals("PLACE"))
			{
				place(myInput);
			}
			else if(myInput[0].toUpperCase().equals("CLEAR"))
			{
				clear(myInput);
			}
			else if(myInput[0].toUpperCase().equals("SET"))
			{
				set(myInput);
			}
			else if(myInput[0].toUpperCase().equals("EXIT"))
			{
				exit(myInput);
			}
			else
			{
				System.out.println("BAD INPUT");
			}
			System.out.print(prompt);		
		}
	}
}

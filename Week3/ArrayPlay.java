/*************************************************************************
 *  Name: Kuangyi Yang
 *  ID: A53083212
 *  Email: kuy006@ucsd.edu
 *************************************************************************/

import java.util.Scanner;

public class ArrayPlay
{
	private static IntArray11 myArray;

	// Method to create array based on input information
	public static void NEW(String[] input)
	{
		// Create an IntArray11 instance with no elements
		if(input.length == 1)
		{
			myArray = new IntArray11();
			System.out.println("OK");
		}
		// Create an IntArray11 instance with input size
		else if(input.length == 2)
		{
			int size = Integer.parseInt(input[1]);
			if(size != 0)
			{
				myArray = new IntArray11(size);
				System.out.println("OK");
			}
			else
			{
				System.out.println("BAD INPUT");
			}
		}
		// Create an IntArray1 instance with input contents
		else if(3 <= input.length)
		{
			int size = input.length - 1;
			int [] intArray = new int[size]; 
			for(int i = 0; i < size; i++)
			{
				intArray[i] = Integer.parseInt(input[i+1]);
			}
			myArray = new IntArray11(intArray);
			System.out.println("OK");
		}	
		
	}

	// Method to print array or element
	public static void PRINT(String[] input)
	{
		if(myArray != null)
		{
			// Print the current IntArray11
			if(input.length == 1)
			{
				System.out.println(myArray.toString());
				System.out.println("OK");
			}
			// Print the element at index of array
			else if(input.length == 2)
			{
				int index = Integer.parseInt(input[1]);
				if(0 <= index && index < myArray.getNelem())
				{
					System.out.println(myArray.getElement(index));
					System.out.println("OK");
				}
				else
				{
					System.out.println("BAD INPUT");
				}
			}
			else if(input.length != 1 || input.length != 2)
			{
				System.out.println("BAD INPUT");
			}
		}
		else
		{
			System.out.println("BAD INPUT");
		}
	}

	// Method to delete specific element from array
	public static void DELETE(String[] input)
	{
		
		if(myArray != null)
		{
			// Delete the element at index of array
			if(input.length == 2)
			{
				int index = Integer.parseInt(input[1]);
				if(myArray.delete(index) == true)
				{
					System.out.println("OK");
				}
				else
				{
					System.out.println("BAD INPUT");
				}
			}
			else if(input.length != 2)
			{
				System.out.println("BAD INPUT");
			}
		}
		else
		{
			System.out.println("BAD INPUT");
		}
	
	}

	// Method to insert element into specific index
	public static void INSERT(String[] input)
	{
		
		if(myArray != null)
		{
			// Insert the element at index of array
			if(input.length == 3)
			{
				int index = Integer.parseInt(input[1]);
				int element = Integer.parseInt(input[2]);
				if(myArray.insert(index,element) == true)
				{
					System.out.println("OK");
				}
				else
				{
					System.out.println("BAD INPUT");
				}
			}
			else if(input.length != 3)
			{
				System.out.println("BAD INPUT");
			}
		}
		else
		{
			System.out.println("BAD INPUT");
		}

	}

	// Method to reverse whole array or part of array
	public static void REVERSE(String[] input)
	{
		if(myArray != null)
		{
			// Reverse the order of elements in the array
			if(input.length == 1)
			{
				myArray.reverse();
				System.out.println("OK");
			}
			// Reverse the order of elements in array from start to end index
			else if(input.length == 3)
			{
				int start = Integer.parseInt(input[1]);
				int end = Integer.parseInt(input[2]);
				if(myArray.reverse(start,end) == true)
				{
					System.out.println("OK");
				}
				else
				{
					System.out.println("BAD INPUT");
				}
			}
			else if(input.length != 1 || input.length != 3)
			{
				System.out.println("BAD INPUT");
			}
		}
		else
		{
			System.out.println("BAD INPUT");
		}
	}

	// Method to set value in specific index
	public static void SET(String[] input)
	{
		if(myArray != null)
		{
			// Store element at index in array
			if(input.length == 3)
			{
				int index = Integer.parseInt(input[1]);
				int element = Integer.parseInt(input[2]);
				if(myArray.setElement(index,element))
				{
					System.out.println("OK");
				}
				else
				{
					System.out.println("BAD INPUT");
				}
			}
			else if(input.length != 3)
			{
				System.out.println("BAD INPUT");
			}
		}
		else
		{
			System.out.println("BAD INPUT");
		}

	}

	// Method to print the number elements currently stored in array
	public static void SIZE(String[] input)
	{
		if(myArray != null)
		{
			if(input.length == 1)
			{
				int size = myArray.getNelem();
				System.out.println(size);
				System.out.println("OK");
			}
			else if(input.length != 1)
			{
				System.out.println("BAD INPUT");
			}
		}
		else
		{
			System.out.println("BAD INPUT");
		}

	}

	// Swap the elements at index1 and index2 of the array
	public static void SWAP(String[] input)
	{
		if(myArray != null)
		{
			if(input.length == 3)
			{
				int index1 = Integer.parseInt(input[1]);
				int index2 = Integer.parseInt(input[2]);
				if(myArray.swap(index1,index2)==true)
				{
					System.out.println("OK");
				}
				else
				{
					System.out.println("BAD INPUT");
				}
			}
			else if(input.length != 3)
			{
				System.out.println("BAD INPUT");
			}
		}
		else
		{
			System.out.println("BAD INPUT");
		}

	}

	// Exit the program
	public static void EXIT(String[] input)
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
			if(myInput[0].toUpperCase().equals("NEW"))
			{
				NEW(myInput);
			}
			else if(myInput[0].toUpperCase().equals("PRINT"))
			{
				PRINT(myInput);
			}
			else if(myInput[0].toUpperCase().equals("DELETE"))
			{
				DELETE(myInput);
			}
			else if(myInput[0].toUpperCase().equals("INSERT"))
			{
				INSERT(myInput);
			}
			else if(myInput[0].toUpperCase().equals("REVERSE"))
			{
				REVERSE(myInput);
			}
			else if(myInput[0].toUpperCase().equals("SET"))
			{
				SET(myInput);
			}
			else if(myInput[0].toUpperCase().equals("SIZE"))
			{
				SIZE(myInput);
			}
			else if(myInput[0].toUpperCase().equals("SWAP"))
			{
				SWAP(myInput);
			}
			else if(myInput[0].toUpperCase().equals("EXIT"))
			{
				EXIT(myInput);
			}
			else
			{
				System.out.println("BAD INPUT");
			}
			System.out.print(prompt);		
		}
	}
}

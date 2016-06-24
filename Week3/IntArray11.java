/**
 * Provide a set of Array functions on an Array of ints
 * @author KUANGYI YANG
 * @version CSE11-Winter2015-PR3
 */
public class IntArray11
{
	private int[] anArray;
	private int numOfElts;
	
	/** 
 	 * 0-argument constructor. Valid instance of an Array of int,
 	 * no ints are stored in the array.
	*/
	public IntArray11()
	{
		anArray = new int[0];
		numOfElts = 0;	 
	}

	/** 
 	 * Store an array of size n. Initialize contents of the array to 
 	 * be 1..n 
 	 * @param size the number of elements to store in the array
	*/
	public IntArray11(int size)
	{
		anArray = new int[size];
		for(int i = 0; i < size; i++)
		{
			anArray[i] = i + 1; 
		}
		numOfElts = size;
	}

	/** 
 	 * Create an array of size n and store a copy of the contents of the
 	 * input argument
 	 * @param intArray array of elements to copy 
	*/
	public IntArray11(int[] intArray)
	{
		anArray = new int[intArray.length];
		numOfElts = intArray.length;
		for(int i = 0; i < numOfElts; i++)
		{
			anArray[i] = intArray[i];
		}
	}

	/* Make a string representation */
	/**
	 * Pretty Print  -- Empty String "[]"
	 *                  else "[e1, e2, ..., en]"
	 */
	@Override
	public String toString()
	{
		String newString = new String();
		String e = "";
		if(numOfElts != 0)
		{
			newString = newString + "[";
			for(int i = 0; i < numOfElts - 1; i++)
			{
				e = Integer.toString(anArray[i]) + ", "; 
				newString = newString + e;
			}
			newString = newString + Integer.toString(anArray[numOfElts-1]);
			newString = newString + "]";
			return newString;
		}else
		{
			return "[]";
		} 
	}

	/* Getters and Setters */

	/** get the number of elements stored in the array  
	 * @return number of elements in the array
	*/
	public int getNelem()
	{
		numOfElts = anArray.length;	
		return numOfElts; 
	}
	/** get the Element at index  
	 * @param index of data to retrieve 
	 * @return element if index is valid else  return 
	 * 		Integer.MIN_VALUE
	*/
	public int getElement(int index)
	{
		if(0 <= index && numOfElts > index)
		{
			int getElt = anArray[index];
			return getElt; 
		}else
		{ 
		return Integer.MIN_VALUE;
		}
	}
	
	/** retrieve a copy of the stored Array
	 * @return a deep copy of the Array. A new int array should be
	 * 		constructed of the correct size and values should
	 * 		copied into it.  
	*/
	public int[] getArray()
	{
		if(0 < numOfElts)
		{
			int [] copyArray = new int[numOfElts];
			for(int i = 0; i < numOfElts; i++)
			{
				copyArray[i] = anArray[i];
			}
			return copyArray;
		}else
		{		
			return new int[0];
		}
	}

	/** set the value of an element in the stored array
	 * @param index of element to store. Must be a valid index 
	 * @param element the data to insert in the array
	 * @return true if element set was successful
	*/
	public boolean setElement(int index, int element)
	{
		if(0 <= index && numOfElts > index)
		{
			anArray[index] = element;
			return true;
		}else
		{
			return false;
		}
	}

	/** Insert an element at index in the array
	 * @param index where to insert. Must be between 0 and number of
	 *              elements in the array
	 * @param element the data to insert in the array
	 * @return true if element insertion was successful
	*/
	public boolean insert(int index, int element)
	{
		if (index >= 0 && index <= numOfElts) 
		{
			int[] temp = new int[numOfElts];
			for(int i=0; i < numOfElts; i++)
			{
				temp[i] = anArray[i];
			}
			anArray = new int[numOfElts + 1];
			for (int i = 0; i < index; i++) 
			{
				anArray[i] = temp[i];
			}
			anArray[index] = element;
			for (int i = index; i < numOfElts; i++)
			{
				anArray[i+1] = temp[i];
			}
			numOfElts = numOfElts + 1;
			return true;
		}else
		{
			return false;
		}
	
	}

	/** Delete and element at index
	 * @param index of element to delete 
	 * @return true if element deletion was successful, false otherwise
	*/
	public boolean delete(int index)
	{
		if(0 <= index && numOfElts > index)
		{
			int [] temp = new int[numOfElts];
			for(int i=0; i < numOfElts; i++)
			{
				temp[i] = anArray[i];
			}
			anArray = new int[numOfElts - 1];
			for(int i = 0; i <= index-1; i++)
			{
				anArray[i] = temp[i];
			}
			for(int i = index; i < numOfElts-1; i++)
			{
				anArray[i] = temp[i+1];
			}
			numOfElts = numOfElts - 1;
			return true;
		}else
		{
			return false;
		}
	}

	/** reverse the order of the elements in the array 
	*/
	public void reverse()
	{
		int[] temp = new int[numOfElts];
		for(int i = 0; i < numOfElts; i++)
		{
			temp[i] = anArray[numOfElts-i-1];
		}
		for(int i = 0; i < numOfElts; i++)
		{
			anArray[i] = temp[i];
		}
	}

	/** reverse the order of the elements in the array from start to
 	*   end index 
	*   @param start beginning index of to start the reverse
	*   @param end	ending index to end the reverse
	*   @return true if start and end index are valid, false otherwise
	*
	*/
	public boolean reverse(int start, int end)
	{
		if(0 <= start && numOfElts > end && start <= end)
		{
			int [] temp = new int[numOfElts];
			for(int i = 0; i <= start-1; i++)
			{
				temp[i] = anArray[i];
			}
			for(int i = start; i <= end; i++)
			{
				temp[i] = anArray[start+end-i];
			}
			for(int i = end+1; i < numOfElts; i++)
			{
				temp[i] = anArray[i];
			}
			for(int i = 0; i < numOfElts; i++)
			{
				anArray[i] = temp[i];
			}

			return true;
		}else
		{
			return false;
		}
	}

	/** swap two elements in the array 
	*   @param index1 index of first element 
	*   @param index2 index of second element
	*   @return true if index1 and index2 are valid, false otherwise
	*
	*/
	public boolean swap(int index1, int index2)
	{
		int temp = 0;
		if(0 <= index1 && 0 <= index1 && numOfElts > index2 && numOfElts > index2)
		{
			temp = anArray[index1];
			anArray[index1] = anArray[index2];
			anArray[index2] = temp;
			return true;
		}else
		{
			return false;
		}
	}
}
// vim: ts=4:sw=4:tw=78:

/** Create a pair of InputStream/OutputStream  objects
 * based what was asked for in the constructor
 * @author KUANGYI YANG A53083212
 * @version 15 Feb 2015
 */
import java.io.InputStream;
import java.io.OutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.File;
public class StreamPair 
{
	private InputStream input;
	private OutputStream output;
	/** Constructor
	 * @param infile  Name of the input file. "-" means standard input
	 * @param outfile  Name of the output file. "-" means standard output
	 * @throws IOException  file related exceptions
	 */
	public StreamPair(String infile, String outfile) throws IOException
	{
		if(infile.equals("-"))
		{
			input = System.in;
		}
		else if(infile.isEmpty())
		{
			input = System.in;
		}
		else
		{
			input = new FileInputStream(infile);
		}

		if(outfile.equals("-"))
		{
			output = System.out;
		}
		else if(outfile.isEmpty())
		{
			output = System.out;
		}
		else
		{
			output = new FileOutputStream(outfile);
		}
	}

	/** get a reference to this stream pair's input stream
	 * @return  reference to the input stream
	 */
	public InputStream getInput()
	{
		return input;
	}

	/** get a reference to this stream pair's output stream
	 * @return  reference to the output stream
	 */
	public OutputStream getOutput()
	{
		return output;
	}

	/** close both streams. Should not cause an error if invoked multiple times
	 */
	public void close()
	{
		try
		{
			input.close();
			output.close();
		}
		catch(IOException excpt)
		{
			System.out.println(excpt.getClass().getName() + ": " + excpt.getMessage());
			return;
		}
	}
}
// vim: ts=4:sw=4:tw=78

/** A class that encrypts/decrypts
 * @author KUANGYI YANG
 * @version 12/Feb/2015
 */
import java.io.IOException;
public abstract class CryptStream
{
	protected StreamPair streams;
	/** Constructor 
	 * @param theStreams a constructed StreamPair Instance
	 */
	public CryptStream(StreamPair theStreams)
	{
		this.streams = theStreams;
	}
	/** Encrypt data in the byte array
	 * @param data - the data to encrypt
	 * @param len - how many bytes in the array to encrypt
	 * @return a byte array with data encrypted. length may not be equal to
	 * input
	 */
	abstract protected byte [] cryptData( byte [] data, int len); 
	/** Decrypt data in the byte array
	 * @param data - the data to decrypt
	 * @param len - how many bytes in the array to decrypt
	 * @return a byte array with data decrypted. length may not be equal to
	 * input
	 */
	abstract protected byte [] decryptData( byte [] data,int len);

	
	/** encrypt the input stream, and write to the output stream of 
     * of the StreamPair 
	*  @return number of bytes in output stream
	*/
	public final int encrypt()
	{
		// number of bytes stored in output
		int outSize = 0;
		// number of bytes stored in buffer
		int inSize = 0;
		byte [] buffer = new byte[1024];
		byte [] output = new byte[1024];
		
		try
		{
			do{
				output = cryptData(buffer,inSize);
				outSize = outSize + output.length;
				streams.getOutput().write(output);
				inSize = streams.getInput().read(buffer);
			}while(inSize != -1);
		}
		catch(IOException excpt)
		{
			System.out.println(excpt.getClass().getName()+":"+excpt.getMessage());
		}
		finally
		{
			streams.close();
			return outSize;
		}
	}
	/** decrypt the input stream, and write to the output stream of 
     * of the StreamPair 
	*  @return number of bytes in output stream
	*/
	public final int decrypt()
	{
		int outSize = 0;
		int inSize = 0;
		byte [] buffer = new byte[1024];
		byte [] output = new byte[1024];
		
		try
		{
			do{
				output = decryptData(buffer,inSize);
				outSize = outSize + output.length;
				streams.getOutput().write(output);
				inSize = streams.getInput().read(buffer);
			}while(inSize  != -1);
		}
		catch(IOException excpt)
		{
			System.out.println(excpt.getClass().getName() + ":" + excpt.getMessage());
		}
		finally
		{
			streams.close();
		} 
		return outSize;
	}
}
// vim: ts=4:sw=4:tw=78

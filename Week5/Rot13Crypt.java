/** 
 * Name: KUANGYI YANG
 * Pid: A53083212
 * Email: kuy006@ucsd.edu
 */

public class Rot13Crypt extends CryptStream
{
	public Rot13Crypt(StreamPair theStreams)
	{
		super(theStreams);
	}

	@Override
	protected byte [] cryptData( byte [] data, int len)
	{
		byte [] crypt = new byte[len];
		for(int i = 0; i < len; ++i)
		{
			crypt[i] = (byte) ((data[i] + 13) % 256);
		}
		return crypt;
	}
	
	@Override
	protected byte [] decryptData( byte [] data,int len)
	{
		byte [] decrypt = new byte[len];
		for(int i = 0; i < len; ++i)
		{
			decrypt[i] = (byte)((data[i]-13)%256);
		}
		return decrypt;	
	}
}
// vim: ts=4:sw=4:tw=78

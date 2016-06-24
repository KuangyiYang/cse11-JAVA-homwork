/** 
 * Name: KUANGYI YANG
 * Pid: A53083212
 * Email: kuy006@ucsd.edu
 */

public class PlainCrypt extends CryptStream
{
	public PlainCrypt(StreamPair theStreams)
	{
		super(theStreams);
	}

	@Override
	protected byte [] cryptData( byte [] data, int len)
	{
		byte [] crypt = new byte[len];
		for(int i = 0; i < len; ++i)
		{
			crypt[i] = data[i];
		}
		return crypt;
	}
	
	@Override
	protected byte [] decryptData( byte [] data,int len)
	{
		byte [] decrypt = new byte[len];
		for(int i = 0; i < len; ++i)
		{
			decrypt[i] = data[i];
		}
		return decrypt;	
	}
}
// vim: ts=4:sw=4:tw=78

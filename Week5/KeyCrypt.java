/** 
 * Name: KUANGYI YANG
 * Pid: A53083212
 * Email: kuy006@ucsd.edu
 */

public class KeyCrypt extends CryptStream
{
	private int sum;
	
	public KeyCrypt(StreamPair theStreams, String key)
	{
		super(theStreams);
		byte [] byteKey = key.getBytes();
		for(int i = 0; i < byteKey.length; ++i)
		{
			sum = sum + byteKey[i];
		}
	}

	@Override
	protected byte [] cryptData( byte [] data, int len)
	{
		byte [] crypt = new byte[len];

		for(int i = 0; i < len; i++)
		{
			crypt[i] = (byte) (data[i]^sum);
		}
		return crypt;
	}
	
	@Override
	protected byte [] decryptData( byte [] data,int len)
	{
		byte [] decrypt = new byte[len];
		
		for(int i = 0; i < len; ++i)
		{
			decrypt[i] = (byte) (data[i]^sum);
		}
		return decrypt;	
	}
}
// vim: ts=4:sw=4:tw=78

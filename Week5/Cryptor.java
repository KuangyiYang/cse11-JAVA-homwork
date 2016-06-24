/** 
 * KUANGYI YANG
 * PID: A53083212
 * email: kuy006@ucsd.edu
 */

import java.io.InputStream;
import java.io.OutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.File;

public class Cryptor
{
	public static void main(String[] args)
	{
		String infile = "";
		String outfile = "";
		String algorithm = "plain";
		String key = "";

		StreamPair sInstance = null;
		CryptStream cInstance = null;

		String command_1 = "";
		String command_2 = "";
		boolean logic = true;

		// detectable error or "help"
		if( (args.length != 0) && (args[0].equals("help") || (args.length % 2) != 0) )
		{
			System.err.println("Cryptor [-d algorithm] [-e algorithm] [-k key] [-i infile] [-o outfile]");
			System.err.println("         algorithm: plain rot13 key");
			System.exit(-1);
			return;
		}
		// read command line arguments
		for(int i = 0; i < args.length; i = i + 2)
		{
			command_1 = args[i];
			command_2 = args[i + 1];
			if(command_1.equals("-i"))
			{
				infile = command_2;
			}
			else if (command_1.equals("-o"))
			{
				outfile = command_2;
			}
			else if (command_1.equals("-d"))
			{
				algorithm = command_2;
				logic = false;
			}
			else if (command_1.equals("-e")) 
			{
				algorithm = command_2;
				logic = true;
			}
			else if (command_1.equals("-k")) 
			{
				key = command_2;
			}else
			{
				System.err.printf("Unknown flag: '" + command_1 + "'\n");
				System.err.println("Cryptor [-d algorithm] [-e algorithm] [-k key] [-i infile] [-o outfile]");
				System.err.println("         algorithm:plain rot13 key");
				System.exit(-1);
				return;
			}
		}
		// implement StreamPair instance
		try
		{
			sInstance =  new StreamPair(infile,outfile);
		}
		catch(IOException excpt)
		{
			System.err.println(excpt.getClass().getName() + ":  " + excpt.getMessage());
			System.err.println("Cryptor [-d algorithm] [-e algorithm] [-k key] [-i infile] [-o outfile]");
			System.err.println("         algorithm: plain rot13 key");
			System.exit(-1);
			return;
		}
		// implement CryptStream Instance
		if(algorithm.equals("plain"))
		{
			cInstance = new PlainCrypt(sInstance);
		}
		else if(algorithm.equals("rot13"))
		{
			cInstance = new Rot13Crypt(sInstance);
		}
		else if(algorithm.equals("key"))
		{
			cInstance = new KeyCrypt(sInstance,key);
		}
		else
		{
			System.err.printf("Unknown algorithm: '" + algorithm + "'\n");
			System.err.println("Cryptor [-d algorithm] [-e algorithm] [-k key] [-i infile] [-o outfile]");
			System.err.println("         algorithm:plain rot13 key");
			System.exit(-1);
			return;
		}

		if (logic == true)
		{
			cInstance.encrypt();
		}
		else
		{
			cInstance.decrypt();
		}	
	}
}
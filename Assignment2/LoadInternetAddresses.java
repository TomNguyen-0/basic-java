package dns_resolver;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import data_structures.Hash;
import data_structures.HashI;
import exceptions.FileFormatException;

/**
 * The LoadInternetAddresses class should take a filename as a string, uses BufferedReader
 * to read the file, split the lines into URLs and IPAddresses, and create the appropriate
 * objects. It should add those objects to a hash, and finally, after reading the whole file
 * it should return the instance of the hash.
 * 
 * If there is an error with the file format, you should throw a new FileFormatException error
 * with an appropriate message.
 *  
 * @author Tom Nguyen
 * @version: Neon Release (4.6.0)- Eclipse. jre 1.8.0_92
 */
public class LoadInternetAddresses {
	String [] values;
	IPAddress ip;
	URL url;
	HashI<URL, IPAddress> ans = new Hash<URL,IPAddress>(3);
	/**
	 * Takes the string one line at a time from a file and split it into 
	 * IP addresses and URLs. After splitting it add those objects into
	 * the hash and then returns the instance of the hash.
	 * 
	 * @param filename is the file name that contains the IP address and 
	 * URLs.
	 * @return the instance of the hash.
	 * @throws FileFormatException when the file is not formated as:
	 * [IP address + "tab" + URL].
	 */
	public HashI<URL, IPAddress> load_addresses(String filename) throws FileFormatException{
		try {
			FileReader infile = new FileReader(filename);
			BufferedReader br = new BufferedReader(infile);
			String line;
			while((line= br.readLine()) != null){
				values = line.split("\t");
				ip = new IPAddress(values[0]);
				url = new URL(values[1]);
				ans.add(url, ip);
			}
			infile.close();
		} catch (FileNotFoundException e) {
			System.err.println("There was a file format exception");
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("There was an error with the reading");
			e.printStackTrace();
		}
		return ans;
	}
}

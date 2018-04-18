package dns_resolver;

import exceptions.FileFormatException;

/**
 * The IPAddress is using iIPv4 and has dotted-decimal notation, with the network, two subnets, 
 * and host separated by periods. For example, the IP address 130.191.226.146 has 
 * a network of 130, a subnet of 191, the second subnet is 226, and the host address is 146.
 * 
 * Your IPAddress class should accept a string of dotted-decimal IPAddresses in the constructor
 * and separate them into the components. 
 * 
 * @see <a href="https://en.wikipedia.org/wiki/IP_address#IPv4_addresses">Wikipedia IPv4 addresses</a>
 * 
 * @author Tom Nguyen
 * @version: Neon Release (4.6.0)- Eclipse. jre 1.8.0_92.
 */

public class IPAddress {
	int network;
	int subnet;
	int subnet2;
	int host;
	String ipString;
	int array[]=new int[4];

	/**
	 * The constructor for the IPAddress class
	 * throws exception when there are letters, numbers
	 * greater than 255 or less than 0, and symbols.
	 * 
	 * @param ip the dotted-decimal IP address
	 * @throws FileFormatException if there are problems with the IP Address.
	 * 
	 */
	public IPAddress(String ip){
		ipString = ip;
		String data[] = ip.split("\\.");
		try{
			network = array[0]=Integer.parseInt(data[0]);
			subnet = array[1]=Integer.parseInt(data[1]);
			subnet2 = array[2]=Integer.parseInt(data[2]);
			host = array[3]=Integer.parseInt(data[3]);
		} catch (NumberFormatException e) {
			System.err.println("There was a Number Format Issue: "+ data[0]+"."+ data[1]+"."+  data[2]+"."+  data[3]);
			e.printStackTrace();
		}
		for(int i=0;i<4;i++){
			if(array[i]>255 || array[i]<0){
				NumberFormatException error = new NumberFormatException();
				System.err.println("There was an IP Address error: "+ data[0]+"."+ data[1]+"."+  data[2]+"."+  data[3]);
				System.err.println("For input string: \""+array[i]+"\"");
				error.printStackTrace();
			}
		}
	}

	/**
	 * Turn the IP address into a hash code for the hash table.
	 * @return the hash code of the IP address.
	 */
	@Override
	public int hashCode() {
		int g=3;
		int hash=0;
		for(int i=0;i<4;i++){
			hash= g*hash+array[i];
		}
		return hash;
	}

	/**
	 * Compares a IP address to another one and returns true if they are
	 * the same.
	 * @return true if the IP Address are the same, otherwise false.
	 */
	public boolean equals(Object obj) {
		if(obj == null)
			return false;
		if(this == obj)
			return true;
		if(getClass() != obj.getClass())
			return false;
		IPAddress test = (IPAddress) obj;
		if(network != test.network)
			return false;
		if(subnet != test.subnet)
			return false;
		if(subnet2 != test.subnet2)
			return false;
		if(host != test.host)
			return false;
		return true;
	}

	/**
	 * Returns a string containing the IP address.
	 * 
	 * @return the IP address as a string.
	 */
	@Override
	public String toString() {
		return ipString;
	}
}

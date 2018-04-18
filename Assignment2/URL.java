package dns_resolver;

/**
 * A URL Object is a representation of the URL that we have been giving. 
 * It knows how to compare URLs!
 * 
 * @author Tom Nguyen
 * @version: Neon Release (4.6.0)- Eclipse. jre 1.8.0_92
 */
public class URL implements Comparable<URL> {

	String webaddress;

	/**
	 * Takes the string and turn it into a URL.
	 * @param string the URL that are to be converted.
	 */
	public URL(String string) {
		webaddress = string;
	}

	/**
	 * Take two URLs and compare them to each other.
	 * @param obj the URL that is to be compared to.
	 * @return an integer of the compared URLs. If the integer is not a zero
	 * than the URLs are not equal.
	 */
	@Override
	public int compareTo(URL obj) {
		return webaddress.compareTo(obj.webaddress);
	}

	/**
	 * Test two URL strings to see if they are the same.
	 * @param url the url that will be compared to.
	 * @return true if they are the same, otherwise false.
	 */
	public boolean equals(URL url){
		return webaddress.equals(url.webaddress);
	}

	/**
	 * Returns the url as a string.
	 * @return the string of the url.
	 */
	public String toString(){
		return webaddress;
	}
	/**
	 * Uses the default hash code to hash the web address string.
	 * @return the hash code of the url.
	 */
	public int hashCode(){
		return webaddress.hashCode();
	}
}

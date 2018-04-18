package exceptions;

/**
 * The file format exception is thrown when trying to read a file
 * that is not in the right format.   
 *  
 * @author Tom Nguyen
 * @version: Neon Release (4.6.0)- Eclipse. jre 1.8.0_92
 */
@SuppressWarnings("serial")
public class FileFormatException extends Exception {
	/**
	 * Helps identify the error for the user and programmer, uses default exception. 
	 */
	public FileFormatException(){
		super();
	}
	/**
	 * Helps identify the error and print out a message.
	 * @param s is a string that contains the message.
	 */
	public FileFormatException(String s){
		super(s);
	}

}

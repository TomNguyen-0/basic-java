package data_structures;

import java.util.Iterator;

/**
 * This stack uses the Last-In-First-Out (LIFO) concept,
 * using methods addFirst and removeFirst from the linked list.
 * The stack relies on the LinkedList.java class for its methods execution.
 * 
 * @author Tom Nguyen <tommie_89@yahoo.com>
 * 
 * @version: Neon Release (4.6.0)- Eclipse. jre 1.8.0_92
 *
 *@see LinkedList.java.
 * @param <E> the type of elements in the stack.
 */
public class Stack<E> {
	/**
	 * Description of show()
	 * 
	 * prints out the head, linked list, tail, and currentSize.
	 * 
	 * For debugging/testing only.
	 **/
	public void show(){
		list.show();
	}
	private ListI<E> list;
	/**
	 * The constructor for the class Stack.
	 * Initialized the list to be a new linked list.
	 */
	public Stack(){
		list=new LinkedList<>();
	}
	/**
	 * Add an object to the top of the stack.
	 * This is the same as addFirst.
	 * 
	 * @param obj the object that is added to the top of the stack.
	 */
	public void push(E obj){
		list.addFirst(obj);
	}
	/**
	 * Return the object and remove the object from the top of the stack.
	 * this is the same as removeFirst.
	 * 
	 * @return the data from the top of the stack.
	 */
	public E pop(){
		return list.removeFirst();
	}
	/**
	 * Returns the size of the stack.
	 * 
	 * @return the current size of the stack.
	 */
	public int size(){
		return list.size();
	}
	/**
	 * Test whether the stack is empty.
	 * 
	 * @return true if the list is empty, otherwise false;
	 */
	public boolean isEmpty() {
		return list.isEmpty();
	}
	/**
	 * Returns false, hard coded to always return false.
	 * Test whether the stack is full.
	 * 
	 * @return false if the list is not full, otherwise the system will crash.
	 */
	public boolean isFull() {
		return list.isFull();
	}
	/**
	 * Looks at the top of the stack without removing the object.
	 * Returns the object from the top of the stack.
	 * 
	 * @return the object without deleting from the top of the stack.
	 */
	public E peek() {
		return list.peekFirst();
	}
	/**
	 * Test whether the stack contains an object.
	 * This will use the object's compareTo method to determine whether two
	 * objects are the same.
	 * 
	 * @param obj the object to look for in the stack.
	 * @return true if the object is found in the stack, otherwise false.
	 */
	public boolean contains(E obj) {
		return list.contains(obj);
	}  
	/**
	 * Return the stack to an empty state.
	 */
	public void makeEmpty() {
		list.makeEmpty();
	}
	/**
	 * Returns an Iterator of the values in the stack,
	 * presented the same order as the stack.
	 * 
	 * @return an iterator of values presented in the stack.
	 * @see java.lang.Iterable#iterator()
	 */
	public Iterator<E> iterator() {
		return list.iterator();

	}
}

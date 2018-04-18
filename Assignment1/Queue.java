package data_structures;

import java.util.Iterator;

/**
 * This queue uses the First-In-First-Out (FIFO) concept,
 * using methods addLast and removeFirst from the linked list.
 * The queue relies on the LinkedList.java class for its methods execution.
 * 
 * @author Tom Nguyen <tommie_89@yahoo.com>
 * 
 * @version: Neon Release (4.6.0)- Eclipse. jre 1.8.0_92
 *
 *@see LinkedList.java.
 * @param <E> the type of elements in the queue.
 */
public class Queue<E> {

	private ListI<E> list;
	/**
	 * The constructor for the class Queue.
	 * Initialized the list to be a new linked list.
	 */
	public Queue(){
		list = new LinkedList<>();
	}
	/**
	 * Add an object to the bottom of the queue.
	 * This is the same as addLast.
	 * 
	 * @param obj the object that is added to the bottom of the queue.
	 */
	public void enqueue(E obj) {
		list.addLast(obj);
	}   
	/**
	 * Return the object and remove the object from the top of the queue.
	 * this is the same as removeFirst.
	 * 
	 * @return the data from the top of the queue.
	 */
	public E dequeue() {
		return list.removeFirst();
	}   
	/**
	 * Returns the size of the queue.
	 * 
	 * @return the current size of the queue.
	 */
	public int size() {
		return list.size();
	}
	/**
	 * Test whether the queue is empty.
	 * 
	 * @return true if the list is empty, otherwise false;
	 */
	public boolean isEmpty() {
		return list.isEmpty();
	}
	/**
	 * Returns false, hard coded to always return false.
	 * Test whether the queue is full.
	 * 
	 * @return false if the list is not full, otherwise the system will crash.
	 */
	public boolean isFull(){
		return list.isFull();
	}
	/**
	 * Looks at the top of the queue without removing the object.
	 * Returns the object from the top of the queue.
	 * 
	 * @return the object without deleting from the top of the queue.
	 */
	public E peek() {
		return list.peekFirst();
	}
	/**
	 * Test whether the queue contains an object.
	 * This will use the object's compareTo method to determine whether two
	 * objects are the same.
	 * 
	 * @param obj the object to look for in the queue.
	 * @return true if the object is found in the queue, otherwise false.
	 */
	public boolean contains(E obj) {
		return list.contains(obj);
	}
	/**
	 * Return the queue to an empty state.
	 */
	public void makeEmpty() {
		list.makeEmpty();
	} 
	/**
	 * Returns an Iterator of the values in the queue,
	 * presented the same order as the queue.
	 * 
	 * @return an iterator of values presented in the queue.
	 * @see java.lang.Iterable#iterator()
	 */
	public Iterator<E> iterator() {
		return list.iterator();
	}
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

}

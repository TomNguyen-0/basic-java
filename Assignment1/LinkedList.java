package data_structures;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * This linked list is a single Linked List type.
 * 
 * The linked list implements the list interface, ListI. 
 * 
 * @author Tom Nguyen <tommie_89@yahoo.com>.
 * 
 * @version: Neon Release (4.6.0)- Eclipse. jre 1.8.0_92
 * 
 * @param <E> the type of elements in this Linked List.
 */

public class LinkedList<E> implements ListI<E> {

	/**
	 * Description of show()
	 * 
	 * prints out the head, linked list, tail, and currentSize.
	 * 
	 * For debugging/testing only.
	 **/
	public void show(){
		Node<E> tmp = head;
		while (tmp != null){
			if(tmp == head)
				System.out.println("Node Head: " + tmp.data);
			System.out.print(" " +tmp.data);
			if(tmp == tail)
				System.out.println("\nNode Tail: " + tmp.data);
			tmp = tmp.next;
		}
		System.out.println("currentSize: " + currentSize);

	}

	/**
	 * pointers of the linked list (head,tail).
	 */
	public Node<E> head,tail;

	/**
	 * counts the size of the linked list.
	 */
	public int currentSize;

	/**
	 * A single node to be used for the linked list.
	 *
	 * @param <E> the type of elements in the node.
	 */

	@SuppressWarnings("hiding")
	class Node<E>{
		E data;
		Node<E> next;

		/**
		 * set object into data and pointer to null.
		 * 
		 * @param obj the type of element that is passed into the node.
		 */
		public Node (E obj){
			data = obj;
			next= null;
		}
	}

	/**
	 * Constructor for the linked list. 
	 * The variable head and currentSize initialized.
	 */
	public LinkedList(){
		head = null;
		currentSize = 0;
	}

	/**
	 * Adds an object to the end of the linked list.
	 * 
	 * @param obj the object that is to be added to the linked list.
	 */
	@Override
	public void addFirst(E obj) {
		Node<E> node = new Node<E>(obj);
		if  (head == null){
			ifSingle(obj);
			return;
		}
		node.next = head;
		head = node;
		currentSize ++;
		return;		
	}

	/**
	 * Adds to an empty linked list for addFirst and addLast methods.
	 * Add the first node to the linked list.
	 * Only use if the linked list is empty.
	 * 
	 * @param obj the object that is to be added to the linked list.
	 */
	public void ifSingle(E obj){
		head = tail = new Node<E>(obj);
		currentSize ++;
	}

	/**
	 * Adds an object to the end of the linked list.
	 * 
	 * @param obj the object to be added to the linked list.
	 */
	@Override
	public void addLast(E obj) {
		Node<E> node = new Node<E>(obj);
		if  (head == null){
			ifSingle(obj);
			return;
		}
		tail.next = node;
		tail = node;
		currentSize++;
		return;
	}

	/**
	 * Removes the first object in the list and returns it.
	 * 
	 * @return the object removed. If the linked list is empty return null.
	 */
	@Override
	public E removeFirst() {
		if(isEmpty())
			return null;
		E tmp = head.data;
		if(head.next == null)
			head = tail = null;
		else
			head = head.next;
		currentSize --;
		return tmp;
	}

	/**
	 * Remove the last object in the linked list and returns it.
	 * 
	 * @return the object removed. If the linked list is empty return null.
	 */
	@Override
	public E removeLast() {
		if(isEmpty())
			return removeFirst();
		if(head == tail)
			return removeFirst();
		E tmp = tail.data;
		Node<E> current = head, previous =null;
		while(current != tail){
			previous = current;
			current = current.next;
		}
		previous.next = null;
		tail = previous;
		currentSize --;
		return tmp;
	}

	/**
	 * Returns the first Object in the linked list and does not delete it.
	 * Returns null if the linked list is empty.
	 * @return the Object at the beginning of the linked list.
	 */
	@Override
	public E peekFirst() {
		if(isEmpty())
			return null;
		return head.data;
	}
	/**
	 * Returns the last Object in the linked list and does delete it.
	 * Returns null if the linked list is empty.
	 * @return the object at the end of the linked list.
	 */
	@Override
	public E peekLast() {
		if(isEmpty())
			return null;
		return tail.data;
	}
	/**
	 * Return the linked list to an empty state.
	 */
	@Override
	public void makeEmpty() {
		head = tail = null;
		currentSize = 0;
	}

	/**
	 * Test whether the linked list is empty.
	 * 
	 * @return true if the list is empty, otherwise false;
	 */
	@Override
	public boolean isEmpty() {
		return head == null;
	}

	/**
	 * Returns false, hard coded to always return false.
	 * Test whether the list is full.
	 * 
	 * @return false if the list is not full, otherwise the system will crash.
	 */
	@Override
	public boolean isFull() {
		return false;
	}
	/**
	 * Returns the number of Objects currently in the linked list.
	 * @return the number of Objects in the linked list.
	 */
	@Override
	public int size() {
		return currentSize;
	}

	/**
	 * Test whether the list contains an object.
	 * This will use the object's compareTo method to determine whether two
	 * objects are the same.
	 * 
	 * @param obj the object to look for in the list.
	 * @return true if the object is found in the list, otherwise false.
	 */
	@SuppressWarnings({ "unchecked", "unused" })
	@Override
	public boolean contains(E obj) {
		Node<E> current = head, previous=null;
		while(current !=null){
			if(((Comparable<E>)obj).compareTo(current.data)== 0)
				return true;
			previous = current;
			current = current.next;
		}
		return false;
	}

	/**
	 * Returns an Iterator of the values in the linked list,
	 * presented the same order as the linked list.
	 * 
	 * @return an iterator of values presented in the linked list.
	 * @see java.lang.Iterable#iterator()
	 */
	@Override
	public Iterator<E> iterator() {
		return new IteratorHelper();
	}

	/**
	 * Removes the object in the linked list and returns it.
	 * 
	 * @param obj the object to be removed from the linked list.
	 * @return the object removed
	 */
	@SuppressWarnings("unchecked")
	public E remove(E obj){
		Node<E> current = head, previous = null;
		while(current != null){
			if(((Comparable<E>)obj).compareTo(current.data)==0){
				if(current==head)
					return removeFirst();
				if(current==tail)
					return removeLast();
				previous.next=current.next;
				currentSize--;
				return current.data;
			}
			previous=current;
			current=current.next;
		}
		return null;
	}

	/**
	 * Help implements iterator, contains: hasNext() and next().
	 * An optimized way of abstract list for IteratorHelper.
	 */
	class IteratorHelper implements Iterator<E>{
		Node<E> instance;
		/**
		 * Constructor for IteratorHelper
		 * . 
		 * The variable instance is initialized to head.
		 */
		public IteratorHelper(){
			instance=head;
		}
		/**
		 * Test whether the linked list has a next node.
		 * @return true if the instance is not null, otherwise false.
		 */
		public boolean hasNext(){
			return instance != null;
		}
		/**
		 * Returns the object and move the pointer to the next node in the linked list.
		 * throws a NoSuchElementException when reached at the end of the linked list.
		 * 
		 * @return the object from the linked list.
		 */
		public E next() {
			if(!hasNext())
				throw new NoSuchElementException("There is no such element.");
			E tmp = instance.data;
			instance = instance.next;
			return tmp;
		}
	}
}
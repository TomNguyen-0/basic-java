package data_structures;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * The linked list for our hash will only implement the
 * methods in the HashListI interface, a reduced set of
 * methods compared to the linked list from Assignment 1.
 * 
 * @author Tom Nguyen <tommie_89@yahoo.com>.
 * @version: Neon Release (4.6.0)- Eclipse. jre 1.8.0_92
 */
public class LinkedList<E> implements HashListI<E> {
	public Node<E> head,tail;
	public int currentSize;
	/**
	 * A single node to be used for the linked list.
	 * @param <E> the type of elements in the node.
	 */
	@SuppressWarnings("hiding")
	class Node<E>{
		E data;
		Node<E> next;
		public Node (E obj){
			data = obj;
			next= null;
		}
	}
	/**
	 * Adds an object to the end of the linked list.
	 * @param obj the object that is to be added to the linked list.
	 */
	@Override
	public void add(E obj) {
		Node<E> node = new Node<E>(obj);
		if  (head == null){
			head = tail = new Node<E>(obj);
			currentSize ++;
			return;
		}
		node.next = head;
		head = node;
		currentSize ++;
		return;	
	}
	/**
	 * Removes the first object in the list and returns it.
	 * @return the object removed. If the linked list is empty return null.
	 */
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
	 * @return the object removed. If the linked list is empty return null.
	 */
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
	 * Removes the object in the linked list and returns it.
	 * @param obj the object to be removed from the linked list.
	 * @return the object removed
	 */
	@SuppressWarnings("unchecked")
	@Override
	public E remove(E obj) {
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
	/**Return the linked list to an empty state.	 */
	@Override
	public void makeEmpty() {
		head = tail = null;
		currentSize = 0;
	}
	/**
	 * Test whether the linked list is empty.
	 * @return true if the list is empty, otherwise false;
	 */
	@Override
	public boolean isEmpty() {return head == null;}
	/**
	 * Returns the number of Objects currently in the linked list.
	 * @return the number of Objects in the linked list.
	 */
	@Override
	public int size() {return currentSize;}
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
	 * @return an iterator of values presented in the linked list.
	 * @see java.lang.Iterable#iterator()
	 */
	@Override
	public Iterator<E> iterator() {return new IteratorHelper();}
	/**
	 * Help implements iterator, contains: hasNext() and next().
	 * An optimized way of abstract list for IteratorHelper.
	 */
	class IteratorHelper implements Iterator<E>{
		Node<E> instance;
		/**
		 * Constructor for IteratorHelper
		 * The variable instance is initialized to head.
		 */
		public IteratorHelper(){instance=head;}
		/**
		 * Test whether the linked list has a next node.
		 * @return true if the instance is not null, otherwise false.
		 */
		public boolean hasNext(){return instance != null;}
		/**
		 * Returns the object and move the pointer to the next node in the linked list.
		 * throws a NoSuchElementException when reached at the end of the linked list.
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

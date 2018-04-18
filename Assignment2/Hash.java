package data_structures;

import java.util.Iterator;

/**
 * The Hash data structure has O(1) time complexity (best case) for add, remove, and find
 * for an object in the data structure. The methods in the Hash data structure are defined
 * by the HashI interface. The Hash consists of an array of Linked Lists,
 * the Linked Lists are defined by the HashListI interface.
 * 
 * @author Tom Nguyen <tommie_89@yahoo.com>.
 * @version: Neon Release (4.6.0)- Eclipse. jre 1.8.0_92
 * @param <K> The key for entries in the hash
 * @param <V> The value for entries in the hash
 */

public class Hash<K, V> implements HashI<K, V> {

	/** Contains the size of the table.	 */
	public int tableSize;
	/** Count the number of elements inside the table.	 */
	public int numElements;
	/**The maximum Load Factor for the table.	 */
	public double maxLoadFactor;
	/**Contains an array of LinkedLists.	 */
	public HashListI<HashElement<K,V>>[] harray;
	@SuppressWarnings("hiding")
	class HashElement<K,V> implements Comparable<HashElement<K,V>>{
		K key;
		V value;
		/**
		 * Constructor for the hash element. 
		 * The variable key and value initialized.
		 * @param key for the hash table.
		 * @param value the value added to the hash table.
		 */
		public HashElement(K key, V value){
			this.key = key;
			this.value = value;
		}
		/**
		 * Uses the compareTo function to compare two keys.
		 * this.key < o.key returns -1.
		 * this.key == o.key returns 0.
		 * this.key > o.key returns 1.
		 * 
		 * @return -1 or 0 or 1 after comparing current key to new key.
		 */
		@SuppressWarnings("unchecked")
		public int compareTo(HashElement<K,V> o){return (((Comparable<K>)this.key).compareTo(o.key));}
	}

	/**
	 *  The Hash constructor accepts a single parameter, an integer, that
	 *  sets the initial size of the Dictionary.
	 *  @param tablesize the table size of the hash table.
	 */
	@SuppressWarnings("unchecked")
	public Hash(int tableSize){
		this.tableSize = tableSize;
		harray = (LinkedList<HashElement<K,V>>[]) new LinkedList [tableSize];
		for (int i = 0; i<tableSize; i++)
			harray[i] = new LinkedList <HashElement<K,V>>();
		maxLoadFactor = 0.75;
		numElements = 0;
	}

	/**  
	 * Adds the given key and value that pair to the dictionary.  
	 * Returns false if the dictionary is full, or if the key is a duplicate. 
	 * Returns true if addition succeeded. 
	 *  
	 * @param key the key to add
	 * @param value the value associated with the key
	 * @return true if the key/value are added to the hash.
	 */
	@Override
	public boolean add(K key, V value) {
		if(loadFactor() > maxLoadFactor)
			resize(tableSize * 2);
		@SuppressWarnings({ "unchecked", "rawtypes" })
		HashElement<K,V> hashElement = new HashElement (key,value);
		int hashval = key.hashCode();
		hashval = hashval & 0x7fffffff;
		hashval = hashval % tableSize;
		harray[hashval].add(hashElement);
		numElements++;
		return true;
	}

	/**
	 * Deletes the key and value pair by the key parameter.
	 * Returns true if found and removed,
	 * otherwise return false.
	 * 
	 * @param key to the hash table.
	 * @return true if found, otherwise false;
	 */
	@Override
	public boolean remove(K key) {
		V val = getValue(key);
		@SuppressWarnings({ "unchecked", "rawtypes" })
		HashElement<K,V> hashElement = new HashElement (key,val);
		int hashval = key.hashCode();
		hashval = hashval & 0x7fffffff;
		hashval = hashval % tableSize;
		harray[hashval].remove(hashElement);
		numElements--;
		return true;
	}

	/**
	 * Change the value associated with existing key.
	 * @param key the key to find
	 * @param value the value to change
	 * @return true if value changed, otherwise false.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public boolean changeValue(K key, V value) {
		int hashval = (key.hashCode() & 0x7fffffff) % tableSize;
		for(HashElement<K,V> he : harray[hashval])
			if (((Comparable<K>)he.key).compareTo(key)==0){
				he.value = value;
				return true;
			}
		return false;
	}

	/**
	 * Test the hash for the entry using the key
	 * @param key the key to look for
	 * @return true if it is found, otherwise false.  
	 */
	@SuppressWarnings("unchecked")
	@Override
	public boolean contains(K key) {
		int hashval = (key.hashCode() & 0x7fffffff) % tableSize;
		for (HashElement<K,V> he : harray[hashval])
			if (((Comparable<K>)he.key).compareTo(key)==0)
				return true;
		return false;
	}

	/**
	 * Returns the value associated with the parameter key.
	 * Returns null if the key is not found or the dictionary is empty.
	 * @param key the key to find the value for
	 * @return the value
	 */
	@SuppressWarnings("unchecked")
	@Override
	public V getValue(K key) {
		int hashval = (key.hashCode() & 0x7fffffff) % tableSize;
		for(HashElement<K,V> hashElement : harray[hashval])
			if (((Comparable<K>)hashElement.key).compareTo(key)==0)
				return hashElement.value;
		return null;
	}

	/**
	 * Returns the number of key and value pairs currently stored
	 * in the hash table.
	 * @return the size for the hash table.
	 */
	@Override
	public int size() {
		return numElements;
	}

	/**
	 * Return true if the hash table is empty.
	 * @return true if empty, otherwise false.
	 */
	@Override
	public boolean isEmpty() {
		return numElements==0;
	}

	/**
	 * Make the hash table empty.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void makeEmpty() {
		harray = (LinkedList<HashElement<K,V>>[]) new LinkedList [tableSize];
		for (int i = 0; i<tableSize; i++)
			harray[i] = new LinkedList <HashElement<K,V>>();
		maxLoadFactor = 0.75;
		numElements = 0;

	}

	/**
	 * Returns a double for the current load factor of the hash table.
	 * @return lambda (loadFactor)
	 */
	@Override
	public double loadFactor() { 
		return numElements/tableSize;
	}

	/**
	 * Return the max load factor. 
	 * The point at which the hash table will resize.
	 * @return the maximum load factor of the hash.
	 */
	@Override
	public double getMaxLoadFactor() {
		return maxLoadFactor;
	}

	/**
	 * Set the maximum load factor.
	 * The point  which the hash will resize.
	 * @param loadfactor the maximum load factor.
	 */
	@Override
	public void setMaxLoadFActor(double loadfactor) {
		maxLoadFactor = loadfactor;

	}

	/**
	 * Resizes the Hash.
	 * @param newSize is the size of the new hash.
	 */
	@Override
	public void resize(int newSize) {
		@SuppressWarnings("unchecked")
		HashListI<HashElement<K,V>>[] new_array = (LinkedList<HashElement<K,V>>[]) new LinkedList[newSize];
		for (int i=0; i<newSize; i++)
			new_array[i] = new LinkedList <HashElement<K,V>>();
		for(K key : this){
			V val = getValue(key);
			HashElement<K,V> hashElement = new HashElement<>(key,val);
			int hashval = (key.hashCode() & 0x7fffffff) % newSize;
			new_array[hashval].add(hashElement);
		}
		harray = new_array;
		tableSize = newSize;
	}

	/**
	 * Returns an Iterator of the keys in the hash,
	 * in ascending sorted order.
	 * @return an iterator helper to assist the iterator.
	 */
	@Override
	public Iterator<K> iterator() {
		return new IteratorHelper<K>();
	}
	/**
	 * Help implements iterator with hasNext and next.
	 * An optimized way for abstract list.
	 *
	 * @param <T> The element that the iterator will use.
	 */
	class IteratorHelper<T> implements Iterator<T>{
		T[] keys;
		int position;
		/**
		 * Constructor for IteratorHelper.
		 * The variable keys is an array of <T>.
		 */
		@SuppressWarnings("unchecked")
		public IteratorHelper(){
			keys = (T[]) new Object[numElements];
			int p=0;
			for(int i=0; i<tableSize; i++){
				LinkedList<HashElement <K,V>> list = (LinkedList<HashElement<K,V>>) harray[i];
				for(HashElement<K,V> h:list)
					keys[p++] = (T)h.key;
			}
			position=0;
		}
		/**
		 * Test whether the hash has a next.
		 * @return true if it has next, otherwise false.
		 */
		public boolean hasNext(){
			return position < keys.length;
		}
		/**
		 * Returns the object and moves the pointer to the next position.
		 * Returns null when reach at the end of the hash.
		 * @return the object from the hash table, otherwise null.
		 */
		public T next(){
			if(!hasNext())
				return null;
			return keys[position ++];
		}
	}

}

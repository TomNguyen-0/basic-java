package data_structures;

import java.util.Iterator;

/**
 * RedBlackTree is a data structure that maintains a balanced
 * Red/Black search tree.
 * 
 * @author Tom Nguyen
 * @version: Neon Release (4.6.0)- Eclipse. jre 1.8.0_92
 *
 * @param <K> The key for entries in the binary search tree.
 * @param <V> The value for entries in the binary search tree.
 */
public class RedBlackTree<K, V> implements RedBlackI<K, V> {
	Node<K,V> root;
	int size;
	@SuppressWarnings("hiding")
	class Node<K,V>{
		K key;
		V value;
		Node<K,V> left, right, parent;
		boolean isLeftChild, black;
		/**
		 * Constructor for Node<K,V>
		 * @param key the key to be used.
		 * @param value the value associated to the key.
		 */
		public Node (K key, V value){
			this.key = key;
			this.value = value;
			left = right = parent = null;
			black = false;
			isLeftChild = false;
		}
		/**
		 * The default constructor for the Node class.
		 * All the variable is set to either null or false.
		 */
		public Node(){
			this.key = null;
			this.value = null;
			left=right=parent = null;
			black = false;
			isLeftChild = false;
		}
	}
	/**
	 * The method to add to the Red/Black Tree. 
	 * @param key is the key used to add.
	 * @param value is the value associated with the key.
	 */
	@Override
	public void add(K key, V value) {
		Node<K,V> node = new Node<>(key,value);
		if(root == null){
			root = node;
			root.black = true;
			size++;
			return;
		}
		add(root,node);
		size++;
	}
	/**
	 * This method is an overload method for the add method.
	 * @param parent The parent node.
	 * @param newNode The node that is to be added to the RB Tree.
	 */
	@SuppressWarnings("unchecked")
	private void add(Node<K,V> parent, Node<K,V> newNode) {
		if(((Comparable<K>)newNode.key).compareTo(parent.key)>=0){
			if(parent.right == null){
				parent.right = newNode;
				newNode.parent = parent;
				newNode.isLeftChild = false;
				return;
			}
			add(parent.right, newNode);
			checkColor(newNode);
			return;
		}
		if(parent.left == null){
			parent.left = newNode;
			newNode.parent = parent;
			newNode.isLeftChild = true;
			return;
		}
		add(parent.left,newNode);
		checkColor(newNode);
	}
	/**
	 * The method used to check the red violations for the RB tree.
	 * @param node the node that is being color checked.
	 */
	public void checkColor(Node<K,V> node){
		if(node == root){
			node.black = true;
			node.isLeftChild=false;
			return;
		}
		if(!node.black && !node.parent.black)
			correctTree(node);

		if(node == root){
			node.black = true;
			node.isLeftChild=false;
			return;
		}
		checkColor(node.parent);
	}

	/**
	 * After the violations are checked by the checkColor method.
	 * This method corrects the violations so that the RB tree
	 * is balanced and the violations are fixed.
	 * @param node that is being added that caused the violations.
	 */
	public void correctTree(Node<K,V> node){
		if(node.parent.isLeftChild){ // aunt is node.parent.parent.right, grandparent of the right child.
			if(node.parent.parent.right == null || node.parent.parent.right.black){
				rotate(node);
				return;
			}
			if(node.parent.parent.right != null) //color flip
				node.parent.parent.right.black = true;
			node.parent.parent.black = false;
			node.parent.black = true;
			return;
		}
		if(!node.parent.isLeftChild){
			//aunt is node.parent.parent.left (grandparent of the left child).
			if (node.parent.parent.left == null || node.parent.parent.left.black){
				rotate(node);
				return;
			}
			if(node.parent.parent.left !=null)
				node.parent.parent.left.black = true;
			node.parent.parent.black = false;
			node.parent.black = true;
			return;
		}
	}
	/**
	 * This method will rotate the RedBlack tree so that it will
	 * be balanced.
	 * @param node the node that was recently added.
	 */
	public void rotate(Node<K,V> node){
		if(node.isLeftChild){
			if(node.parent.isLeftChild){
				rightRotate(node.parent.parent);
				node.black = false;
				node.parent.black=true;
				if(node.parent.right !=null)
					node.parent.right.black = false;
				return;
			}
			rightLeftRotate(node.parent.parent);
			node.black = true;
			node.right.black = false;
			node.left.black = false;
			return;
		}
		if(!node.isLeftChild){
			if(!node.parent.isLeftChild){
				leftRotate(node.parent.parent);
				node.black = false;
				node.parent.black = true;
				if(node.parent.left != null)
					node.parent.left.black = false;
				return;
			}
			leftRightRotate(node.parent.parent);
			node.black = true;
			node.right.black = false;
			node.left.black = false;
			return;
		}	
	}
	/**
	 * This method rotates the node to the left.
	 * @param node the grandparent of the node that caused the violations.
	 */
	public void leftRotate(Node<K,V> node){
		Node<K,V> temp = node.right;
		node.right = temp.left;
		if(node.right != null){
			node.right.parent = node;
			node.right.isLeftChild = false;
		}
		if(node.parent==null){ //root node
			temp.parent = null;
			root = temp;
		}
		else{
			temp.parent = node.parent;
			if(node.isLeftChild){
				temp.isLeftChild = true;
				temp.parent.left = temp;
			}
			else{
				temp.isLeftChild = false;
				temp.parent.right = temp;
			}
		}
		temp.left = node;
		node.isLeftChild = true;
		node.parent = temp;
	}
	/**
	 * This method rotates the node to the right.
	 * @param node the grandparent of the node that caused the violations.
	 */
	public void rightRotate(Node<K,V> node){
		Node<K,V> temp = node.left;
		node.left = temp.right;
		if(node.left != null){
			node.left.parent = node;
			node.left.isLeftChild = true;
		}
		if(node.parent==null){ //root node
			temp.parent = null;
			root = temp;
		} 
		else{
			temp.parent = node.parent;
			if(!node.isLeftChild){
				temp.isLeftChild = false;
				temp.parent.right = temp;
			}
			else{
				temp.isLeftChild = true;
				temp.parent.left = temp;
			}
		}
		temp.right = node;
		node.isLeftChild = false;
		node.parent = temp;
	}
	/**
	 * This method rotates the node to the left than to the right.
	 * @param node the grandparent of the node that caused the violations.
	 */
	public void leftRightRotate(Node<K,V> node){
		leftRotate(node.left);
		rightRotate(node);
	}
	/**
	 * This method rotates the node to the left than to the right.
	 * @param node the grandparent of the node that caused the violations.
	 */
	public void rightLeftRotate(Node<K,V> node){
		rightRotate(node.right);
		leftRotate(node);
	}
	/**
	 * Tests whether the RedBlack tree contains the key
	 * @param key the key to look for
	 * @return true when it is found, otherwise false.
	 */
	@Override
	public boolean contains(K key) {
		return contains(key,root);
	}
	/**
	 * A overload method used by the contains method to test wheter
	 * the RedBlack tree contains the key.
	 * @param key the key to look for.
	 * @param node the node that is being tested.
	 * @return true when the key is found, otherwise false.
	 */
	@SuppressWarnings("unchecked")
	public boolean contains(K key, Node<K,V> node){
		if(node ==null)
			return false;
		if(((Comparable<K>)key).compareTo(node.key)==0)
			return true;
		if(((Comparable<K>)key).compareTo(node.key)>0)
			return contains(key,node.right);
		return contains(key,node.left);
	}
	/**
	 * Get the value associated with a given key
	 * @param key the key to get the value for
	 * @return the current value
	 */
	@Override
	public V getValue(K key) {
		return getValue(key,root);
	}
	/**
	 * A overload method used to get the value associated with
	 * a given key.
	 * @param key the key to look for.
	 * @param node the current node that is being tested.
	 * @return the value of that node that was being looked for.
	 */
	@SuppressWarnings("unchecked")
	public V getValue(K key, Node<K,V> node){
		if(node == null)
			return null;
		if(((Comparable<K>)key).compareTo(node.key)==0)
			return node.value;
		if(((Comparable<K>)key).compareTo(node.key)>0)
			return getValue(key,node.right);
		return getValue(key,node.left);
	}
	/**
	 * Returns the number of elements in the RBTree.
	 * @return the number of elements in the tree.
	 */
	@Override
	public int size() {
		return size;
	}
	/**
	 * Test whether the RedBlack tree is empty.
	 * @return true if the tree is empty otherwise, false.
	 */
	@Override
	public boolean isEmpty() {
		return root==null;
	}
	/**
	 * The height of the tree. Recall that a tree with 
	 * only a root node has height 0 
	 * @return the height of the tree at the root node
	 */
	@Override
	public int height() {
		if(root==null)
			return 0;
		return height(root)-1;
	}
	/**
	 * A overload method to get the height of the tree.
	 * Recall that a tree with only a root node has height 0.
	 * @param node the current node.
	 * @return the height of the tree at the root node
	 */
	public int height(Node<K,V> node){
		if(node == null)
			return 0;
		int leftHeight = height(node.left)+1;
		int rightHeight = height(node.right)+1;
		if(leftHeight > rightHeight)
			return leftHeight;
		return rightHeight;
	}
	/**
	 * Returns the number of black nodes in the RedBlack Tree
	 * @param node the current node that it is at.
	 * @return the number of black nodes in the RedBlack Tree.
	 * @throws BlackNodesViolationException 
	 */
	public int blackNodes(Node<K,V> node){
		if(node == null)
			return 1;
		int rightBlackNodes = blackNodes(node.right);
		int leftBlackNodes = blackNodes(node.left);
		if(rightBlackNodes != leftBlackNodes){
			System.err.print("violation: Every path from root to leaf has the same number of black nodes.");
			if(leftBlackNodes> rightBlackNodes)
				leftBlackNodes = rightBlackNodes;
			else
				rightBlackNodes = leftBlackNodes;
		}
		if(node.black)
			leftBlackNodes++;
		return leftBlackNodes;
	}
	/**
	 * An iterator for all the keys in the RBTree. This will
	 * iterate over the keys using <b>InOrder Traversal</b>
	 * @see java.lang.Iterable#iterator()
	 */
	@Override
	public Iterator<K> iterator() {
		return new IteratorHelper<K>();
	}
	/**
	 * Help implements iterator with hasNext and next.
	 * An optimized way for abstract list.
	 *
	 * @param <K> The element that the iterator will use.
	 */
	@SuppressWarnings("hiding")
	class IteratorHelper<K> implements Iterator<K>{
		K[] keys;
		int position;
		/**
		 * Constructor for IteratorHelper.
		 * The variable keys is an array of <T>.
		 */
		@SuppressWarnings("unchecked")
		public IteratorHelper(){
			keys = (K[]) new Object[size];
			position=0;
			getKey((RedBlackTree<K, V>.Node<K, V>) root);
			position=0;
		}

		/**
		 * Load all the keys into the array keys from the root node.
		 * @param node the root.
		 */
		public void getKey(RedBlackTree<K, V>.Node<K, V> node){
			if(node==null)
				return;
			getKey(node.left);
			keys[position++]=node.key;
			getKey(node.right);
		}
		/**
		 * Returns the object and moves the pointer to the next position.
		 * Returns null when reach at the end of the hash.
		 * @return the object from the  tree, otherwise null.
		 */
		public K next(){
			if(!hasNext())
				return null;
			return keys[position ++];
		}
		/**
		 * Test whether the tree has a next.
		 * @return true if it has next, otherwise false.
		 */
		public boolean hasNext(){
			return position < keys.length;
		}
	}
	/**
	 * Recursively print the tree. This method should print the
	 * entire tree using Inorder Traversal to the standard
	 * output. You can print the tree one node per line,
	 * and use periods to note the hierarchy of the tree.
	 */
	@Override
	public void print() {
		int p=0;
		printHelper(root,p);
	}
	/**
	 * A overload method used by the print method to print the 
	 * Inorder Traversal.
	 * @param node is the current node.
	 */
	public void printHelper(Node<K,V> node, int levels){	
		if(node==null)
			return;
		printHelper(node.left, levels+1);
		for(int i = 0; i<levels;i++)
			System.out.print(".");
		System.out.print(node.key+" : ");
		if(node.black)
			System.out.print(" black");
		else
			System.out.print(" red");
		if (node.parent ==null)
			System.out.print(" (root)");
		System.out.println();
		printHelper(node.right,levels+1);
	}
	/**
	 * This method is used for debugging. prints out the root and
	 * the left and right node.
	 * @param temp the current node.
	 */
	public void debug(Node<K,V> temp){
		System.out.println("This is root: "+ root.key+ " ");
		if(temp.left!=null && temp.right!=null && temp.parent!=null){
			System.out.println(temp.parent.key + " "+temp.left.key +" "+ temp.right.key);
			return;
		}
		if(temp.left!=null && temp.right!=null){
			System.out.println(temp.parent + " "+temp.left.key +" "+ temp.right.key);
			return;
		}
		if(temp.left!=null && temp.parent!=null){
			System.out.println(temp.parent.key + " "+temp.left.key +" "+ temp.right);
			return;
		}
		if(temp.parent!=null && temp.right!=null){
			System.out.println(temp.parent.key + " "+temp.left +" "+ temp.right.key);
			return;
		}
		if(temp.left != null){
			System.out.println(temp.parent + " "+temp.left.key +" "+ temp.right);
			return;
		}
		if(temp.right != null){
			System.out.println(temp.parent + " "+temp.left +" "+ temp.right.key);
			return;
		}
		if(temp.parent != null){
			System.out.println(temp.parent.key + " "+temp.left +" "+ temp.right);
			return;
		}
	}
}

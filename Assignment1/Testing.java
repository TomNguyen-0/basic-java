



import java.util.Iterator;

import data_structures.LinkedList;
import data_structures.Queue;
import data_structures.Stack;

/**
 * 
 */

/**
 * @author tom
 *
 */
public class Testing {

	/**
	 * 
	 */
	public Testing() {
		// TODO Auto-generated constructor stub
	}

	
	public void sayHello(){
		System.out.println("Hello World.");
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		LinkedList<Integer> list = new LinkedList<Integer>();
		int add = 3;

		int n=10;
		for (int i=0; i<n; i++)
			list.addFirst(i);
		if(list.size() !=10)
			wrong();
		for(int i=0; i<n;i++){
			int x = list.removeLast();
			if(x!=i)
				wrong();
		}
		for(int i = 0;i<n; i++)
			list.addLast(i);
		for(int i=0; i<=0 ; i++){
			int x=list.removeFirst();
			if(x!=i)
				wrong();
		}

		for(int i = 1; i<add; i++){
			list.addLast(i);
			list.addFirst(i);
		}
		list.show();
		System.out.println();

		System.out.print(" currentSize: " + list.currentSize);
		System.out.print(" removeFirst: " + list.removeFirst() + "\n");
		list.show();
		System.out.print(" CurrentSize: " + list.currentSize + " removedLast: " + list.removeLast() + "\n");
		list.show();

		for(int i = 0; i<(2*add); i++){
			System.out.print(" currentSize: " + list.currentSize);
			System.out.print(" removeFirst: " + list.removeFirst());
			System.out.print(" CurrentSize: " + list.currentSize + " removedLast: " + list.removeLast());
			System.out.print(" currentSize: " + list.currentSize);
			System.out.println();
		}

		System.out.println();
		list.show();
		System.out.println("removeFirst: " + list.removeFirst());
		System.out.println();

		/*
		 * This is where the real test occur:
		 * Test five key point:
		 * 1. when list is empty
		 * 2. when list only has one element
		 * 3. First
		 * 4.last
		 * 5. middle
		 *
		 */
		System.out.println("removeLast when link list is empty: " + list.removeLast());
		System.out.println("removeFirst when link list is empty: " + list.removeFirst()+"\n");
		System.out.println("addFirst to linked list: 10"); list.addFirst(10);
		list.show();
		System.out.println("removeLast, when link list has one data link "+ list.removeLast()+"\n");
		System.out.println("addLast to linked list: 20"); list.addLast(20);
		list.show();
		System.out.println("removeFirst, when link list has one data link: " + list.removeFirst());
		System.out.println("\naddLast to linked list 2 times: 20,30 \n"); list.addLast(20); list.addLast(30); list.show();
		System.out.println("\nremoveLast when 2 elements in the data link: " + list.removeLast() + "\n"); list.show(); list.removeLast();
		System.out.println("\naddFirst to linked list 2 times: 20,30"); list.addLast(20); list.addFirst(30); list.show();
		System.out.println("\nremoveFirst when 2 elements are in the data link: " + list.removeFirst()); list.show();
		System.out.println("\nChecking the method contains(0): " + list.contains(0));
		for(int i = 1; i<7; i++){list.addLast(i);}
		System.out.println("\nAdding 1,2,3,4,5,6,7" );
		list.show();
		System.out.println("\nChecking the method contains(20): " + list.contains(20));
		System.out.println("\nChecking the method contains(6): " + list.contains(6));
		System.out.println("\nDeleting 6: "); list.removeLast(); list.show();
		System.out.println("\nChecking the method contains(6): " + list.contains(6));
		System.out.println("\nAdd last 6"); list.addLast(6); list.show();
		for(int i = 0; i<21;i++){
			System.out.print("\nChecking the method contains(" + i + "):" + list.contains(i));
		}
		System.out.println("\nThe size of the linked data is: " + list.size());
		System.out.println("\nShow peekFirst: " + list.peekFirst());
		System.out.println("\nShow peekLast: " + list.peekLast());
		for(int i=1; i<7 ; i++){
			//list.removeLast();
			list.removeFirst();
		}list.show();
		System.out.println("\nShow peekFirst: " + list.peekFirst());
		System.out.println("\nShow peekLast: " + list.peekLast());
		System.out.println("\nEmpty the linked list: list.makeEmpty()"); list.makeEmpty(); list.show();
		System.out.println("\nShow peekFirst: " + list.peekFirst());
		System.out.println("\nShow peekLast: " + list.peekLast());

		System.out.println("\nAdd 1-10");
		for(int i=1;i<11;i++)
			list.addLast(i);
		//list.addFirst(i);
		list.show();
		//System.out.println("\nremove 5: "+ list.remove(5));
		remove(5,list);
		list.show();
		remove(1,list);
		list.show();
		remove(10,list);
		list.show();
		System.out.println("\n add first 9: ");list.addFirst(9);
		list.show();
		remove(9,list);
		list.show();
		System.out.println("\n add last 9: ");list.addLast(9);
		list.show();
		remove(9,list);
		list.show();
		System.out.println("\nmake empty");list.makeEmpty();
		list.show();
		System.out.println("\nadd last 9: ");list.addLast(9);
		list.show();
		remove(9,list);
		list.show();
		System.out.println("\nadd first 9:");list.addFirst(9);
		list.show();
		remove(9,list);
		list.show();
		for(int i=1;i<5;i++)
			list.addLast(i);
		list.show();
		Iterator<Integer> something = list.iterator();
		for(int i=1;i<5;i++){
			System.out.println("\n iterator has next: "+ something.hasNext());
			System.out.println("\n iterator next: " + something.next());
		}
		System.out.println("\n iterator has next: "+ something.hasNext());
		//This is where stack testing happens.
		System.out.println("*****************STACK**************************");
		Stack<Integer> newList = new Stack<>();
		System.out.println("\nAdd 19 to stack: ");newList.push(19);
		newList.show();
		for(int i=1;i<7;i++)
			newList.push(i);
		newList.show();
		System.out.println("\nRemove from the top of the stack(6): " + newList.pop());
		System.out.println("\nThe size of the stack(6): " + newList.size());
		newList.show();
		System.out.println("\nRemove from the top of the stack(5): "+newList.pop());
		newList.show();
		System.out.println("\nIs the stack empty? "+newList.isEmpty());
		System.out.println("\nMake the stack empty."); newList.makeEmpty();
		System.out.println("\nIs the stack empty? "+newList.isEmpty());
		System.out.println("\nRemove from stack when empty: " + newList.pop());
		System.out.println("\nIs the stack full? "+newList.isFull());
		System.out.println("\nAdd 1-6 to stack.");
		for(int i=1;i<7;i++)
			newList.push(i);
		newList.show();
		System.out.println("\nDoes this contain the number 3? " + newList.contains(3));
		System.out.println("\nStack peek: "+newList.peek());
		newList.show();
		Iterator<Integer> iStack = newList.iterator();
		for(int i=1;i<newList.size()+1;i++){
			System.out.println("\n iterator has next: "+ iStack.hasNext());
			System.out.println("\n iterator next: " + iStack.next());
		}
		System.out.println("\n iterator has next: "+ iStack.hasNext());
		//	    private ListI list;	    
		//	    public Queue() {}
		//	    public void enqueue(E obj) {}   
		//	    public E dequeue() {}   
		//	    public int size() {}
		//	    public boolean isEmpty() {}
		//	    public E peek() {}
		//	    public boolean contains(E obj) {}
		//	    public void makeEmpty() {} 
		//	    public Iterator iterator() {}
		System.out.println("*******************QUEUE*************");
		Queue<Integer> queue = new Queue<>();
		System.out.println("\nAdd 1-3 to queue.");
		for(int i=1;i<4;i++)
			queue.enqueue(i);
		queue.show();
		System.out.println("\nPeek: "+queue.peek());
		System.out.println("\nDoes this contain 9? "+queue.contains(9));
		queue.show();
		System.out.println("\nDequeue: "+queue.dequeue());
		queue.show();
		System.out.println("\nDequeue: "+queue.dequeue());
		System.out.println("\nQueue size: " + queue.size());
		System.out.println("\nDequeue: "+queue.dequeue());
		queue.show();
		System.out.println("\nDequeue when empty: "+queue.dequeue());
		System.out.println("\nIs this queue empty? "+queue.isEmpty());
		System.out.println("\nDoes this contain 9?"+queue.contains(9));
		System.out.println("\nAdd 9 to queue.");queue.enqueue(9);
		System.out.println("\nIs this queue empty? " + queue.isEmpty());
		System.out.println("\nQueue peek: "+queue.peek());
		System.out.println("\nDoes this contain 9?"+queue.contains(9));
		System.out.println("Make queue empty.");queue.makeEmpty();
		queue.show();
		System.out.println("\nAdd 1-8 to queue.");
		for(int i=1; i<9;i++)
			queue.enqueue(i);
		queue.show();
		Iterator<Integer> iQueue = queue.iterator();
		for(int i=1;i<queue.size()+1;i++){
			System.out.println("\niterator has next: "+ iQueue.hasNext());
			System.out.println("iterator next: " + iQueue.next());
		}
		System.out.println("\niterator has next: "+ iQueue.hasNext());
		System.out.println("\nIs this queue full? "+queue.isFull());





	}
	public static void wrong(){
		System.out.println("Something is wrong");
	}
	public static void remove(int number, LinkedList<Integer> list){
		System.out.println("\nremove "+number+ ": "+ list.remove(number));
	}

}

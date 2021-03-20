package pedigree;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * A heap where it is possible to retrieve an element at a specific index.
 * 
 * @author Alexandre Pachot
 * @author Dave Sanon-Abraham
 * 
 */
public class Heap<E extends Comparable<E>>
{

	private ArrayList<E> heap;
	private static final int INITIAL_CAPACITY = 10;
	private static int heapSize;
	
	/**
	  * The comparator we're using, or null for natural ordering.
	  */
	Comparator<? super E> comparator;
	
	public Heap(int initialCapacity)
	{
		// We start the array at index 1, not 0.
		heap = new ArrayList<E>(initialCapacity + 1);
		
		// Just to fill the first entry in the list
		heap.add(null);

		// Number of sims in the heap
		heapSize = 0;
	}
	
	public Heap()
	{
		this(INITIAL_CAPACITY);
	}
	
	public Heap(Comparator<E> comp)
	{
		this.comparator=comp;
	}

	
	public int size()
	{
		return heapSize;
	}
	
	/**
	 * Add a sim to the heap.
	 * 
	 * @param sim The sim to add
	 */
	public void add(E e)
	{
		swim(e);
		heapSize++;		
	}
	
	/**
	 * Remove the first element of the heap.
	 * 
	 * @return The first element of the heap
	 */
	public E poll()
	{
		E min = heap.get(1);
		
		// Get the last element
		E last = heap.get(heapSize);
		
		heap.set(heapSize, null);
		heapSize--;
		if (heapSize > 0) {
			
			// We bring down the last element
			sink(last, 1);
		}
		return min;
	}
	
	public void print() {
		for(int i=1; i<=heapSize; i++) {
			System.out.print(heap.get(i).toString());
			System.out.print(" ");
		}
		System.out.println();
	}

	
	public E get(int i) {
		if (i < heap.size()) {
			return heap.get(i+1);
		} else {
			return null;
		}
		
	}
	
	/**
	 * Insert a sim at the bottom and bring it up to his position.
	 * 
	 * @param sim The sim to insert
	 */
	void swim(E e)
	{
		// Index of the new element
		int i = heapSize + 1;
		
		// Index of the parent, p = i/2
		int p = i>>1;
		
		// As long as we are not at the root
		while (p > 0) {
			E parent = heap.get(p);
			
			// If the parent is smaller then we stop
			if (parent.compareTo(e) < 0) {
				break;
			}
			
			// Otherwise, we take down the parent
			if(i<heap.size()) {
				heap.set(i, parent);
			} else {
				heap.add(i, parent);
			}
			
			// We bring up the empty bubble to the parent position
			i =  p;
			
			// Index of the new parent, p = i/2
			p = i>>1;
		}
		
		// We insert the sim at his position
		System.out.println("i = " + i);
		System.out.println("size = " + heap.size());
		if(i<heap.size()) {
			heap.set(i, e);
		} else {
			heap.add(i, e);
		}
		
	}
	
	/**
	 * Insert a element at the top and bring it down to his position.
	 * 
	 * @param e The element to insert
	 * @param i The starting position, the top
	 */
	void sink(E e, int i)
	{
		assert(i < heap.size());
		int minChildIndex = minChild(i);
		E minChild;
		while (minChildIndex != 0) {
			minChild = heap.get(minChildIndex);
			
			// If the child is bigger than the parent we stop the descent
			if (minChild.compareTo(e)>=0) {
				break;
			}
			
			// The child is moved up to the position of the parent
			heap.set(i, minChild);
			
			// Index of the next parent
			i = minChildIndex;
			
			// Getting the max child from the new parent
			minChildIndex = minChild(i);
		}
		heap.set(i, e);
	}
	
	// Returns the min child of the parent or 0 if no child
	int minChild(int p)
	{
		
		// Default child
		int c = 0;
		
		// Index of the left child
		if (2 * p <= heapSize) {
			c = 2*p;
		}
		
		// Index of the right child
		if (2 * p + 1 <= heapSize) {
			E childLeft = heap.get(c);
			E childRight = heap.get(2*p + 1);
			if (childRight.compareTo(childLeft) < 0) {
				c = 2*p + 1;
			}
		}
		
		return c;
	}

}

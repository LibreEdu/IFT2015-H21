package pedigree;

import java.util.ArrayList;

/**
 * A max heap of sims
 * 
 * @author Alexandre Pachot
 * @author Dave Sanon-Abraham
 * 
 */
public class MaxHeap {

	private static ArrayList<Sim> heap;
	private static final int SMP_SIZE = 1000;
	private static int heapSize;
	
	public MaxHeap(int smp_size) {
		// We start the array at index 1, not 0.
		heap = new ArrayList<Sim>(smp_size + 1);
		
		// Just to fill the first entry in the list
		heap.add(new Sim(Sim.Sex.M));

		// Number of sims in the heap
		heapSize = 0;
	}
	
	public MaxHeap() {
		this(SMP_SIZE);
	}
	
	/**
	 * Add a sim to the heap.
	 * 
	 * @param sim The sim to add
	 */
	public void insert(Sim sim) {
		swim(sim);
		heapSize++;		
	}
	
	/**
	 * Insert a sim at the bottom and bring it up to his position.
	 * 
	 * @param sim The sim to insert
	 */
	private void swim(Sim sim) {
		// Index of the new sim
		int i = heapSize + 1;
		
		// Index of the parent, p = i/2
		int p = i>>1;
		
		// As long as we are not at the root
		while (p > 0) {
			Sim parent = heap.get(p);
			
			// If the parent is bigger (born after the sim) then we stop
			if (parent.compareTo(sim) >= 0) {
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
		heap.add(i, sim);
		
	}
	
	/**
	 * Remove the first element of the heap.
	 * 
	 * @return The youngest sim, the one with the biggest birth date.
	 */
	public Sim deleteMax() {
		Sim max = heap.get(1);
		
		// Get the last sim
		Sim last = heap.get(heapSize);
		
		heap.set(heapSize, null);
		heapSize--;
		if (heapSize > 0) {
			
			// We bring down the last sim
			sink(last, 1);
		}
		return max;
	}
	
	/**
	 * Insert a sim at the top and bring it down to his position.
	 * 
	 * @param sim The sim to insert
	 * @param i The starting position, the top
	 */
	private void sink(Sim sim, int i) {
		assert(i < heap.size());
		int maxChildIndex = maxChild(i);
		Sim maxChild;
		while (maxChildIndex <= heapSize) {
			maxChild = heap.get(maxChildIndex);
			
			// If the child is smaller than the parent, we stop the descent
			if (maxChild.compareTo(sim)<=0) {
				break;
			}
			
			// The child is moved up to the position of the parent
			heap.set(i, maxChild);
			
			// Index of the next parent
			i = maxChildIndex;
			
			// Getting the max child from the new parent
			maxChildIndex = maxChild(i);
		}
		heap.set(i, sim);
	}
	
	// Returns the max child of the parent or 0 if no child
	private int maxChild(int p) {
		
		// Default child
		int c = 0;
		
		// Index of the left child
		if (2 * p <= heapSize) {
			c = 2 * p;
		}
		
		// Index of the right child
		if (2 * p + 1 <= heapSize) {
			Sim childLeft = heap.get(c);
			Sim childRight = heap.get(2*p+1);
			if (childRight.compareTo(childLeft) > 0) {
				c = 2 * p + 1;
			}
		}
		
		return c;
	}
	
	
	
}

package pedigree;

import java.util.ArrayList;

public class MaxHeap {

	private static ArrayList<Sim> heap;
	private static final int SMP_SIZE = 1000;
	private static int heapSize;
	
	public MaxHeap(int smp_size) {
		// We start the array at index 1
		heap = new ArrayList<Sim>(smp_size + 1);
		heapSize = 0; // Number of sims in the heap
	}
	
	public MaxHeap() {
		this(SMP_SIZE + 1);
	}

	// Diaporama 6.2 (file de priorité, tas binaire)
	// Diapo 19/21
	private void swim(Sim sim) {
		int i = heapSize + 1;
		int p = i/2;
		while (p > 0) {
			Sim parent = heap.get(p);
			if (parent.compareTo(sim) >= 0) {
				break;
			}
			heap.set(i, parent);
			i =  p;
			p = i>>1;
		}
		heap.set(i, sim);
		heapSize++;
	}
	
	
}

package pedigreeOld;

import pedigree.Heap;
import pedigree.Sim;
import pedigree.Sim.Sex;

public class HeapTest {

	//private static Heap<Sim> maxHeap = new Heap<Sim>(new birthComparator<Sim>()); // Birth comparaison
	private static Heap<Sim> minHeap = new Heap<Sim>(); // Death comparaison

	public static void main(String[] args) {
		/*
		maxInsert(1);
		maxInsert(3);
		maxInsert(2);
		maxInsert(4);
		maxInsert(0);
		maxInsert(6);
		maxInsert(5);
		maxInsert(7);
		maxHeap.print();
		int size = maxHeap.size();
		Sim sim;
		for (int i = 0; i < size; i++) {
			sim = (Sim) maxHeap.poll();
			System.out.println(sim.getBirthTime());			
		}

		System.out.println();
		*/
		minInsert(4);
		minInsert(2);
		minInsert(6);
		minInsert(5);
		minInsert(1);
		minInsert(3);
		minInsert(7);
		minInsert(0);
		minHeap.print();
		int size = minHeap.size();
		Sim sim;
		for (int i = 0; i < size; i++) {
			sim = (Sim) minHeap.poll();
			System.out.println(sim.getDeathTime());
		}

	}
	
	/*private static void maxInsert(int i) {
		maxHeap.add(new Sim(null, null, i, Sim.Sex.M));
	}*/

	private static void minInsert(int i) {
		Sim sim = new Sim(Sim.Sex.M);
		sim.setDeathTime(i);
		minHeap.add(sim);
	}

}

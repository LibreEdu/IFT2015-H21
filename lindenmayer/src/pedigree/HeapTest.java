package pedigree;

public class HeapTest {

	private static MaxHeap max = new MaxHeap(); // Birth comparaison
	private static MinHeap min = new MinHeap(); // Death comparaison

	public static void main(String[] args) {
		maxInsert(1);
		maxInsert(3);
		maxInsert(2);
		maxInsert(4);
		maxInsert(0);
		maxInsert(6);
		maxInsert(5);
		maxInsert(7);
		max.print();
		int size = max.size();
		for (int i = 0; i < size; i++) {
			System.out.println(max.deleteMax().getBirthTime());			
		}

		System.out.println();
		minInsert(4);
		minInsert(2);
		minInsert(6);
		minInsert(5);
		minInsert(1);
		minInsert(3);
		minInsert(7);
		minInsert(0);
		min.print();
		size = min.size();
		for (int i = 0; i < size; i++) {
			System.out.println(min.deleteMin().getDeathTime());
		}

	}
	
	private static void maxInsert(int i) {
		max.insert(new Sim(null, null, i, Sim.Sex.M));
	}

	private static void minInsert(int i) {
		Sim sim = new Sim(Sim.Sex.M);
		sim.setDeathTime(i);
		min.insert(sim);
	}

}

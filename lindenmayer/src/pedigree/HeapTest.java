package pedigree;

public class HeapTest {

	private static MaxHeap max = new MaxHeap();
	private static MinHeap min = new MinHeap();

	public static void main(String[] args) {
		max.insert(new Sim(null, null, 0, Sim.Sex.M));
		max.insert(new Sim(null, null, 2, Sim.Sex.M));
		max.insert(new Sim(null, null, 1, Sim.Sex.M));
		
		for (int i = 0; i < 3; i++) {
			System.out.println(max.deleteMax().getBirthTime());			
		}

		System.out.println();
		
		min.insert(new Sim(null, null, 2, Sim.Sex.M));
		min.insert(new Sim(null, null, 0, Sim.Sex.M));
		min.insert(new Sim(null, null, 1, Sim.Sex.M));
		for (int i = 0; i < 3; i++) {
			System.out.println(min.deleteMin().getBirthTime());
		}

	}

}

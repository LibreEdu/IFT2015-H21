package pedigree;

public class MaxHeapTest {
	
	private static MaxHeap h = new MaxHeap();

	public static void main(String[] args) {
		h.insert(new Sim(null, null, 0, Sim.Sex.M));
		h.insert(new Sim(null, null, 2, Sim.Sex.M));
		h.insert(new Sim(null, null, 1, Sim.Sex.M));
		
		for (int i = 0; i < 3; i++) {
			Sim t = h.deleteMax();
			System.out.println(t.getBirthTime());			
		}
	}

}

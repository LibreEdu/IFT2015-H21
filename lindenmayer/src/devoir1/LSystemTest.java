package devoir1;

public class LSystemTest {

	private LSystem l;
	
	public LSystemTest() {
		System.out.println("Test LSystem");
		l = new LSystem(3, 22.5, 250, 0, 90);
		l.setAxiom("F");
	}

}

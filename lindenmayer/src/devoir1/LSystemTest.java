package devoir1;

public class LSystemTest {

	private LSystem s;
	
	public LSystemTest() {
		System.out.println("Test LSystem");
		s = new LSystem(3, 22.5, 250, 0, 90);
		s.addSymbol('F');
		s.addSymbol('[');
		s.addSymbol(']');
		s.addSymbol('+');
		s.addSymbol('-');
		//s.addRule('F', "FF-[-F+F+F]+[+F-F-F]");
		s.addRule('F', "FF-F");
		s.setAction('F', "draw");
		s.setAction('[', "push");
		s.setAction(']', "pop");
		s.setAction('+', "turnL");
		s.setAction('-', "turnR");
		s.setAxiom("F");
		System.out.println("\t Axiom : " + s.toString(s.getAxiom()));
		System.out.println("\t Rule : " + s.toString(s.addSymbol('F').getRule()));
		System.out.println("Fin test LSystem");
	}

}

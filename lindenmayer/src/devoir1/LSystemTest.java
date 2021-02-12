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
		//s.toString(s.getAxiom());
		//s.toString(s.addSymbol('F').getRules());
		//s.toString(s.applyRules(s.getAxiom(), 1));
	}

}

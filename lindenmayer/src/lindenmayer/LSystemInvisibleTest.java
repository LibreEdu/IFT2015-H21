package lindenmayer;

public class LSystemInvisibleTest {

	private LSystem ls;
	
	public LSystemInvisibleTest() {
		System.out.println("  Début test LSystem");
		ls = new LSystemInvisible();
		ls.addSymbol('F');
		ls.addSymbol('-');
		ls.addRule('F', "FF-F");
		ls.setAction('F', "draw");
		ls.setAction('-', "turnR");
		ls.setAxiom("F");
		System.out.println("    Axiom  : " + ls.toString(ls.getAxiom()));
		System.out.println("    Symb F : " + ls.toString(ls.addSymbol('F')));
		System.out.println("    Rule F : " + ls.toString(ls.rewrite(ls.addSymbol('F'))));
		System.out.println("    Symb - : " + ls.toString(ls.addSymbol('-')));
		System.out.println("    Rule - : " + ls.toString(ls.rewrite(ls.addSymbol('-'))));
		System.out.println("    S0     : " + ls.toString(ls.applyRules(ls.getAxiom(), 0)));
		System.out.println("    S1     : " + ls.toString(ls.applyRules(ls.getAxiom(), 1)));
		System.out.println("    S2     : " + ls.toString(ls.applyRules(ls.getAxiom(), 2)));
		System.out.println("  Fin test LSystem");
	}

}

package lindenmayerTest;

import java.io.IOException;

import lindenmayer.ReadJSON;

public class ReadJSONTest {

	private LSystemString ls;
	private TortueVirtuelle t;
	private ReadJSON readJSON;
	
	ReadJSONTest() {
        ls = new LSystemString();
        t = new TortueVirtuelle();
        readJSON = new ReadJSON();	
	}
	
	public void test() {
		System.out.println("  Début test ReadJSON");
		try {
			readJSON.readFile("./test/herbe.json", t, ls);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("    Rule F : " + ls.toString(ls.rewrite(ls.addSymbol('F'))));
		System.out.println("    Rule F : " + ls.toString(ls.rewrite(ls.addSymbol('F'))));
		System.out.println("    Rule F : " + ls.toString(ls.rewrite(ls.addSymbol('F'))));
		System.out.println("    Axiom  : " + ls.toString(ls.getAxiom()));
		System.out.println("    S0     : " + ls.toString(ls.applyRules(ls.getAxiom(), 0)));
		System.out.println("    S1     : " + ls.toString(ls.applyRules(ls.getAxiom(), 1)));
		System.out.println("    S2     : " + ls.toString(ls.applyRules(ls.getAxiom(), 2)));
		System.out.println("    init   : (" + t.getPosition().getX() + "," + 
				t.getPosition().getY() + "," + t.getAngle() + ")");
		System.out.println("    step   : " + t.getStep());
		try {
			readJSON.readFile("./test/sierpinski.json", t, ls);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("    Rule L : " + ls.toString(ls.rewrite(ls.addSymbol('L'))));
		System.out.println("    Rule R : " + ls.toString(ls.rewrite(ls.addSymbol('R'))));
		System.out.println("    Axiom  : " + ls.toString(ls.getAxiom()));
		System.out.println("    S0     : " + ls.toString(ls.applyRules(ls.getAxiom(), 0)));
		System.out.println("    S1     : " + ls.toString(ls.applyRules(ls.getAxiom(), 1)));
		System.out.println("    S2     : " + ls.toString(ls.applyRules(ls.getAxiom(), 2)));
		System.out.println("  Fin test ReadJSON");
	}
	
}

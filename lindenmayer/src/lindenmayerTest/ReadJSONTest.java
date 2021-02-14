package lindenmayerTest;

import java.io.IOException;

import lindenmayer.ReadJSON;

public class ReadJSONTest {

	private LSystemString ls;
	private TortueVirtuelle turtle;
	private ReadJSON readJSON;
	
	ReadJSONTest() {
        ls = new LSystemString();
        turtle = new TortueVirtuelle();
        readJSON = new ReadJSON();	
	}
	
	public void test() {
		System.out.println("  Début test ReadJSON");
		try {
			readJSON.readFile("./test/herbe.json", turtle, ls);
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
		try {
			readJSON.readFile("./test/sierpinski.json", turtle, ls);
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

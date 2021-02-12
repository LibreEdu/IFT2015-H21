package devoir1;

import lindenmayer.Symbol;

public class SequenceTest {

	private SequenceTest s;
	
	public SequenceTest() {
		System.out.println("  Début test Sequence");
		LSystem ls = new LSystem();
		ls.addSymbol('F');
		ls.addSymbol('-');
		ls.addRule('F', "FF-F");
		Symbol.Seq s = ls.rewrite(ls.addSymbol('F'));
		System.out.println("    Séquence : " + ls.toString(s));
		System.out.println("  Fin test Sequence");
	}

}

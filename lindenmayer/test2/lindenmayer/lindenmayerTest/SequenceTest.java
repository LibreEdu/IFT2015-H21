package lindenmayerTest;

import lindenmayer.Sequence;
import lindenmayer.Symbol;

public class SequenceTest {
	
	public void test() {
		System.out.println("  Début test Sequence");
		LSystemString ls = new LSystemString();
		ls.addSymbol('F');
		ls.addSymbol('-');
		ls.addRule('F', "FF-F");
		Symbol.Seq s1 = ls.rewrite(ls.addSymbol('F'));
		System.out.println("    Séquence s1    : " + ls.toString(s1));
		Symbol.Seq s2 = new Sequence();
		s2.add(ls.addSymbol('F'));
		System.out.println("    Séquence s2    : " + ls.toString(s2));
		s2.add(s1);
		System.out.println("    Séquence s2+s1 : " + ls.toString(s2));
		System.out.println("  Fin test Sequence");
	}

}

package lindenmayerTest;

import lindenmayer.LSystem;
import lindenmayer.Symbol;

public class LSystemString extends LSystem {

	public String toString(Symbol.Seq seq) {
		String string = new String();
		for(int i = 0; i < seq.size(); i++) {
			string += seq.get(i).toString();
		}
		return string;
	}
	
	public String toString(Symbol sym) {
		return sym.toString();
	}

}

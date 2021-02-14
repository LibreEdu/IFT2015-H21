package lindenmayer;

import java.awt.geom.Rectangle2D;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;

import lindenmayer.Symbol.Seq;

public class LSystem extends AbstractLSystem {
	
	private HashMap<Character, Symbol> alphabet;
	private Seq axiom;

	public LSystem() {
		alphabet = new HashMap<Character, Symbol>();
	}
	
	@Override
	public Symbol addSymbol(char sym) {
		alphabet.putIfAbsent(sym, new Symbol(sym));
		return alphabet.get(sym);
	}

	@Override
	public void addRule(Symbol sym, String expansion) {
		sym.addRule(string2seq(expansion));
	}

	public void addRule(char key, String expansion) {
		addRule(alphabet.get(key), expansion);
	}

	@Override
	public void setAction(Symbol sym, String action) {
		sym.setAction(action);
	}

	public void setAction(char key, String action) {
		setAction(alphabet.get(key), action);
	}

	@Override
	public void setAxiom(String str) {
		axiom = string2seq(str);
	}

	@Override
	public Symbol.Seq getAxiom() {
		return axiom;
	}

	@Override
	public Symbol.Seq rewrite(Symbol sym) {
		Symbol.Seq seq = sym.getRule();
		if (seq != null) {
			return seq;
		} else {
			seq = new Sequence();
			seq.add(sym);
			return seq;
		}
	}

	@Override
	public void tell(Turtle turtle, Symbol.Seq seq) {
		Iterator<Symbol> it = seq.iterator();
		for(int i = 0; i < seq.size(); i++) {
			turtleAction(turtle, it.next().getAction());
		}
	}
	
	public void turtleAction(Turtle turtle, String action) {
		// https://stackoverflow.com/questions/22419511/how-to-pass-method-name-dynamically-in-java
		try {
			Method method = 
					turtle.getClass().getMethod(action);
			method.invoke(turtle);
		} catch (NoSuchMethodException | SecurityException | 
				IllegalAccessException | IllegalArgumentException | 
				InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	public Rectangle2D tell2D(Turtle turtle, Symbol.Seq seq) {
		Iterator<Symbol> it = seq.iterator();
		for(int i = 0; i < seq.size(); i++) {
			turtleAction2D(turtle, it.next().getAction());
		}
		return null;
	}
	
	public Rectangle2D turtleAction2D(Turtle turtle, String action) {
		
		turtleAction(turtle, action);
		
		return null;
	}
	
	@Override
	public Symbol.Seq applyRules(Symbol.Seq seq, int n) {
		if (n < 1) {
			return seq;
		}
		Symbol.Seq sequence = new Sequence();
		for(int i = 0 ; i < seq.size(); i++) {
			sequence.add(rewrite(seq.get(i)));
		}
		return applyRules(sequence, n-1);
	}

	@Override
	public Rectangle2D tell(Turtle turtle, Symbol.Seq seq, int rounds) {
		if (rounds == 1) {
			tell(turtle, seq);
		}
		return null;
	}
	
	private Symbol.Seq string2seq(String str) {
		Sequence sequence = new Sequence();
		for(int i = 0; i < str.length(); i++) {
			// Add to the sequence the symbol corresponding to the character
			sequence.add(alphabet.get(str.charAt(i)));
		}
		return sequence;
	}

}

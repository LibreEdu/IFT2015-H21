package devoir1;

import java.awt.geom.Rectangle2D;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

import lindenmayer.AbstractLSystem;
import lindenmayer.Symbol;
import lindenmayer.Symbol.Seq;
import lindenmayer.Turtle;

public class LSystem extends AbstractLSystem{
	
	private HashMap<Character, Symbol> alphabet;
	private Seq axiom = null;

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
		sym.addRule(strin2seq(expansion));
	}

	@Override
	public void setAction(Symbol sym, String action) {
		sym.setAction(action);
	}

	@Override
	public void setAxiom(String str) {
		axiom = strin2seq(str);
	}

	@Override
	public Seq getAxiom() {
		return axiom;
	}

	@Override
	public Seq rewrite(Symbol sym) {
		return sym.getRules();
	}

	@Override
	public void tell(Turtle turtle, Symbol sym) {
		// https://stackoverflow.com/questions/22419511/how-to-pass-method-name-dynamically-in-java
		try {
			Method method = turtle.getClass().getMethod(sym.getAction());
			method.invoke(turtle);
		} catch (NoSuchMethodException | SecurityException | 
				IllegalAccessException | IllegalArgumentException | 
				InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Seq applyRules(Seq seq, int n) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Rectangle2D tell(Turtle turtle, Symbol sym, int rounds) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private Seq strin2seq(String str) {
		Seq seq = null ;
		for(int i = 0; i < str.length(); i++) {
			// Add to the sequence the symbol corresponding to the character
			seq.add(alphabet.get(str.charAt(i)));
		}
		return seq;
	}

}

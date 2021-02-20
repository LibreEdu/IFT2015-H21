package lindenmayer;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;

import lindenmayer.Symbol.Seq;

/**
 * Implementation of the class AbstractLSystem
 * 
 * @author Alexandre Pachot
 * @author Dave Sanon-Abraham
 */
public class LSystem extends AbstractLSystem {
	
	private HashMap<Character, Symbol> alphabet;
	private Seq axiom;
	private Rectangle2D rectangle2D;

	public LSystem() {
		alphabet = new HashMap<Character, Symbol>();
		rectangle2D = new Rectangle2D.Double();		
	}
	
	/**
	 * In order to be able to initialize the rectangle with the starting point
	 * specified in the JSON file.
	 */
	public void init(Point2D pos) {
		rectangle2D.setFrame(pos.getX(), pos.getY(), 0, 0);
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

	/**
	 * In order to be able to add a ruler by specifying the character related to
	 * the symbol, rather than the symbol itself.
	 */
	public void addRule(char key, String expansion) {
		addRule(alphabet.get(key), expansion);
	}

	@Override
	public void setAction(Symbol sym, String action) {
		sym.setAction(action);
	}

	/**
	 * In order to be able to link the action to the symbol by specifying the
	 * character linked to the symbol, rather than the symbol itself.
	 */
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
	
	/**
	 * Method for dynamically calling the turtle method.
	 */
	public void turtleAction(Turtle turtle, String action) {
		// https://stackoverflow.com/questions/22419511/how-to-pass-method-name-dynamically-in-java
		try {
			Method method = 
					turtle.getClass().getMethod(action);
			rectangle2D.add(turtle.getPosition());
			method.invoke(turtle);
		} catch (NoSuchMethodException | SecurityException | 
				IllegalAccessException | IllegalArgumentException | 
				InvocationTargetException e) {
			e.printStackTrace();
		}
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
		if (rounds == 0) {
			tell(turtle, seq);
			rectangle2D.add(turtle.getPosition());
		} else {
			for(int i = 0 ; i < seq.size(); i++) {
				tell(turtle, rewrite(seq.get(i)), rounds - 1);
				rectangle2D.add(turtle.getPosition());
			}
		}
		return rectangle2D;
	}
	
	/**
	 * Allows you to transform a string into a sequence of symbols.
	 */
	private Symbol.Seq string2seq(String str) {
		Sequence sequence = new Sequence();
		for(int i = 0; i < str.length(); i++) {
			// Add to the sequence, the symbol corresponding to the character
			sequence.add(alphabet.get(str.charAt(i)));
		}
		return sequence;
	}

}

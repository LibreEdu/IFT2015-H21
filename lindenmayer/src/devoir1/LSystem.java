package devoir1;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.HashMap;

import lindenmayer.AbstractLSystem;
import lindenmayer.Symbol;
import lindenmayer.Symbol.Seq;
import lindenmayer.Turtle;

public class LSystem extends AbstractLSystem{
	
	private HashMap<Character, Symbol> alphabet = new HashMap<Character, Symbol>();
	
	// Univers des symboles possibles, qui peuvent être dans l’alphabet
	private Symbol push = new Symbol(('['));
	private Symbol pop = new Symbol((']'));
	private Symbol turnL = new Symbol(('+'));
	private Symbol turnR = new Symbol(('-'));
	private Symbol draw = new Symbol(('F'));
	private Symbol move = new Symbol(('f'));

	public LSystem() {
	}

	@Override
	public Symbol addSymbol(char sym) {
		Symbol symbol = null;
		switch(sym) {
			case '[' : 
				symbol = push; 
				break;
			case ']' : 
				symbol = pop; 
				break;
			case '+' : 
				symbol = turnL;
				break;
			case '-' : 
				symbol = turnR;
				break;
			case 'F' : 
				symbol = draw;
				break;
			case 'f' : 
				symbol = move;
				break;
		}
		alphabet.putIfAbsent(sym, symbol);
		return alphabet.get(sym);
	}

	@Override
	public void addRule(Symbol sym, String expansion) {
		switch(sym.toString()) {
			case "[" :
				push.addRule(expansion);
				break;
			case "]" :
				pop.addRule(expansion);
				break;
			case "+" :
				turnL.addRule(expansion);
				break;
			case "-" :
				turnR.addRule(expansion);
				break;
			case "F" :
				draw.addRule(expansion);
				break;
			case "f" : 
				move.addRule(expansion);
				break;
		}	
	}

	@Override
	public void setAction(Symbol sym, String action) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setAxiom(String str) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Seq getAxiom() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Seq rewrite(Symbol sym) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void tell(Turtle turtle, Symbol sym) {
		// TODO Auto-generated method stub
		
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

}

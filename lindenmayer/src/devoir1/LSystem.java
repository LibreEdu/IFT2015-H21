package devoir1;

import java.awt.geom.Rectangle2D;

import lindenmayer.AbstractLSystem;
import lindenmayer.Symbol;
import lindenmayer.Symbol.Seq;
import lindenmayer.Turtle;

public class LSystem extends AbstractLSystem{

	public LSystem() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Symbol addSymbol(char sym) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addRule(Symbol sym, String expansion) {
		// TODO Auto-generated method stub
		
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

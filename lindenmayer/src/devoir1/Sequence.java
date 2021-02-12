package devoir1;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import lindenmayer.Symbol;

public class Sequence implements Symbol.Seq {
	
	private List<Symbol> list;

	public Sequence() {
		list = new LinkedList<Symbol>();
	}

	@Override
	public Iterator<Symbol> iterator() {
		return list.iterator();
	}
	
	/**
	 * Appends the symbol to the end of this list.
	 * 
	 * @param s symbol to be added
	 */
    public void add(Symbol s) {
    	list.add(s);
    }
    
    public int size() {
    	return list.size();
    }
    
    public Symbol get(int index) {
    	return list.get(index);
    }
    
    public void set(int index, Symbol.Seq seq) {
    	for(int i = 0; i < seq.size(); i++) {
    		list.set(index + i, seq.get(i));
    	}
    }

}
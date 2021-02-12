package devoir1;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import lindenmayer.Symbol;

/**
 * Class managing a sequence of symbols
 * 
 * @author Alexandre Pachot
 *
 */
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
    
    /**
     * Returns the size of the sequence
     */
    public int size() {
    	return list.size();
    }
    
    /**
     * Returns the ith symbol of the sequence
     */
    public Symbol get(int index) {
    	return list.get(index);
    }
    
    /**
     * Replace the ith symbol by a sequence
     */
    public void set(int index, Symbol.Seq seq) {
    	LSystem s = new LSystem();
    	for(int i = 0; i < seq.size(); i++) {
    		System.out.print("set(" + i + ", " + seq.size() + ", " + index + ", ");
    		s.toString(seq);
    		System.out.println(seq.get(i).toString());
    		//list.set(index + i, seq.get(i));
    	}
    }

}
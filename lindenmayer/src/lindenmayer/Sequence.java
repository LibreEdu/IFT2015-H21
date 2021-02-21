package lindenmayer;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Class managing a sequence of symbols
 * 
 * @author Alexandre Pachot
 * @author Dave Sanon-Abraham
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
    public void add(Symbol sym) {
    	list.add(sym);
    }
    
	/**
	 * Appends the sequence to the end of this list.
	 * 
	 * @param s symbol to be added
	 */
    public void add(Symbol.Seq seq) {
		Iterator<Symbol> it = seq.iterator();
		for(int i = 0; i < seq.size(); i++) {
			list.add(it.next());
		}
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

}
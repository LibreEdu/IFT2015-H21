package devoir1;


import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import lindenmayer.Symbol;

public class Seq implements Symbol.Seq{
	
	private List<Symbol> list;

	public Seq() {
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

}

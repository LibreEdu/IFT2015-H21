package lindenmayer;

import java.util.ArrayList;
import java.util.Random;

/**
 * Symbol in an L-system's alphabet. 
 * 
 * @author Miklós Csűrös
 * @author Alexandre Pachot
 */
public class Symbol 
{
    private final char value;
    private ArrayList<Symbol.Seq> rules;
    private String action;
    
    public Symbol(char c) {
        this.value = c;
        rules = new ArrayList<Seq>();
    }
    
    @Override
    public String toString() {
        return Character.toString(value);
    }
    
    /**
     * Common interface to a string of symbols. 
     * 
     */
    public interface Seq extends Iterable<Symbol> {
		int size();
	}
    
    public void addRule(Symbol.Seq rule) {
    	rules.add(rule);
    }
    
    public void setAction(String action) {
    	this.action = action;
    }
    
	public String getAction() {
		return action;
	}
	
    public int getSize() {
    	return rules.size();
    }
    
    public Seq getRules(int rule) {
    	return rules.get(rule);
    }
    
    public Seq getRules() {
		switch(getSize()) {
		case 0 :
			return null;
		case 1 :
			return getRules(1);
		default :
			return getRules(new Random().nextInt(getSize()));
		}
    }

}

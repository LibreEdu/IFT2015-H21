package lindenmayer;

import java.util.ArrayList;
import java.util.Random;

/**
 * Symbol in an L-system's alphabet. 
 * 
 * @author Miklós Csűrös
 * @author Alexandre Pachot
 * @author Dave Sanon-Abraham
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
		Symbol get(int index);
		void add(Symbol sym);
		void add(Symbol.Seq seq);
	}
    
    /**
     * Adds a rule to the list of rules
     * @param rule The rule to be added
     */
    public void addRule(Symbol.Seq rule) {
    	rules.add(rule);
    }
    
    /**
     * Each symbol corresponds to an action. The name of the action must 
     * correspond to the turtle method. 
     * @param action Action corresponding to the symbol
     */
    public void setAction(String action) {
    	this.action = action;
    }
    
	public String getAction() {
		return action;
	}
	
    public int getSize() {
    	return rules.size();
    }
    
    public Symbol.Seq getRule(int rule) {
    	return rules.get(rule);
    }
    
    /**
     * Returns one of the rules that applies to the symbol
     * @return rule
     */
    public Seq getRule() {
		switch(getSize()) {
		case 0 :
			return null;
		case 1 :
			return getRule(0);
		default :
			return getRule(new Random().nextInt(getSize()));
		}
    }

}

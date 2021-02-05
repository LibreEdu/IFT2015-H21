package lindenmayer;

import java.util.ArrayList;

/**
 * Symbol in an L-system's alphabet. 
 * 
 * @author Miklós Csűrös
 */
public class Symbol 
{
    private final char value;
    private ArrayList<String> rules = new ArrayList<String>();
    
    public Symbol(char c)
    {
        this.value = c;
    }
    
    @Override
    public String toString()
    {
        return Character.toString(value);
    }
    
    /**
     * Common interface to a string of symbols. 
     * 
     */
    public interface Seq extends Iterable<Symbol>
    {}
    
    public void addRule(String expansion) {
    	rules.add(expansion);
    }
}

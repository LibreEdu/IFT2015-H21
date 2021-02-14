package devoir1;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import org.json.*;

import lindenmayer.AbstractLSystem;
import lindenmayer.Symbol;
import lindenmayer.Symbol.Seq;
import lindenmayer.Turtle;

public class LSystem extends AbstractLSystem{
	
	private HashMap<Character, Symbol> alphabet;
	private Seq axiom;
	private double step;
	private double delta;
	private Turtle turtle;

	public LSystem() {
		alphabet = new HashMap<Character, Symbol>();
		turtle = new Tortue();
	}

	public LSystem(double step, double delta, double x, double y,
			double theta) {
		this();
		setParameters(step, delta, x, y, theta);
	}

	public void setParameters(double step, double delta, double x, double y, 
			double theta) {
		this.step = step;
		this.delta = delta;
		turtle.init(new Point2D.Double(x,y), theta);
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

	public void addRule(char key, String expansion) {
		addRule(alphabet.get(key), expansion);
	}

	@Override
	public void setAction(Symbol sym, String action) {
		sym.setAction(action);
	}

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
	public Seq rewrite(Symbol sym) {
		return sym.getRule();
	}

	@Override
	public void tell(Turtle turtle, Symbol.Seq seq) {
		Iterator<Symbol> it = seq.iterator();
		for(int i = 0; i < seq.size(); i++) {
			// https://stackoverflow.com/questions/22419511/how-to-pass-method-name-dynamically-in-java
			try {
				Method method = 
						turtle.getClass().getMethod(it.next().getAction());
				method.invoke(turtle);
			} catch (NoSuchMethodException | SecurityException | 
					IllegalAccessException | IllegalArgumentException | 
					InvocationTargetException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public Symbol.Seq applyRules(Symbol.Seq seq, int n) {
		if (n < 0) {
			System.out.println("n < 0");
			return seq;
		}
		for(int i = 0 ; i < seq.size(); i++) {
			System.out.println("n = " + n + ", i = " + i);
			Symbol.Seq sequence = applyRules(seq.get(i).getRule(), n-1);
			toString(sequence);
			seq.set(i, sequence);
		}
		return seq;
	}

	@Override
	public Rectangle2D tell(Turtle turtle, Symbol sym, int rounds) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private Symbol.Seq string2seq(String str) {
		Sequence sequence = new Sequence();
		for(int i = 0; i < str.length(); i++) {
			// Add to the sequence the symbol corresponding to the character
			sequence.add(alphabet.get(str.charAt(i)));
		}
		return sequence;
	}
	public void appel(String file) throws IOException {
		LSystem s = new LSystem(3, 22.5, 250, 0, 90);
		Tortue t = new Tortue();
		readJSONFile(file, s, t);
	}
	public static void readJSONFile(String file, LSystem S, Turtle T) throws IOException {
        JSONObject input = new JSONObject(new JSONTokener(new java.io.FileReader(file)));
        JSONArray alphabet = input.getJSONArray("alphabet");
        JSONObject actions = input.getJSONObject("actions");
        JSONObject regles = input.getJSONObject("rules");
       // JSONObject parameters = input.getJSONObject("parameters");
       // int step = parameters.getInt("step");
       // BigDecimal angle = parameters.getBigDecimal("angle");    
       // JSONArray start = parameters.getJSONArray("start");
        
        // Register alphabet
        for (int i = 0; i < alphabet.length(); i++) {
            String letter = alphabet.getString(i);
            S.addSymbol(letter.charAt(0));
        }
        

        for (Iterator<String> it = regles.keys(); it.hasNext();) {
            String prochain = it.next();
            //JSONArray valeur = (JSONArray) regles.get(value);
            //System.out.println(valueRule);
            //S.addRule(S.getAssociations().get(value.charAt(0)), valueRule.get(0).toString());
        }
        

        for (Iterator<String> it = actions.keys(); it.hasNext();) {
            String value = it.next();
            String valueAction = (String) actions.get(value);
            System.out.println(valueAction);
            //S.setAction(S.getAssociations().get(value.charAt(0)), valueAction);
        }
        S.setAxiom(input.getString("axiom"));
    }        
	
	    
	
	// For test purposes only
	public String toString(Symbol.Seq seq) {
		String string = "";
		for(int i = 0; i < seq.size(); i++) {
			string += seq.get(i).toString();
		}
		return string;
	}

}

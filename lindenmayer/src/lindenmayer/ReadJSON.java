package lindenmayer;

import java.awt.geom.Point2D;
import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

/**
 * Class to read the JSON file and initialize the system.
 * 
 * @author Alexandre Pachot
 * @author Dave Sanon-Abraham
 */
public class ReadJSON {
	public void readFile(String file, AbstractTurtle t, LSystem ls)
			throws IOException {
        JSONObject input = new JSONObject(new JSONTokener(
        		new java.io.FileReader(file)));
        
        JSONArray alphabet = input.getJSONArray("alphabet");
        for (int i = 0; i < alphabet.length(); i++) {
            ls.addSymbol(alphabet.getString(i).charAt(0));
        }
        
        JSONObject rules = input.getJSONObject("rules");
        ArrayList<String> symboles = new ArrayList<String>(rules.keySet());
        String symbole;
        JSONArray regles;
        for (int i = 0; i < symboles.size(); i++) {
        	symbole = symboles.get(i);
            regles = rules.getJSONArray(symbole);
            for (int j = 0; j < regles.length(); j++) {
            	ls.addRule(symbole.charAt(0), regles.getString(j));
            }
        }
        
        ls.setAxiom(input.getString("axiom"));
        
        JSONObject actions = input.getJSONObject("actions");
        symboles = new ArrayList<String>(actions.keySet());
        for (int i = 0; i < symboles.size(); i++) {
        	symbole = symboles.get(i);
        	ls.setAction(symbole.charAt(0), actions.getString(symbole));
        }
        
        JSONObject parameters = input.getJSONObject("parameters");
        JSONArray start = parameters.getJSONArray("start");
        Point2D pos = new Point2D.Double(start.getDouble(0),start.getDouble(1));
        t.init(pos, start.getDouble(2));
        t.setUnits(parameters.getDouble("step"), parameters.getDouble("angle"));
        ls.init(pos);
	}

}

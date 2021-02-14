package lindenmayer;

import java.awt.geom.Point2D;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

public class ReadJSON {

	public ReadJSON() {
		// TODO Auto-generated constructor stub
	}
	
	public static void readFile(String file, AbstractTurtle t, LSystem ls) throws IOException {
        JSONObject input = new JSONObject(new JSONTokener(new java.io.FileReader(file)));
        JSONArray alphabet = input.getJSONArray("alphabet");
        JSONObject actions = input.getJSONObject("actions");
        JSONObject regles = input.getJSONObject("rules");
        JSONObject parameters = input.getJSONObject("parameters");
        int step = parameters.getInt("step");
        double delta = parameters.getDouble("angle");
        JSONArray start = parameters.getJSONArray("start");
        double x = start.getDouble(0);
        double y = start.getDouble(1);
        double angle = start.getDouble(2);
        ArrayList<String> listA = new ArrayList<String>(actions.keySet());
        ArrayList<String> listR = new ArrayList<String>(regles.keySet());
        t.setUnits(step, delta);
        t.init(new Point2D.Double(x,y), angle);
        ls.setAxiom(input.getString("axiom"));
        // Ajoute l'alphabet
        for (int i = 0; i < alphabet.length(); i++) {
            String letter = alphabet.getString(i);
            ls.addSymbol(letter.charAt(0));
            System.out.println(letter.charAt(0));
        }
       
        for (int i=0; i<listR.size() ; i++) {
            String key = listR.get(i) ;
            char clee= key.charAt(0);
            JSONArray regle = regles.getJSONArray(key);
            for (int j=0; j<regle.length();j++) {
            	ls.addRule(clee, regle.getString(j));
            	System.out.println(clee+" "+regle.getString(j));
            }
        }
        for(int i=0; i<listA.size();i++) {
        	String key = listA.get(i);
        	char clee = key.charAt(0);
        	ls.setAction(clee, actions.getString(key));
        	System.out.println(clee +" "+ actions.getString(key));
        }
	}

}

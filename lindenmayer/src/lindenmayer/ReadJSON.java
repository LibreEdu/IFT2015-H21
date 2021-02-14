package lindenmayer;

import java.awt.geom.Point2D;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Iterator;

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
        t.setUnits(step, delta);
        t.init(new Point2D.Double(x,y), angle);
        ls.setAxiom(input.getString("axiom"));
        // Ajoute l'alphabet
        for (int i = 0; i < alphabet.length(); i++) {
            String letter = alphabet.getString(i);
            ls.addSymbol(letter.charAt(0));
        }
       System.out.print(actions.get(0));
    /*
        for (Iterator<String> it = regles.keys(); it.hasNext();) {
            String prochain = it.next();
            JSONArray valeur = (JSONArray) regles.get(prochain);
            System.out.println(valeur.get(0));
            //S.addRule(S.getAssociations().get(value.charAt(0)), valueRule.get(0).toString());
        }
        
/*
        for (Iterator<String> it = actions.keys(); it.hasNext();) {
            String value = it.next();
            String valueAction = (String) actions.get(value);
            System.out.println(valueAction);
            //S.setAction(S.getAssociations().get(value.charAt(0)), valueAction);
        }
        //*/
    }

}

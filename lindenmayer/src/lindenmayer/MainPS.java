package lindenmayer;

import java.awt.geom.Rectangle2D;
import java.io.IOException;

public class MainPS {
	private static LSystem lsystem;
	private static TortuePS turtle;
	private static ReadJSON readJSON;
	
	public static void main(String[] args) {
        lsystem = new LSystem();
        readJSON = new ReadJSON();
        turtle = new TortuePS();
        
        try {
			readJSON.readFile(args[0], turtle, lsystem);
		} catch (IOException e) {
			e.printStackTrace();
		}
        int nbRounds = Integer.parseInt(args[1]);
        //fileStart();
        Rectangle2D rectangle2D = lsystem.tell(turtle, lsystem.getAxiom(),
        		nbRounds);
        //fileEnd(Rectangle2D);
	}
}

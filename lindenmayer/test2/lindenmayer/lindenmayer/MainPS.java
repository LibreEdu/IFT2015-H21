package lindenmayer;

import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.util.Locale;

/**
 * Class that launches the PostScript part of the program.
 * 
 * @author Alexandre Pachot
 * @author Dave Sanon-Abraham
 */
public class MainPS {
	private LSystem lsystem;
	private TortuePS turtle;
	private ReadJSON readJSON;
	
	public MainPS() {
        lsystem = new LSystem();
        readJSON = new ReadJSON();
        turtle = new TortuePS();
        
        // In order for the decimal separator to be the dot in a println.
        Locale.setDefault(Locale.US);
	}
	
	public void run(String file, int nbRounds) {
        try {
			readJSON.readFile(file, turtle, lsystem);
		} catch (IOException e) {
			e.printStackTrace();
		}
        
		fileStart();
        Rectangle2D rectangle2D = lsystem.tell(turtle, lsystem.getAxiom(),
        		nbRounds);
        fileEnd(rectangle2D);
	}
	
	private void fileStart() {
		print("%!PS-Adobe-3.0 EPSF-3.0");
		print("%%Title: L-system");
		print("%%Creator: lsystem.MainPS" );
		print("%%BoundingBox: (atend)");
		print("%%EndComments");
		print("/M {moveto} bind def");
		print("/L {lineto} bind def");
		print("0.5 setlinewidth");
		print(turtle.getPosition().getX() + " " + turtle.getPosition().getY() +
				" newpath moveto");
	}
	
	private void print(String message) {
		System.out.println(message);
	}
	
	private void fileEnd(Rectangle2D r) {
		print("stroke");
		print("%%Trailer");
		print("%%BoundingBox: " + (int) r.getX() + " " + (int) r.getY() + " " + 
				(int)(r.getX() + r.getWidth()) + " " + 
				(int)(r.getY() + r.getHeight()));
		print("%%EOF");		
	}
	
}

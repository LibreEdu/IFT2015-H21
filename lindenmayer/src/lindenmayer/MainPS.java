package lindenmayer;

import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.util.Locale;

public class MainPS {
	private static LSystem lsystem;
	private static TortuePS turtle;
	private static ReadJSON readJSON;
	
	public static void main(String[] args) {
        lsystem = new LSystem();
        readJSON = new ReadJSON();
        turtle = new TortuePS();
        
        Locale.setDefault(Locale.US);

        try {
			//readJSON.readFile(args[0], turtle, lsystem);
           	//readJSON.readFile("./test/test.json", turtle, lsystem);
           	readJSON.readFile("./test/buisson.json", turtle, lsystem);
           	//readJSON.readFile("./test/herbe.json", turtle, lsystem);
           	//readJSON.readFile("./test/hexamaze.json", turtle, lsystem);
           	//readJSON.readFile("./test/sierpinski.json", turtle, lsystem);
		} catch (IOException e) {
			e.printStackTrace();
		}
        
		fileStart();
		//int nbRounds = Integer.parseInt(args[1]);
		int nbRounds = 2;
        Rectangle2D rectangle2D = lsystem.tell(turtle, lsystem.getAxiom(),
        		nbRounds);
        fileEnd(rectangle2D);
	}
	
	static void fileStart() {
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
	
	static void print(String message) {
		System.out.println(message);
	}
	
	static void fileEnd(Rectangle2D r) {
		print("stroke");
		print("%%Trailer");
		print("%%BoundingBox: " + (int) r.getX() + " " + (int) r.getY() + " " + 
				(int)(r.getX() + r.getWidth()) + " " + 
				(int)(r.getY() + r.getHeight()));
		print("%%EOF");		
	}
	
}

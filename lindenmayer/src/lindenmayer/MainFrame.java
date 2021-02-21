package lindenmayer;

import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.io.IOException;

import javax.swing.JFrame;

/**
 * Class that allows to launch the graphical part of the program. The window 
 * displaying the result is visible for 6 seconds before being refreshed.
 * 
 * @author Alexandre Pachot
 * @author Dave Sanon-Abraham
 */
public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private String file;
	private int rounds;
	private LSystem lsystem;
	private TortueEcran turtle;
	private ReadJSON readJSON;
	
	public MainFrame(String file, int rounds) {
		this.file = file;
		this.rounds = rounds;
		setTitle(file + " for n = " + rounds);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        lsystem = new LSystem();
        readJSON = new ReadJSON();
    }

	public void paint(Graphics g) {
		// https://stackoverflow.com/questions/13136942/paint-in-jframe-not-working-java
        super.paint(g);
        turtle = new TortueEcran(g);
        try {
			readJSON.readFile(file, turtle, lsystem);
			turtle.init(turtle.getPosition(), -turtle.getAngle());
		} catch (IOException e) {
			e.printStackTrace();
		}
        Rectangle2D rectangle2D = lsystem.tell(turtle, lsystem.getAxiom(),
        		rounds);
        try {
			Thread.sleep(6000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        setSize((int)rectangle2D.getWidth() * 2,
        		(int)rectangle2D.getHeight() * 2);
        setLocationRelativeTo(null);
    }
    
}

package lindenmayer;

import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.io.IOException;

import javax.swing.JFrame;

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
		} catch (IOException e) {
			e.printStackTrace();
		}
        Rectangle2D rectangle2D = lsystem.tell(turtle, lsystem.getAxiom(),
        		rounds);
        setSize((int)rectangle2D.getWidth() + 10,
        		(int)rectangle2D.getHeight() + 10);
        System.out.println((int)rectangle2D.getWidth());
        System.out.println((int)rectangle2D.getHeight());
        setLocationRelativeTo(null);
    }
    
}

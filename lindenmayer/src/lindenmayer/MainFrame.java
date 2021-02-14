package lindenmayer;

import java.awt.Graphics;
import java.io.IOException;

import javax.swing.JFrame;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private LSystemEcran ls;
	private TortueEcran turtle;
	private ReadJSON readJSON;
	
	public MainFrame() {
		super("L-système");
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

	public void paint(Graphics g) {
        super.paint(g);
        turtle = new TortueEcran(g);
        ls = new LSystemEcran(turtle);
        readJSON = new ReadJSON();
        try {
			readJSON.readFile("./test/herbe.json", turtle, ls);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        ls.paint(g);
    }
	
	
    
}

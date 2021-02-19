package lindenmayer;

import java.awt.geom.Rectangle2D;
import java.io.IOException;

import javax.swing.SwingUtilities;

import lindenmayerTest.MainTest;

public class Main {
	private static LSystem lsystem;
	private static TortuePS turtle;
	private static ReadJSON readJSON;
	
	public static void main(String[] args) {
		/*System.out.println("coucou");
		System.out.println("coucou2");
		/*
		MainTest test = new MainTest();
		test.run();
		/*
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainFrame("./test/buisson.json", 3).setVisible(true);
                //new MainFrame("./test/herbe.json", 4).setVisible(true);
                //new MainFrame("./test/hexamaze.json", 5).setVisible(true);
                //new MainFrame("./test/sierpinski.json", 10).setVisible(true);
            }
        });
        //*/
        lsystem = new LSystem();
        readJSON = new ReadJSON();
        turtle = new TortuePS();
        
        try {
			readJSON.readFile(args[0], turtle, lsystem);
		} catch (IOException e) {
			e.printStackTrace();
		}
        int nbRounds = Integer.parseInt(args[1]);
        fileStart();
        Rectangle2D rectangle2D = lsystem.tell(turtle, lsystem.getAxiom(),
        		nbRounds);
        fileEnd(Rectangle2D);
	}

}

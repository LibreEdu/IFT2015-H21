package lindenmayer;

import java.awt.geom.Rectangle2D;
import java.io.IOException;

import javax.swing.SwingUtilities;

import lindenmayerTest.MainTest;

public class Main {

	
	public static void main(String[] args) {
		/*
		MainTest test = new MainTest();
		test.run();
		/*/
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
               // new MainFrame("./test/buisson.json", 5).setVisible(true);
                //new MainFrame("./test/herbe.json", 7).setVisible(true);
                new MainFrame("./test/hexamaze.json", 6).setVisible(true);
                //new MainFrame("./test/sierpinski.json", 8).setVisible(true);
            }
        });
        //*/
	}

}

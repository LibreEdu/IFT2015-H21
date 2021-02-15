package lindenmayer;

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
                //new MainFrame("./test/buisson.json", 3).setVisible(true);
                new MainFrame("./test/herbe.json", 4).setVisible(true);
                //new MainFrame("./test/hexamaze.json", 5).setVisible(true);
                //new MainFrame("./test/sierpinski.json", 10).setVisible(true);
            }
        });
        //*/
	}

}

package lindenmayer;

import javax.swing.SwingUtilities;

//import lindenmayerTest.MainTest;

public class Main {

	
	public static void main(String[] args) {	
		/*
		MainTest test = new MainTest();
		test.run();
		//*/

		if (args.length == 3) {
	        SwingUtilities.invokeLater(new Runnable() {
	            @Override
	            public void run() {
	            	int nbRounds = Integer.parseInt(args[1]);
	            	//new MainFrame("./test/buisson.json", 5).setVisible(true);
	                //new MainFrame("./test/herbe.json", 7).setVisible(true);
	                //new MainFrame("./test/hexamaze.json", 6).setVisible(true);
	                //new MainFrame("./test/sierpinski.json", 8).setVisible(true);
	            	new MainFrame(args[0], nbRounds).setVisible(true);
	            }
	        });			
		} else {
			MainPS mainPS = new MainPS();
			mainPS.run(args[0], Integer.parseInt(args[1]));
		}
        //*/
	}

}

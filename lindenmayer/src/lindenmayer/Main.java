package lindenmayer;

import javax.swing.SwingUtilities;

/**
 * Main class, the first argument is the path to the JSON file, the second 
 * argument is the number of iterations. If there is a third argument, no matter
 *  what it is, the result will be displayed on screen rather than in stream.
 * @author Alexandre Pachot
 * @author Dave Sanon-Abraham
 */
public class Main {	
	public static void main(String[] args) {	
		if (args.length == 3) {
	        SwingUtilities.invokeLater(new Runnable() {
	            @Override
	            public void run() {
	            	int nbRounds = Integer.parseInt(args[1]);
	            	new MainFrame(args[0], nbRounds).setVisible(true);
	            }
	        });			
		} else {
			MainPS mainPS = new MainPS();
			mainPS.run(args[0], Integer.parseInt(args[1]));
		}
	}

}

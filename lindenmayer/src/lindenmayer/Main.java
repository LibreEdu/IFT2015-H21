package lindenmayer;

import javax.swing.SwingUtilities;

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

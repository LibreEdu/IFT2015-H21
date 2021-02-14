package lindenmayer;

import javax.swing.SwingUtilities;

public class MainTest {

	public MainTest() {
		System.out.println("Début main");
		//*
		TortueInvisibleTest tortueInvisibleTest = new TortueInvisibleTest();
		System.out.println();
		SequenceTest sequenceTest = new SequenceTest();
		System.out.println();
		LSystemInvisibleTest lSystemInvisibleTest = new LSystemInvisibleTest();
		//*/
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TortueEcranTest().setVisible(true);
            }
        });
        //*/
		System.out.println("Fin main");
	}

}

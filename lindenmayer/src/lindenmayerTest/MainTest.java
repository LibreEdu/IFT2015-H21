package lindenmayerTest;

import javax.swing.SwingUtilities;

public class MainTest {

	public void run() {
		System.out.println("Début main");
		/*
		TortueVirtuelleTest tortue = new TortueVirtuelleTest();
		tortue.test();
		System.out.println();
		SequenceTest sequence = new SequenceTest();
		sequence.test();
		System.out.println();
		//*
		ReadJSONTest readJSONTest = new ReadJSONTest();
		readJSONTest.test();
		/*
		System.out.println();
		LSystemTest lSystem = new LSystemTest();
		lSystem.test();
		//*
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

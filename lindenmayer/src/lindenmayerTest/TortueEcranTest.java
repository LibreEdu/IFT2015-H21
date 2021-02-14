package lindenmayerTest;

import java.awt.Graphics;
import java.awt.geom.Point2D;

import javax.swing.JFrame;

import lindenmayer.TortueEcran;

public class TortueEcranTest extends JFrame {

	private static final long serialVersionUID = 1L;
	private TortueEcran t;
	
	public TortueEcranTest() {
		super("TortueEcranTest");
        setSize(300, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
	
    public void paint(Graphics g) {
        super.paint(g);
        t = new TortueEcran(g);
        t.init(new Point2D.Double(100,100), 90);
        t.setUnits(100, 90);
        for(int i = 0; i < 4; i++) {
        	t.draw();
        	t.turnL();
        }
    }
    
}

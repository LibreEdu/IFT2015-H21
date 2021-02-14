package lindenmayer;

import java.awt.Graphics;

import javax.swing.JFrame;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private TortueEcran t;
	
	public MainFrame() {
		super("L-système");
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
	
    public void paint(Graphics g) {
        super.paint(g);
        t = new TortueEcran(g);
    }
    
}

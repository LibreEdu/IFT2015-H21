package lindenmayer;

import java.awt.Graphics;
import java.awt.geom.Point2D;

public class LSystemEcran extends LSystem {

	private TortueEcran turtle;
	
	public LSystemEcran(Graphics g) {
		turtle = new TortueEcran(g);
	}
	
	public void setParameters(double step, double delta, double x, double y, 
			double theta) {
		super.setParameters(step, delta);
		turtle.init(new Point2D.Double(x,y), theta);
	}
}

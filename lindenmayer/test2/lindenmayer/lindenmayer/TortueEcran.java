package lindenmayer;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;

/**
 * Turtle-specific methods that generate PostScript code
 * 
 * @author Alexandre Pachot
 * @author Dave Sanon-Abraham
 */
public class TortueEcran extends AbstractTurtle {

	Graphics2D g2d;
	
	public TortueEcran(Graphics g) {
		g2d = (Graphics2D) g;
	}
	
	@Override
	public void draw() {
		Point2D pos = getPosition();
		int x2 = (int) (pos.getX()
				+ getStep() * Math.cos(Math.toRadians(getAngle())));
		int y2 = (int) (pos.getY()
				+ getStep() * Math.sin(Math.toRadians(getAngle())));
		g2d.drawLine((int)pos.getX(), (int)pos.getY(), x2, y2);
		move();
	}
	
}

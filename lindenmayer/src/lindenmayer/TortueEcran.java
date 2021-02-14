package lindenmayer;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;

public class TortueEcran extends AbstractTurtle {

	Graphics g;
	Graphics2D g2d;
	
	public TortueEcran(Graphics g) {
		this.g = g;
		g2d = (Graphics2D) g;
	}
	
	public void draw() {
		Point2D pos = getPosition();
		int x2 = (int) (pos.getX()
				+ getStep() * Math.cos(Math.toRadians(getAngle())));
		int y2 = (int) (pos.getY()
				+ getStep() * Math.sin(Math.toRadians(getAngle())));
		g2d.drawLine((int)pos.getX(), (int)pos.getY(), x2, y2);
		move();
	}

	@Override
	public void turnR() {
		super.turnL();
		
	}

	@Override
	public void turnL() {
		super.turnR();
	}
}

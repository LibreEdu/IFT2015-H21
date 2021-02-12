package devoir1;

import java.awt.geom.Point2D;

public class TortueTest {
	
	private Point2D pos;
	private Tortue t;
	
	public TortueTest() {
		System.out.println("  Début test Tortue");
		t = new Tortue();
		System.out.print("    ");
		position();
		push();
		setUnits(10,90);
		move();
		turnL();
		setUnits(5,90);
		move();
		turnR();
		pop();
		pop();
		push();
		t.init(new Point2D.Double(10,10), 0);
		pop();
		System.out.println("  Fin test Tortue");
	}

	private void position() {
		System.out.println("(" + t.getPosition().getX() + "," + 
				t.getPosition().getY() + "," + t.getAngle() + ")");
	}
	
	private void setUnits(double step, double delta) {
		t.setUnits(step, delta);
		System.out.println("    setUnits(" + step + "," + delta + ")");
	}
	
	private void move() {
		t.move();
		System.out.print("    move() => ");
		position();
	}
	
	private void turnL() {
		t.turnL();
		System.out.print("    turnL() => ");
		position();
	}
	
	private void turnR() {
		t.turnR();
		System.out.print("    turnR() => ");
		position();
	}
	
	private void push() {
		t.push();
		System.out.print("    push => ");
		position();	}
	
	private void pop() {
		t.pop();
		System.out.print("    pop => ");
		position();
	}
}

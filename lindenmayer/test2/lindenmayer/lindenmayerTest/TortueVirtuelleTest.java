package lindenmayerTest;

import java.awt.geom.Point2D;

public class TortueVirtuelleTest {
	
	private TortueVirtuelle t;
	
	public void test() {
		System.out.println("  Début test Tortue");
		t = new TortueVirtuelle();
		System.out.print("    ");
		position();
		push();
		init(new Point2D.Double(100,100), 90);
		setUnits(100,90);
		move();
		turnR();
		move();
		turnR();
		move();
		turnR();
		move();
		pop();
		pop();
		move();
		turnL();
		System.out.println("  Fin test Tortue");
	}

	private void position() {
		System.out.println("(" + t.getPosition().getX() + "," + 
				t.getPosition().getY() + "," + t.getAngle() + ")");
	}
	
	private void init(Point2D pos, double theta) {
		t.init(pos, theta);
		System.out.print("    init(" + pos.getX() + "," + pos.getY() + "," + 
				theta + ") => ");
		position();
	}
	
	private void setUnits(double step, double delta) {
		t.setUnits(step, delta);
		System.out.println("    setUnits(" + step + "," + delta + ")");
	}
	
	private void move() {
		t.move();
		System.out.print("    move()                 => ");
		position();
	}
	
	private void turnL() {
		t.turnL();
		System.out.print("    turnL()                => ");
		position();
	}
	
	private void turnR() {
		t.turnR();
		System.out.print("    turnR()                => ");
		position();
	}
	
	private void push() {
		t.push();
		System.out.print("    push                   => ");
		position();	}
	
	private void pop() {
		t.pop();
		System.out.print("    pop                    => ");
		position();
	}
}

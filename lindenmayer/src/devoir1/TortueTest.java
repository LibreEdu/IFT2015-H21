package devoir1;

public class TortueTest {
	
	Tortue t = new Tortue(0, 0, 90);
	
	public TortueTest() {
		System.out.println("Test Tortue");
		System.out.print("\t");
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
		System.out.println("Fin test Tortue");
	}

	private void position() {
		System.out.println("(" + t.getPosition().getX() + "," + 
				t.getPosition().getY() + "," + t.getAngle() + ")");
	}
	
	private void setUnits(double step, double delta) {
		t.setUnits(step, delta);
		System.out.println("\tsetUnits(" + step + "," + delta + ")");
	}
	
	private void move() {
		t.move();
		System.out.print("\tmove() => ");
		position();
	}
	
	private void turnL() {
		t.turnL();
		System.out.print("\tturnL() => ");
		position();
	}
	
	private void turnR() {
		t.turnR();
		System.out.print("\tturnR() => ");
		position();
	}
	
	private void push() {
		t.push();
		System.out.print("\tpush => ");
		position();	}
	
	private void pop() {
		t.pop();
		System.out.print("\tpop => ");
		position();
	}
}

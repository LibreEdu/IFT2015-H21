package devoir1;

public class TortueTest {
	
	Tortue b = new Tortue(0, 0, 90);
	
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
		System.out.println("(" + b.getPosition().getX() + "," + 
				b.getPosition().getY() + "," + b.getAngle() + ")");
	}
	
	private void setUnits(double step, double delta) {
		b.setUnits(step, delta);
		System.out.println("\tsetUnits(" + step + "," + delta + ")");
	}
	
	private void move() {
		b.move();
		System.out.print("\tmove() => ");
		position();
	}
	
	private void turnL() {
		b.turnL();
		System.out.print("\tturnL() => ");
		position();
	}
	
	private void turnR() {
		b.turnR();
		System.out.print("\tturnR() => ");
		position();
	}
	
	private void push() {
		b.push();
		System.out.print("\tpush => ");
		position();	}
	
	private void pop() {
		b.pop();
		System.out.print("\tpop => ");
		position();
	}
}

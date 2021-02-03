package devoir1;

public class BidonTest {
	
	Bidon b = new Bidon(0, 0, 90);
	
	public BidonTest() {
		System.out.println("Test Bidon");
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
		System.out.println("Fin test Bidon");
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

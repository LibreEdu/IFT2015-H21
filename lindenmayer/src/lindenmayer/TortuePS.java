package lindenmayer;

public class TortuePS extends AbstractTurtle {

	@Override
	public void draw() {
		super.draw();
		super.move();
		System.out.printf("%.1f %.1f L", getPosition().getX(),
				getPosition().getY());
		System.out.println();
	}
	
	public void pop() {
		super.pop();
		System.out.println("stroke");
		System.out.printf("%.1f %.1f newpath M", getPosition().getX(),
				getPosition().getY());
		System.out.println();
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

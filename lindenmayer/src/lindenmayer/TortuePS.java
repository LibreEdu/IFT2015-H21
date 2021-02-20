package lindenmayer;

public class TortuePS extends AbstractTurtle {

	public TortuePS() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void draw() {
		super.move();
		
		System.out.printf("%.1f %.1f L", getPosition().getX(),
				getPosition().getY());
		System.out.println();
	}
	
	public void pop() {
		System.out.println("stroke");
		System.out.println( position() + "newpath M");
	}
	
	String position() {
		return getPosition().getX() + " " + getPosition().getY() + " ";
	}

}

package devoir1;

import java.awt.geom.Point2D;
import java.util.Stack;

import lindenmayer.Turtle;

public class Tortue implements Turtle {
	
	private static final double X_DEFAUT = 0;
	private static final double Y_DEFAUT = 0;
	private static final double THETA_DEFAUT = 90;
	
	private class State implements Cloneable {
		private double x;
		private double y;
		private double theta;
		private State(Point2D pos, double theta) {
			x = pos.getX();
			y = pos.getY();
			this.theta = theta;
		}
		public Object clone() throws CloneNotSupportedException {
			return super.clone();
		}
		private void setState(State s) {
			x = s.x;
			y = s.y;
			theta = s.theta;			
		}
	}
	private State state;
	private double step;
	private double delta;
	private Stack<State> stack;

	public Tortue() {
		state = new State(new Point2D.Double(X_DEFAUT,Y_DEFAUT), THETA_DEFAUT);
		stack = new Stack<State>();
	}
	
	@Override
	public void draw() {
		penDown();
		move();
		penUp();
	}

	@Override
	public void move() {
		state.x += step * Math.cos(Math.toRadians(state.theta));
		state.y += step * Math.sin(Math.toRadians(state.theta));
	}

	@Override
	public void turnR() {
		state.theta -= delta;
	}

	@Override
	public void turnL() {
		state.theta += delta;
	}

	@Override
	public void push() {
		try {
			stack.push((State) state.clone());
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void pop() {
		if (!stack.empty()) {
			state.setState(stack.pop());			
		}
	}

	@Override
	public void stay() {
		
	}

	@Override
	public void init(Point2D pos, double angle_deg) {
		state.x = pos.getX();
		state.y = pos.getY();
		state.theta = angle_deg;
		stack.clear();
	}

	@Override
	public Point2D getPosition() {
		return new Point2D.Double(state.x, state.y);
	}

	@Override
	public double getAngle() {
		return state.theta;
	}

	@Override
	public void setUnits(double step, double delta) {
		this.step = step;
		this.delta = delta;
	}
	
	public void penUp() {
		
	}
	
	public void penDown() {
		
	}

}

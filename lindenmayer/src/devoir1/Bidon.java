package devoir1;

import java.awt.geom.Point2D;
import java.util.Stack;

import lindenmayer.Turtle;

public class Bidon implements Turtle {
	
	private class State implements Cloneable {
		private double x;
		private double y;
		private double theta;
		public State(double x, double y, double theta) {
			this.x = x;
			this.y = y;
			this.theta = theta;
		}
		public State(State state) {
			this.x = state.x;
			this.y = state.y;
			this.theta = state.theta;
		}
		public Object clone() throws CloneNotSupportedException {
			return (State) super.clone();
		}
		public void setState(State state) {
			this.x = state.x;
			this.y = state.y;
			this.theta = state.theta;			
		}
	}
	private State state;
	private double delta;
	private boolean draw;
	private double step;
	private Stack<State> stack;
	private static final double X_DEFAUT = 0;
	private static final double Y_DEFAUT = 0;
	private static final double THETA_DEFAUT = 90;

	public Bidon() {
		this(X_DEFAUT, Y_DEFAUT, THETA_DEFAUT);
	}

	public Bidon (double x, double y, double theta) {
		state.x = x;
		state.y = y;
		state.theta = theta;
	}
	
	@Override
	public void draw() {
		draw = true;
		move();
		draw = false;
	}

	@Override
	public void move() {
		state.x = state.x + step * Math.cos(Math.toRadians(getAngle()));
		state.y = state.y + step * Math.sin(Math.toRadians(getAngle()));
	}

	@Override
	public void turnR() {
		state.theta = state.theta - delta;
	}

	@Override
	public void turnL() {
		state.theta = state.theta + delta;
	}

	@Override
	public void push() {
		State backup = null;
		try {
			backup = new State((State)state.clone());
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		stack.push(backup);
	}

	@Override
	public void pop() {
		State backup = stack.pop();
		state.setState(backup);
	}

	@Override
	public void stay() {	
	}

	@Override
	public void init(Point2D pos, double angle_deg) {
		state = new State(pos.getX(), pos.getY(), angle_deg);
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

}

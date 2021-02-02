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
			this.x = state.getX();
			this.y = state.getY();
			this.theta = state.getTheta();
		}
		public double getX() {
			return x;
		}
		public void setX(double x) {
			this.x = x;
		}
		public double getY() {
			return y;
		}
		public void setY(double y) {
			this.y = y;
		}
		public double getTheta() {
			return theta;
		}
		public void setTheta(double theta) {
			this.theta = theta;
		}
		public Object clone() {
			return new State(this);
		}
		public void setState(State state) {
			this.x = state.getX();
			this.y = state.getY();
			this.theta = state.getTheta();			
		}
	}
	private State state;
	private double delta;
	private boolean draw;
	private double step;
	private Stack<State> stack;

	public Bidon() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void draw() {
		draw = true;
		move();
		draw = false;
	}

	@Override
	public void move() {
		state.setX(state.getX() + step * Math.cos(Math.toRadians(getAngle())));
		state.setY(state.getY() + step * Math.sin(Math.toRadians(getAngle())));
	}

	@Override
	public void turnR() {
		state.setTheta(state.getTheta() - delta);
	}

	@Override
	public void turnL() {
		state.setTheta(state.getTheta() + delta);
	}

	@Override
	public void push() {
		State backup = new State((State)state.clone());
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
		return new Point2D.Double(state.getX(), state.getY());
	}

	@Override
	public double getAngle() {
		return state.getTheta();
	}

	@Override
	public void setUnits(double step, double delta) {
		this.step = step;
		this.delta = delta;
	}

}

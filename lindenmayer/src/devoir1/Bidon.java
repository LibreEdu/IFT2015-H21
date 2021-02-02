package devoir1;

import java.awt.geom.Point2D;
import java.util.Stack;

import lindenmayer.Turtle;

public class Bidon implements Turtle{
	
	private class State{
		private Point2D pos;
		private double theta;
		public State(Point2D pos, double theta) {
			this.pos = pos;
			this.theta = theta;
		}
		public State(State state) {
			this.pos = state.getPos();
			this.theta = state.getTheta();
		}
		private Point2D getPos() {
			return pos;
		}
		public void setPos(Point2D pos) {
			this.pos = pos;
		}
		public double getTheta() {
			return theta;
		}
		public void setTheta(double theta) {
			this.theta = theta;
		}
		public State getState() {
			return this;
		}
		public void setState(State state) {
			this.pos = state.getPos();
			this.theta = state.getTheta();			
		}
	}
	private State state;
	private double delta;
	private boolean draw;
	private double d;
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
		state.pos.setLocation(
				state.getPos().getX() + d * Math.cos(Math.toRadians(getAngle())),
				state.getPos().getY() + d * Math.sin(Math.toRadians(getAngle())));
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
		State backup = new State(state.getState());
		stack.push(backup);
	}

	@Override
	public void pop() {
		State backup = stack.pop();
		state.setState(backup);
	}

	@Override
	public void stay() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(Point2D pos, double angle_deg) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Point2D getPosition() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double getAngle() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setUnits(double step, double delta) {
		// TODO Auto-generated method stub
		
	}

}

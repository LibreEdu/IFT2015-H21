package lindenmayer;

import java.awt.geom.Point2D;

import java.util.Stack;

public abstract class AbstractTurtle implements Turtle {
	
	private static final double X_DEFAUT = 0;
	private static final double Y_DEFAUT = 0;
	private static final double THETA_DEFAUT = 90;
	
	private class State implements Cloneable {
		private Point2D pos;
		private double theta;
		private State(Point2D pos, double theta) {
			this.pos = pos;
			this.theta = theta;
		}
		public Object clone() throws CloneNotSupportedException {
			return super.clone();
		}
		private void setState(State s) {
			pos = s.pos;
			theta = s.theta;			
		}
	}
	private State state;
	private double step;
	private double delta;
	private Stack<State> stack;

	public AbstractTurtle() {
		state = new State(new Point2D.Double(X_DEFAUT,Y_DEFAUT), THETA_DEFAUT);
		stack = new Stack<State>();
	}
	
	@Override
	public void draw() {
/*		if (!stack.empty()) {
			State sc = stack.peek();
			System.out.println("draw = " + sc.pos.getX() + " " + sc.pos.getY());			
		} 
*/	}

	@Override
	public void move() {
		double x = state.pos.getX() + 
				step * Math.cos(Math.toRadians(state.theta));
		double y = state.pos.getY() + 
				step * Math.sin(Math.toRadians(state.theta));
		state.pos.setLocation(x, y);
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
			Point2D p = new Point2D.Double(state.pos.getX(), state.pos.getY());
			stack.push(new State(p,state.theta));
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
		state.pos = pos;
		state.theta = angle_deg;
		stack.clear();
	}

	@Override
	public Point2D getPosition() {
		return state.pos;
	}

	@Override
	public double getAngle() {
		return state.theta;
	}

	public double getStep() {
		return step;
	}

	@Override
	public void setUnits(double step, double delta) {
		this.step = step;
		this.delta = delta;
	}

}

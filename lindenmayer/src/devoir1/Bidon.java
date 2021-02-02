package devoir1;

import java.awt.geom.Point2D;
import java.util.Stack;

import lindenmayer.Turtle;

public class Bidon implements Turtle{
	
	private class State{
		private double x;
		private double y;
		private double theta;
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
	}
	private State etat;
	private double delta;
	private boolean draw;
	private double d;
	private Stack<State> pile;

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
		etat.setX(etat.getX() + d * Math.cos(Math.toRadians(getAngle())));
		etat.setY(etat.getY() + d * Math.sin(Math.toRadians(getAngle())));
	}

	@Override
	public void turnR() {
		etat.setTheta(etat.getTheta() - delta);
	}

	@Override
	public void turnL() {
		etat.setTheta(etat.getTheta() + delta);
	}

	@Override
	public void push() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pop() {
		// TODO Auto-generated method stub
		
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

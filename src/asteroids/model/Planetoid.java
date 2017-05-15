package asteroids.model;

import be.kuleuven.cs.som.annotate.*;

public class Planetoid extends MinorPlanet {
	
	// CONSTRUCTORS
	
	/**
	 * Variable that keeps track of the total distance traveled by the planetoid.
	 */
	private double totalTraveledDistance;
	
	/**
	 * Set the total distance traveled by the planetoid.
	 * @param distance
	 * The distance traveled by the planetoid.
	 * @return see implementation...
	 * 
	 */
	protected void setTotalTraveledDistance(double distance){
		this.totalTraveledDistance = distance;
	}
	
	/**
	 * Get the total distance traveled by the planetoid.
	 * @return see implementation...
	 */
	public double getTotalTraveledDistance(){
		return totalTraveledDistance;
	}
	
	public Planetoid(double xCoord, double yCoord, double xVelocity, double yVelocity, double radius, double totalTraveledDistance, World world) {
		super(xCoord, yCoord, xVelocity, yVelocity, radius,world);
		setTotalTraveledDistance(totalTraveledDistance);
	
	}
	
	public Planetoid(double xCoord, double yCoord, double xVelocity, double yVelocity, double totalTraveledDistance, double radius) {
		this(xCoord, yCoord, xVelocity, yVelocity, radius, totalTraveledDistance, null);
	
	}
	
	@Override
	public void move(double time) throws IllegalArgumentException {
		super.move(time);
		shrink(getSpeed() * time * getShrinkingPercentage());
		double xVel = getXVelocity();
		double yVel = getYVelocity();
		totalTraveledDistance += time*getAbsSpeed(xVel, yVel);
	}
	
	
	
	// MASS (total)
	
	private final double density = 0.917 * Math.pow(10, 12);
	
	@Basic @Immutable @Raw
	public double getDensity() {
		return this.density;
	}
	
	@Basic @Raw
	public double getMass() {
		return (4/3)*Math.PI*Math.pow(getRadius(), 3)*getDensity();
	}

	
	// SHRINKING AND DISSOLVING
	
	public static double SHRINKING_PERCENTAGE = 0.0001;
	
	public static double getShrinkingPercentage() {
		return SHRINKING_PERCENTAGE;
	}
	
	public void shrink(double percentage) {
		double newRadius = getRadius() * 0.01 * percentage;
		if (newRadius < getMinRadius()) {
			terminate();
		} else {
			setRadius(newRadius);
		}
	}
	
	public void dissolve() {
		terminate();
		
		if (getRadius() >= 30 && getWorld() != null) { // spawn two asteroids
			double radius = getRadius() / 2;
			double direction = Math.random() * 2 * Math.PI;
			double xVelocity = Math.cos(direction) * 1.5 * getSpeed();
			double yVelocity = Math.sin(direction) * 1.5 * getSpeed();
			double[] velocities = limitSpeed(xVelocity, yVelocity);
			double deltaX = Math.cos(direction) * 0.75 * getRadius();
			double deltaY = Math.sin(direction) * 0.75 * getRadius();
			double x = getXCoord();
			double y = getYCoord();

			Asteroid a1 = new Asteroid(x + deltaX, y + deltaY, velocities[0], velocities[1], radius);
			Asteroid a2 = new Asteroid(x - deltaX, y - deltaY, -velocities[0], -velocities[1], radius);
			if ( a1.canBePlacedIn(getWorld()) ) getWorld().add(a1);
			if ( a2.canBePlacedIn(getWorld()) ) getWorld().add(a2);
		}
	}
	
}
